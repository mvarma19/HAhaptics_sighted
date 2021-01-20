

        package com.example.morsetranslator;

        import android.annotation.SuppressLint;
        import android.content.Context;
        import android.content.Intent;
        import android.content.SharedPreferences;
        import android.os.Bundle;
        import android.os.Vibrator;
        import android.preference.PreferenceManager;
        import android.speech.tts.TextToSpeech;
        import android.util.Log;
        import android.view.MotionEvent;
        import android.view.View;
        import android.widget.Button;
        import android.widget.SeekBar;
        import android.widget.TextView;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.coordinatorlayout.widget.CoordinatorLayout;

        import java.util.Calendar;

        import static android.app.PendingIntent.getActivity;
        import static com.example.morsetranslator.HAMorseCommon.user;
        import static java.lang.String.valueOf;

//import static com.example.morsetranslator.HAMorseCommon.timeunit;

public class HABlindTutorial extends AppCompatActivity {

    Vibrator mvibrator;
    SeekBar seekBar_vinterval;
    SeekBar seekBar_vduration;
    private  SharedPreferences preferences;

    private static final String PROGRESS="SEEKBAR";


    Button tv;
    private CoordinatorLayout coordinatorLayout;
    TextView pwTV;
    TextView tv_vinterval;
    TextView hiddentextview;
    TextView clrTV;
    //TextView enterTV;


    Button begintutorial;
    Button continue_tutorial;
    long down = 0;
    public String pw = "";
    //public int progresValue = 0;
    public int count = 0;
    static int timeunit=100;
    public boolean touchevent = false;
    private TextToSpeech t2;
    static int duration=10;
    String fileWriteString = "";
    String username;
    Bundle bundle;
    static int currentProgress;
    static int newProgressValue;



    static int interval=100;
    TextView tv_vduration;
    public static final String durationseek = "durationseek";
    public static final String intervalseek = "intervalseek";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ha_blind_tutorial);
        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        initializeVariables();

        retrieveItemsFromBundle();
        continue_tutorial = (Button) findViewById(R.id.continue_button);
        continue_tutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBundleAndOpenActivity(HAMainScreen.class);
                //startActivityForResult(intent,1);
            }
        });
//        t2 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    int result = t2.setLanguage(Locale.US);
//                    if (result == TextToSpeech.LANG_MISSING_DATA
//                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.e("TTS", "Language not supported");
//                    } else {
//                        //mButtonSpeak.setEnabled(true);
//                    }
//                } else {
//                    Log.e("Text to Speech", "Initialization failed");
//                }
//            }
//        });


//        preferences=getSharedPreferences(" ",MODE_PRIVATE);
//        final SharedPreferences.Editor editor=preferences.edit();
//
//        seekBar_vduration.setProgress(preferences.getInt(PROGRESS,0));
//        preferences1=getSharedPreferences(" ",MODE_PRIVATE);
//        final SharedPreferences.Editor editor1=preferences1.edit();
//        seekBar_vinterval.setProgress(preferences1.getInt(PROGRESS1,0));
        preferences = getSharedPreferences(PROGRESS , Context.MODE_PRIVATE);
        currentProgress = preferences.getInt(PROGRESS, 0);


        tv_vduration.setText("Vibration Duration:"+seekBar_vduration.getProgress()+"/"+seekBar_vduration.getMax());
        tv_vinterval.setText("Vibration Interval:"+seekBar_vinterval.getProgress()+"/"+seekBar_vinterval.getMax());

        seekBar_vduration.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            final int seek = 10;
            final int yourStep = 10;

            //int progressValue=0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tv_vduration.setText("Vibration Duration:" + seekBar.getProgress() + "/" + seekBar.getMax());
                progress = ((int) Math.round(progress / yourStep)) * yourStep;
                seekBar.setProgress(progress);

                //seekBar_vduration.setProgress(preferences1.getInt(PROGRESS1,progress));
                //Toast.makeText(getApplicationContext(), valueOf(progress), Toast.LENGTH_SHORT).show();

                duration = seekBar.getProgress();
//                Snackbar snackbar = Snackbar.make(seekBar,valueOf(progress), Snackbar.LENGTH_SHORT);
//                snackbar.show();

                //mvibrator.vibrate(100);
                //t2.speak("Vibration Duration Changed to:"+String.valueOf(duration),TextToSpeech.QUEUE_FLUSH,null);

            }


            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tv_vduration.setText("Vibration Duration:" + seekBar.getProgress() + "/" + seekBar.getMax());




            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mvibrator.vibrate(100);
                tv_vduration.setText("Vibration Duration:" + seekBar.getProgress() + "/" + seekBar.getMax());
//                editor1.putInt(PROGRESS,seekBar.getProgress());
//                editor1.commit();
                 newProgressValue = seekBar.getProgress();
                currentProgress = newProgressValue ;
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt(PROGRESS, currentProgress).apply();
                editor.commit();







            }

        });



        seekBar_vinterval.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            final int seek = 0;
            final int yourStep = 100;


            @Override
            public void onProgressChanged(SeekBar seekBar, int progresValue, boolean fromUser) {
                tv_vinterval.setText("Vibration Interval:" + seekBar.getProgress() + "/" + seekBar.getMax());
                progresValue = ((int) Math.round(progresValue / yourStep)) * yourStep;
               seekBar.setProgress(progresValue);
                //seekBar.setProgress(preferences.getInt(PROGRESS,0));
                //Toast.makeText(getApplicationContext(), valueOf(progresValue), Toast.LENGTH_SHORT).show();
//                Snackbar snackbar = Snackbar.make(seekBar,valueOf(progresValue), Snackbar.LENGTH_SHORT);
//                snackbar.show();
                interval = seekBar.getProgress();


                //t2.speak("Vibration Interval Changed to:"+String.valueOf(interval),TextToSpeech.QUEUE_FLUSH,null);


            }




            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(), "Tutorial Speed:", Toast.LENGTH_SHORT).show();
                tv_vinterval.setText("Vibration Interval:" + seekBar.getProgress() + "/" + seekBar.getMax());


            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mvibrator.vibrate(100);
                tv_vinterval.setText("Vibration Interval:" + seekBar.getProgress() + "/" + seekBar.getMax());
//                editor.putInt(PROGRESS,seekBar.getProgress());
//                editor.commit();




            }
        });






        tv = (Button) findViewById(R.id.tvb);

        pwTV = (TextView) findViewById(R.id.input);
        hiddentextview = (TextView) findViewById(R.id.del);
        clrTV = (TextView) findViewById(R.id.clr);
        //enterTV = (TextView) findViewById(R.id.enter);


        processTVPress(tv, 0);
        processTVPress(hiddentextview, 1);
        processTVPress(clrTV, 2);
        //processTVPress(enterTV, 3);

    }

    void addToBundleAndOpenActivity(Class cls) {
        Intent intent = new Intent(HABlindTutorial.this, cls);
        //HAMorseCommon.user = user;


        Log.e("Duration", valueOf(duration));

        Bundle bundle = new Bundle();


        bundle.putString("userName", user);
        bundle.putInt("duration",duration);
        bundle.putInt("interval",interval);


        intent.putExtras(bundle);
        startActivity(intent);
        Log.e("SentBundle", valueOf(bundle));
    }
    void retrieveItemsFromBundle(){
        if (bundle!=null) {

            user=bundle.getString("userName");
            duration=bundle.getInt("duration");
            interval=bundle.getInt("interval");


        }
    }


    private void initializeVariables() {
        seekBar_vinterval= (SeekBar) findViewById(R.id.vibration_intervalseekbar);
        tv_vinterval = (TextView) findViewById(R.id.vibration_interval);
        seekBar_vduration=(SeekBar)findViewById(R.id.vibration_durationseekbar);
        tv_vduration=(TextView)findViewById(R.id.vibrationintervaltv);


    }
//    public void fileSaving(){
//        try {
//            // Creates a file in the primary external storage space of the
//            // current application.
//            // If the file does not exists, it is created.
//            File filePath = new File(this.getFilesDir(), "docs");
//            if (!filePath.exists()){
//                filePath.mkdir();
//                //newFile.createNewFile();
//                // Adds a line to the file
//                //Uri contentUri = getUriForFile(this, "com.example.morsetranslator.fileprovider", newFile);
//            }
//            File newFile = new File(filePath, "new2_image.txt");
//            File testFile = new File(this.getExternalFilesDir(null), "TestFile1.txt");
//            Uri paths = getUriForFile(this, "com.example.morsetranslator.provider", newFile);
//            Log.e("URIFilePath", paths.toString());
//            BufferedWriter writerImage = new BufferedWriter(new FileWriter(newFile, true /*append*/));
//            writerImage.write("Current vibration interval is :" + interval+ "\n");
//            writerImage.write("Current vibration duration is :" + duration + "\n");
//
//
//            writerImage.close();
//            Log.e("FilePath", getApplicationContext().getApplicationContext().getPackageName() + "/TestFile.txt");
//
//
//            if (!testFile.exists()) {
//                testFile.createNewFile();
//                // Adds a line to the file
//                BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true /*append*/));
//                writer.write("This is a test file.");
//                writer.close();
//            } else {
//
//                BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true /*append*/));
//                writer.write("Current Vibration Interval is :" + interval + "\n");
//                writer.write("Current Vibration Duration is :" + duration + "\n");
//
//                writer.close();
//                Log.e("FilePath", getApplicationContext().getApplicationContext().getPackageName() + "/TestFile.txt");
//            }
//            // Refresh the data so it can seen when the device is plugged in a
//            // computer. You may have to unplug and replug the device to see the
//            // latest changes. This is not necessary if the user should not modify
//            // the files.
//            MediaScannerConnection.scanFile(this,
//                    new String[]{testFile.toString()},
//                    null,
//                    null);
//            //String filename="contacts_sid.txt";
//            //File testFile1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
//            Uri path = Uri.fromFile(testFile);
//            Log.e("FilePath",path.toString());
//            Intent emailIntent = new Intent(Intent.ACTION_SEND);
//// set the type to 'email'
//            emailIntent .setType("vnd.android.cursor.dir/email");
//            String[] to = {"roshan82@gmail.com"};
//            emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
//// the attachment
//            emailIntent .putExtra(Intent.EXTRA_STREAM, paths);
//// the mail subject
//            emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
//            startActivity(Intent.createChooser(emailIntent , "Send email..."));
//        } catch (IOException e) {
//            Log.e("ReadWriteFile", "Unable to write to the file."+e);
//        }
//    }



    public void processTVPress(final TextView t, final int t1) {

        t.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("Tag", "I am here!!");
                        if (t1 == 0) {
                            touchevent = true;
                            new Thread(new TouchVibe()).start();
                            //t2.speak(String.valueOf(pw),TextToSpeech.QUEUE_FLUSH,null);

                        }

//                        store += t.getText();
//                        input.setText(store);
                        break;
                    case MotionEvent.ACTION_MOVE:
                        //Log.d("Tag","ACTION MOVE");
                        //Log.d("Tag",String.valueOf("Move"));

                        //isMoving = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("TAG", "ACTION_UP");
                        if (t1 == 0) {
                            touchevent = false;
                        }
                        if (t1 == 1) {

                            if (pw != null && pw.length() > 0) {
                                pw = pw.substring(0, pw.length() - 1);
                            }
                            pwTV.setText(pw);
                        }
                        if (t1 == 2) {

                            pw = "";
                            pwTV.setText(pw);
                            //t2.speak(String.valueOf(pw),TextToSpeech.QUEUE_FLUSH,null);

                        }
//                        if (t1 == 3) {
//                            //HAMorseCommon.writeAnswerToFile(getApplicationContext(),"fromTouchMorse");
//                            HAMorseCommon.sendEmail(HABlindTutorial.this);
//                        }
//                        long diff = System.currentTimeMillis() - down;
//                        Log.d("time is",String.valueOf(diff));
//                        long standard = 300L;
//                        if(diff>standard)
//                            break;
//                        store += t.getText();
//                        input.setText(store);
//                        //enter.performClick();
//                        //isMoving=false;
                        break;
                }

                return true;
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    store += t.getText();
//                    input.setText(store);
//                }
                //return false;
            }
        });
    }


    class TouchVibe implements Runnable {
        @Override
        public void run() {
            down = System.currentTimeMillis();
            while (touchevent) {
                if (Math.abs(down - System.currentTimeMillis()) > interval) {
                    Log.d("TAG", "Thread");
                    down = System.currentTimeMillis();
                    Vibrator mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                    fileWriteString="This is the first time user saves the vibration interval and duration!";
                    fileWriteString = "Duration and Interval," + valueOf(duration) + ","  +   valueOf(interval) + "Time is:," + valueOf(Calendar.getInstance().getTimeInMillis())+",Date is"+ HAMorseCommon.dateTime() + "\n";
                    HAMorseCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);


                    mvibrator.vibrate(duration);


                    count++;
                    if (count == 10 || count > 10) {
                        count = 0;
                    }
                }
            }


            if (!touchevent) {

                mvibrator.cancel();
                Log.d("PW", valueOf(count));
                pw = pw + count;
                pwTV.setText(pw);
                count = 0;
            }



        }
    }
}



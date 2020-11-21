//package com.example.morsetranslator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Typeface;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.os.Vibrator;
//import android.util.Log;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Collections;
//import java.util.Date;
//import java.util.List;
//import java.util.Locale;
//
//public class HAMorseEvaluation extends AppCompatActivity {
//
//    String type="Preliminary";
//
//    long[][] patterns={{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
//
//
//    String user="";
//    Bundle bundle;
//    int trialNum=1;
//    int maxCodes=10;
//    int repetitions=2;
//    long startTime=0;
//    long ansTime=0;
//    int attempts=0;
//    int correctAnswers=0;
//    int[] sounds={R.raw.beep,R.raw.one, R.raw.two,R.raw.three,R.raw.four,R.raw.five,R.raw.six,R.raw.seven,R.raw.eight,R.raw.nine};
//    MediaPlayer m1;//=MediaPlayer.create(this,sounds[currentNumber]);
//    List<Integer> trialArray=new ArrayList<Integer>(maxCodes*repetitions);
//
//    TextView tvTrial;
//    EditText etAns;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_h_a_evaluation);
//        final Button buttonVibrate = (Button) findViewById(R.id.vibrate);
//        final Button buttonNext = (Button) findViewById(R.id.next);
//        Button Home=(Button) findViewById(R.id.home);
//        final Button sendEmailButton=(Button) findViewById(R.id.sendEmail);
//        final Vibrator mvibrator;
//        bundle=getIntent().getExtras();
//        tvTrial=(TextView)findViewById(R.id.trial);
//        etAns=(EditText)findViewById(R.id.ans);
//
//        for (int j = 0; j < repetitions; j++) {
//            for (int i = 0; i < maxCodes; i++) {
//                trialArray.add(i);
//            }
//        }
//        sendEmailButton.setVisibility(View.INVISIBLE);
//
//        Collections.shuffle(trialArray);
//        Log.e("LoginRnd",String.valueOf(trialArray));
//        mvibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//        setTrialNum(trialNum);
//        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy,HH:mm:ss", Locale.getDefault());
//        String currentDateandTime = sdf.format(new Date());
//        Log.e("DateTime",currentDateandTime.toString());
//        writeToFile("Evaluation of Participant No..,"+user+",@,"+currentDateandTime.toString(), false);
//        Date currentTime = Calendar.getInstance().getTime();
//        //Date currentDate=Calendar.getInstance().get
//        buttonVibrate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                patterns=HAMorseCommon.populate();
//                SystemClock.sleep(1000);
//                mvibrator.vibrate(patterns[trialArray.get(trialNum-1)], -1);
//                if (attempts==0){
//                    startTime=Calendar.getInstance().getTimeInMillis();
//                }
//                attempts++;
//            }
//        });
//
//        Home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToBundleAndOpenActivity(HAMainScreen.class);
//                //startActivityForResult(intent,1);
//            }
//        });
//
//        sendEmailButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sendEmail();
//                //startActivityForResult(intent,1);
//            }
//        });
//
//        buttonNext.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String answer=etAns.getText().toString();
//                if (answer.matches("")){
//                    Log.e("Answers","Empty Text lah");
//                    Toast toast1 = Toast.makeText(getApplicationContext(), "Please enter your answer", Toast.LENGTH_LONG);
//                    toast1.show();
//                }else {
//                    Log.e("Answers", "Answer Provided");
//                    if (answer.matches(String.valueOf(trialArray.get(trialNum-1)))){
//                        Log.e("Answer","Correct "+String.valueOf(trialArray.get(trialNum-1))+","+answer);
//                        correctAnswers++;
//                    }else {
//                        Log.e("Answer", "Incorrect " + String.valueOf(trialArray.get(trialNum - 1)) + "," + answer);
//                    }
//                    if (trialNum < maxCodes * repetitions) {
//                        ansTime=Calendar.getInstance().getTimeInMillis();
//                        writeToFile(answer,false);
//                        attempts=0;
//                        trialNum++;
//                    } else {
//                        tvTrial.setTypeface(tvTrial.getTypeface(), Typeface.BOLD);
//                        tvTrial.setText("Your Score: "+String.valueOf(correctAnswers)+"/"+String.valueOf(maxCodes * repetitions)+" ("+correctAnswers*100/(maxCodes * repetitions)+"%)");
//                        //SystemClock.sleep(2000);
//                        sendEmailButton.setVisibility(View.VISIBLE);
//                        buttonNext.setEnabled(false);
//                        buttonVibrate.setEnabled(false);
//                        etAns.setEnabled(false);
//                        writeToFile(answer,true);
//                    }
//                    etAns.getText().clear();
//                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.hideSoftInputFromWindow(etAns.getWindowToken(), 0);
//
//                }
//            }
//        });
//
//
//    }
//
//
//    private void setTrialNum(int num){
//        tvTrial.setText("Trial: "+Integer.toString(num)+"/"+repetitions*maxCodes);
//    }
//
//    public void writeToFile(String ans, boolean finished){
//        String ansString="0Prelim,"+String.valueOf(trialNum)+","+String.valueOf(startTime)+","+String.valueOf(ansTime)+","+String.valueOf(attempts)+","+String.valueOf(trialArray.get(trialNum-1))+","+ans+"\n";
//        HAMorseCommon.writeAnswerToFile(getApplicationContext(),ansString);
//        if (finished){
//            //HAMorseCommon.sendEmail(this);
//        }else {
//            setTrialNum(trialNum);
//        }
//    }
//    /*
//    public void writeAnswerToFile(String ans, boolean finished){
//        try {
//            // Creates a file in the primary external storage space of the
//            // current application.
//            // If the file does not exists, it is created.
//            String fileName="AnswerFileOf-"+user+".txt";
//            String ansString=String.valueOf(trialNum)+","+String.valueOf(startTime)+","+String.valueOf(ansTime)+","+String.valueOf(attempts)+","+String.valueOf(trialArray.get(trialNum-1))+","+ans+"\n";
//            File filePath = new File(this.getFilesDir(), "docs");
//            if (!filePath.exists()){
//                filePath.mkdir();
//            }
//            //--------------------
//            File answerFile = new File(filePath, fileName);
//            Uri paths = getUriForFile(this, "com.example.morsetranslator.provider", answerFile);
//            Log.e("URIFilePath",paths.toString());
//            BufferedWriter writerImage = new BufferedWriter(new FileWriter(answerFile, true));
//            //writerImage.write("Current number is :" + String.valueOf(currentNumber)+"\n");
//            writerImage.write(ansString);
//            writerImage.close();
//            Log.e("FilePath",getApplicationContext().getApplicationContext().getPackageName()+"/TestFile.txt");
//            MediaScannerConnection.scanFile(this,
//                    new String[]{answerFile.toString()},
//                    null,
//                    null);
//            //------------------------
//            //Writing data to a backup file in case emailing is not done properly
//            File backUpFile = new File(this.getExternalFilesDir(null), "BackupAnsof"+user+".txt");
//            if (!backUpFile.exists()){
//                backUpFile.createNewFile();
//                // Adds a line to the file
//            }
//            BufferedWriter writer = new BufferedWriter(new FileWriter(backUpFile, true ));
//            writer.write(ansString);
//            writer.close();
//            MediaScannerConnection.scanFile(this,
//                    new String[]{backUpFile.toString()},
//                    null,
//                    null);
//            // Refresh the data so it can seen when the device is plugged in a
//            // computer. You may have to unplug and replug the device to see the
//            // latest changes. This is not necessary if the user should not modify
//            // the files.
//
//            if (finished) {
//                Intent emailIntent = new Intent(Intent.ACTION_SEND);
//                // set the type to 'email'
//                emailIntent.setType("vnd.android.cursor.dir/email");
//                String to[] = {"roshan82@gmail.com","embodiedroshan@gmail.com"};
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
//                // the attachment
//                emailIntent.putExtra(Intent.EXTRA_STREAM, paths);
//                // the mail subject
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Answers of Participant.."+user);
//                startActivity(Intent.createChooser(emailIntent, "Send email..."));
//            }
//        } catch (IOException e) {
//            Log.e("ReadWriteFile", "Unable to write to the file."+e);
//        }
//    }*/
//
//    void addToBundleAndOpenActivity(Class cls) {
//        Intent intent = new Intent(HAMorseEvaluation.this, cls);
//        startActivity(intent);
//    }
//
//    void sendEmail(){
//        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//        HAMorseCommon.sendEmail(this);
//    }
//
//
//}

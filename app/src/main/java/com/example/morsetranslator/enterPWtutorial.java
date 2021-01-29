package com.example.morsetranslator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

//import static com.example.morsetranslator.HABlindTutorial.interval;
//import static com.example.morsetranslator.HABlindTutorial.duration;

import static com.example.morsetranslator.HAMorseCommon.user;




public class enterPWtutorial extends AppCompatActivity {

    Vibrator mvibrator;
    Bundle bundle;
    Button tv;
    TextView pwTV;
    TextView delTV;
    TextView clrTV;
    Button continue_trial;
    //TextView enterTV;
    TextView duration_tv;
    TextView interval_tv;
    TextView testPWtv;
    TextView trialTV;
    String fileWriteString = "";

    int duration=HABlindTutorial.duration;
    int interval=HABlindTutorial.interval;
    Button change;
    private TextToSpeech t2;
    //String pwstore="";
    //TextView conditionTV;
    Random r = new Random();
    int evalPW = 0;
    long startTime = 0;
    long down = 0;
    int trial = 1;
    public String pw = "";
    public int count = 0;
    AlertDialog.Builder builder;
    public boolean touchevent = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enter_pw_tutorial);
        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        bundle = getIntent().getExtras();
        retrieveItemsFromBundle();


        tv = (Button) findViewById(R.id.tvb);
        testPWtv = (TextView) findViewById(R.id.testpw);

        pwTV = (TextView) findViewById(R.id.input);
        delTV = (TextView) findViewById(R.id.del);
        clrTV = (TextView) findViewById(R.id.clr);
        //enterTV=(TextView) findViewById(R.id.enter);
        continue_trial = (Button) findViewById(R.id.continue_button);
        trialTV = (TextView) findViewById(R.id.tvtrial);
        trialTV.setText("Trial " + String.valueOf(trial) + "/3 ");


        duration_tv = (TextView) findViewById(R.id.duration);
        interval_tv = (TextView) findViewById(R.id.interval);
        duration_tv.setText("Vibration Duration: " + String.valueOf(duration) + "/100");
        interval_tv.setText("Vibration Interval: " + String.valueOf(interval) + "/400");
        change = (Button) findViewById(R.id.change_button);
        builder = new AlertDialog.Builder(this);
        change.setEnabled(false);


        t2 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    int result = t2.setLanguage(Locale.US);
                    if (result == TextToSpeech.LANG_MISSING_DATA
                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language not supported");
                    } else {
                        //mButtonSpeak.setEnabled(true);
                    }
                } else {
                    Log.e("Text to Speech", "Initialization failed");
                }
            }
        });



        testPWtv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                t2.speak("Please ENter the pin"+String.format("%04d",evalPW),TextToSpeech.QUEUE_FLUSH,null);
                return true;
            }
        });
        duration_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                t2.speak("Vibration Duration"+duration+"/100",TextToSpeech.QUEUE_FLUSH,null);
                return false;
            }
        });
        interval_tv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                t2.speak("Vibration Interval"+interval+"/400",TextToSpeech.QUEUE_FLUSH,null);
                return false;
            }
        });
pwTV.setOnTouchListener(new View.OnTouchListener() {
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        t2.speak("Enter the PIN",TextToSpeech.QUEUE_FLUSH,null);
        return false;
    }
});


        //conditionTV=(TextView) findViewById(R.id.tvcondition);

        generateEvaluationPassword();
        retrieveItemsFromBundle();


        processTVPress(tv, 0);
        processTVPress(delTV, 1);
        processTVPress(clrTV, 2);
        //processTVPress(enterTV,3);

    }
    //final GestureDetector gdt = new GestureDetector(new enterPWtutorial());


    private void generateEvaluationPassword() {

        evalPW = r.nextInt(9999);
        testPWtv.setText("Please Enter the PIN: " + String.format("%04d", evalPW));
        //t2.speak("Required PIN"+evalPW+"Entered PiN"+pw,TextToSpeech.QUEUE_FLUSH,null);

       // t2.speak("Please Enter the PIN"+String.valueOf(evalPW),TextToSpeech.QUEUE_ADD,null);
    }




    public void processTVPress(final TextView t, final int t1) {
        t.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("Tag", "I am here!!");
                        if (t1 == 0) {
                            touchevent = true;
                            new Thread(new TouchVibe()).start();
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
                            t2.speak("Delete button!",TextToSpeech.QUEUE_FLUSH,null);
                        }
                        if (t1 == 2) {

                            pw = "";
                            pwTV.setText(pw);
                            t2.speak("Clear button!",TextToSpeech.QUEUE_FLUSH,null);
                        }
//                        if (t1==3){
//                            //HAMorseCommon.writeAnswerToFile(getApplicationContext(),"fromTouchMorse");
//                            HAMorseCommon.sendEmail(enterPWtutorial.this);
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
        continue_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (pw.equals(String.format("%04d", evalPW))) {
                    Log.e("Eval", String.valueOf(evalPW) + " CORRECT " + pw);
                    builder.setMessage("You have entered the right answer!").setTitle("PIN entry");
                    //return false;
                    t2.speak("You have entered the right PIN code!",TextToSpeech.QUEUE_FLUSH,null);
                } else {
                    Log.e("Eval", String.valueOf(evalPW) + " INCORRECT " + pw);
                    builder.setMessage("You have entered the wrong answer!").setTitle("PIN entry");
                    t2.speak("You have entered the right PIN code!",TextToSpeech.QUEUE_FLUSH,null);

                }
                t2.speak("Continue",TextToSpeech.QUEUE_FLUSH,null);
                Log.e("Answers", "Empty Text lah");
                builder.setMessage("Required PIN: " + String.format("%04d", evalPW) + "\nPIN you entered: " + pw)

                        .setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {


                                generateEvaluationPassword();
                                pw = "";
                                pwTV.setText(pw);
                                //shifts=0;
                                //trial == 1 ? t2.speak("fd",TextToSpeech.QUEUE_FLUSH,null) : ;;]
                                trial++;

                                if (trial >= 4) {
                                    //HAMorseCommon.user = username;
                                    addToBundleAndOpenActivity(HAMainScreen.class);


                                }
                                updateTV();

                                startTime = Calendar.getInstance().getTimeInMillis();
                                //finish();
//                                Toast.makeText(getApplicationContext(), "you choose yes action for alertbox",
//                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("PIN information");
                alert.show();
                fileWriteString = "practice PIN," + String.valueOf(evalPW) + "," + trial + "," + String.valueOf(startTime) + "," + String.valueOf(Calendar.getInstance().getTimeInMillis()) + "Required PIN," + String.format("%04d", evalPW) + "Entered PIN," + pw + "Date:," + HAMorseCommon.dateTime() + "\n";
                HAMorseCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);



            }
        });




    }


    void addToBundleAndOpenActivity(Class cls) {
        Intent intent = new Intent(enterPWtutorial.this, cls);

        Bundle bundle = new Bundle();

        bundle.putString("userName", user);
        bundle.putInt("duration", duration);
        bundle.putInt("interval", interval);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.e("SentBundle", String.valueOf(bundle));
    }

    void retrieveItemsFromBundle() {
        if (bundle != null) {

            user = bundle.getString("userName");
            duration = bundle.getInt("duration");
            interval = bundle.getInt("interval");


        }
    }

    private void updateTV() {

        if(trial < 4){
            t2.speak("Trial "+String.valueOf(trial)+"/3",TextToSpeech.QUEUE_FLUSH,null);
        }




       trialTV.setText("Trial " + String.valueOf(trial) + "/3 ");
       // t2.speak("Trial "+String.valueOf(trial)+"/3",TextToSpeech.QUEUE_FLUSH,null);

        Log.e("trial:" + String.valueOf(trial), String.valueOf(trial));

    }



    class TouchVibe implements Runnable {
        @Override
        public void run() {
            down = System.currentTimeMillis();
            while (touchevent) {
                if (Math.abs(down - System.currentTimeMillis()) > interval) {
                    Log.d("TAG", "Thread");
                    down = System.currentTimeMillis();
                    //long[] pattern = new long[]{0, duration, interval};
                    //Log.e("Hi I am vibration:" + interval + duration, String.valueOf(pattern));


                    mvibrator.vibrate(duration);

                    count++;

                    if (count == 10 || count > 10) {
                        count = 0;
                    }
                }
            }
            t2.speak("You have entered"+count,TextToSpeech.QUEUE_FLUSH,null);
            if (!touchevent) {
                mvibrator.cancel();
                Log.d("PW", String.valueOf(count));
                pw = pw + String.valueOf(count);
                pwTV.setText(pw);
                count = 0;
            }
        }
    }
}




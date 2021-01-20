package com.example.morsetranslator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
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
import static java.util.jar.Pack200.Unpacker.PROGRESS;


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


    int trial=1;


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
    //int trial = 1;
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
        trialTV.setText("Trial " + String.valueOf(trial) + "/5 ");
        duration_tv = (TextView) findViewById(R.id.duration);
        interval_tv = (TextView) findViewById(R.id.interval);
        duration_tv.setText("Vibration Duration: " + String.valueOf(duration) + "/100");
        interval_tv.setText("Vibration Interval: " + String.valueOf(interval) + "/400");
        change = (Button) findViewById(R.id.change_button);
        builder = new AlertDialog.Builder(this);
        change.setEnabled(false);



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
        //t2.speak(String.format("%04d", evalPW),TextToSpeech.QUEUE_ADD,null);
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
                        }
                        if (t1 == 2) {

                            pw = "";
                            pwTV.setText(pw);
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
                } else {
                    Log.e("Eval", String.valueOf(evalPW) + " INCORRECT " + pw);
                    builder.setMessage("You have entered the wrong answer!").setTitle("PIN entry");


                }
                Log.e("Answers", "Empty Text lah");
                builder.setMessage("Required PIN: " + String.format("%04d", evalPW) + "\nPIN you entered: " + pw)

                        .setPositiveButton("Okay!", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                generateEvaluationPassword();
                                pw = "";
                                pwTV.setText(pw);
                                //shifts=0;
                                trial++;
                                updateTV();
                                fileWriteString = "Practise PIN," + String.valueOf(evalPW) + "," + trial + "Start is time:," + String.valueOf(startTime) + "Today's date with seconds," + String.valueOf(Calendar.getInstance().getTimeInMillis()) + "PIN required," + String.format("%04d", evalPW) + "PIN entered:," + pw + " Date:," + HAMorseCommon.dateTime() + "\n";
                                fileWriteString="Selected Vibration duration and Interval are:"+duration+","+interval;

                                HAMorseCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);

                                if (trial >= 6 ) {


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
        trialTV.setText("Trial " + String.valueOf(trial) + "/5 ");

        Log.e("trial:" + String.valueOf(trial), String.valueOf(trial));

    }

//                            void sendEmail() {
//                                getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                                getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
//                                HAMorseCommon.sendEmail(this);
//                            }

    class TouchVibe implements Runnable {
        @Override
        public void run() {
            down = System.currentTimeMillis();
            while (touchevent) {
                if (Math.abs(down - System.currentTimeMillis()) > interval) {
                    Log.d("TAG", "Thread");
                    down = System.currentTimeMillis();
                    fileWriteString="This is the second time user uses the saved vibration interval and duration! for practice";
                    fileWriteString = "Duration and Interval," + String.valueOf(duration) + ","  +   String.valueOf(interval) + "Time is:," + String.valueOf(Calendar.getInstance().getTimeInMillis())+",Date is"+ HAMorseCommon.dateTime() + "\n";
                    HAMorseCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);



                    mvibrator.vibrate(duration);

                    count++;

                    if (count == 10 || count > 10) {
                        count = 0;
                    }
                }
            }
//            t2.speak(String.valueOf(count),TextToSpeech.QUEUE_FLUSH,null);
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




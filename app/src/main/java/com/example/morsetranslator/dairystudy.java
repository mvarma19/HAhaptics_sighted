package com.example.morsetranslator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
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

import static com.example.morsetranslator.HAMorseCommon.user;
import static java.lang.Thread.*;

public class dairystudy extends AppCompatActivity {

    Vibrator mvibrator;
    Bundle bundle;
    Button tv;
    TextView pwTV;
    TextView delTV;
    TextView clrTV;
    TextView conditionTV;
    Button continue_trial;
    //TextView enterTV;
    TextView diary;
    TextView duration_tv;
    TextView interval_tv;
    TextView testPWtv;
    TextView trialTV;
    String fileWriteString="";
    Button change;
    AlertDialog.Builder builder;
    int duration=HABlindTutorial.duration;
    int interval=HABlindTutorial.interval;


    private TextToSpeech t2;
    //String pwstore="";
    //TextView conditionTV;
    Random r = new Random();
    static long startSleep = 0;//for start sleep time
    int evalPW=0;
    long startTime=0;
    long down=0;
    int trial=0;
    public String pw="";
    public int count=0;

    int expCondition=HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex];
    static int conditionIndex=0;
    public boolean touchevent=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dairy);
        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        bundle=getIntent().getExtras();
        retrieveItemsFromBundle();

        tv = (Button) findViewById(R.id.tvb);
        diary=(TextView)findViewById(R.id.ds);

        testPWtv=(TextView) findViewById(R.id.testpw);

        pwTV=(TextView) findViewById(R.id.input);
        delTV=(TextView) findViewById(R.id.del);
        clrTV=(TextView) findViewById(R.id.clr);
        //enterTV=(TextView) findViewById(R.id.enter);
        continue_trial=(Button)findViewById(R.id.continue_button);
        trialTV=(TextView) findViewById(R.id.tvtrial);
        trialTV.setText("Trial "+String.valueOf(trial)+"/3 ");
        duration_tv=(TextView)findViewById(R.id.duration);
        interval_tv=(TextView)findViewById(R.id.interval);
        conditionTV=(TextView) findViewById(R.id.tvcondition);


        duration_tv.setText("Vibration Duration: "+String.valueOf(duration)+"/100");
        interval_tv.setText("Vibration Interval: "+String.valueOf(interval)+"/400");
        change=(Button)findViewById(R.id.change_button);
        builder = new AlertDialog.Builder(this);
        change.setEnabled(false);






        generateEvaluationPassword();
        retrieveItemsFromBundle();


        processTVPress(tv,0);
        processTVPress(delTV,1);
        processTVPress(clrTV,2);
        //processTVPress(enterTV,3);

    }

    //final GestureDetector gdt = new GestureDetector(new enterPWtutorial());



    private void generateEvaluationPassword(){
        evalPW=r.nextInt(9999);
        testPWtv.setText("Please Enter the PIN: "+String.format("%04d", evalPW));
    }





    public void processTVPress(final TextView t, final int  t1) {
        t.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        Log.e("Tag","I am here!!");
                        if (t1==0) {
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
                        if (t1==0) {
                            touchevent = false;
                        }
                        if (t1==1){

                            if (pw != null && pw.length() > 0) {
                                pw = pw.substring(0, pw.length() - 1);
                            }
                            pwTV.setText(pw);
                        }
                        if (t1==2){

                            pw="";
                            pwTV.setText(pw);
                        }
//
                        break;
                }

                return true;

            }
        });
        if(expCondition==2) {
            startSleep+= (new Random()).nextInt(100); //randomization




            Log.d("Hi, I am condition 2,Start time is random here:",String.valueOf(startSleep));

        }
        if(expCondition==4) {
            startSleep+= (new Random()).nextInt(100); //randomization

            Log.d("Hi i am condition 4:Start time is random here:",String.valueOf(startSleep));

        }
        if(expCondition==3) {
            interval+= (new Random()).nextInt(100);
            Log.d("hi i am condition3,random interval is:", String.valueOf(interval));


        }
        if(expCondition==4) {
            interval+= (new Random()).nextInt(100);
            Log.d("hi i am condition4,random interval is:", String.valueOf(interval));


        }

        continue_trial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pw.equals(String.format("%04d", evalPW))) {
                    Log.e("Eval", String.valueOf(evalPW) + " CORRECT " + pw);
                    //return false;
                } else {
                    Log.e("Eval", String.valueOf(evalPW) + " INCORRECT " + pw);
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

                                if (trial >= 3) {

                                    HAMorseCommon.conditionIndex++;
                                    Log.e("CONDITIONS:",String.valueOf(HAMorseCommon.conditionIndex));
                                    HAMorseCommon.conditionIndex--;
                                    addToBundleAndOpenActivity(Survey.class);

                                }


                                updateTV();

                                startTime = Calendar.getInstance().getTimeInMillis();

                            }
                        });
                AlertDialog alert = builder.create();
                //Setting the title manually
                alert.setTitle("PIN information");

                alert.show();





                fileWriteString = "Result of trial no"+"," +trial+","+"Condition number"+","+expCondition+","+ "Required PIN" +","+ String.valueOf(evalPW) +","+"Start time was:"+","+ String.valueOf(startTime) +","+ "Calender date and time:" +","+ String.valueOf(Calendar.getInstance().getTimeInMillis()) +","+ "Required PIN"+","+ String.format("%04d", evalPW) +","+ "Entered PIN"+"," + pw +","+ "Date:" +","+ HAMorseCommon.dateTime() + "\n";
                HAMorseCommon.writeAnswerToFile(getApplicationContext(), fileWriteString);



            }
        });
    };

    void addToBundleAndOpenActivity(Class cls){
        Intent intent = new Intent(dairystudy.this, cls);

        Bundle bundle=new Bundle();

        bundle.putString("userName",user);
        bundle.putInt("duration", duration);
        bundle.putInt("interval", interval);
        intent.putExtras(bundle);
        startActivity(intent);
        Log.e("SentBundle",String.valueOf(bundle));
    }

    void retrieveItemsFromBundle(){
        if (bundle!=null) {

            user=bundle.getString("userName");
            duration=bundle.getInt("duration");
            interval=bundle.getInt("interval");
        }

    }
    private void updateTV()  {
        SystemClock.sleep(startSleep);
        Log.e("system has slept for:",String.valueOf(startSleep));


        trialTV.setText("Trial "+String.valueOf(trial+1)+"/3 ");
        conditionTV.setText(" "+(String.valueOf(HAMorseCommon.conditionIndex+1))
                +"/"+String.valueOf(HAMorseCommon.conditionArray.length)
                +"("+String.valueOf(HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex])+")");
    }
    void sendEmail(){
        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        HAMorseCommon.sendEmail(this);
    }

    class TouchVibe implements Runnable {
        @Override
        public void run() {

            down = System.currentTimeMillis();



            while (touchevent){



                if (Math.abs(down-System.currentTimeMillis())>interval && Math.abs(down-System.currentTimeMillis())>startSleep) {




                    Log.d(" and interval ", String.valueOf(interval));

                    down=System.currentTimeMillis();



                    mvibrator.vibrate(duration);



                    count++;



                    if (count==10 || count>10){
                        count=0;
                    }
                }
            }


            updateTV();









            if (!touchevent){
                mvibrator.cancel();
                Log.d("PW", String.valueOf(count));
                pw=pw+String.valueOf(count);
                pwTV.setText(pw);
                count=0;
            }
        }

    }

}
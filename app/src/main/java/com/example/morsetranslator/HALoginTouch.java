package com.example.morsetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class HALoginTouch extends AppCompatActivity {

    Vibrator mvibrator;
    TextView tv;
    TextView pwTV;
    TextView delTV;
    TextView clrTV;
    TextView enterTV;
    long down=0;
    public String pw="";
    public int count=0;
    public boolean touchevent=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_a_login_touch);
        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tv = (TextView) findViewById(R.id.tvb);

        pwTV=(TextView) findViewById(R.id.input);
        delTV=(TextView) findViewById(R.id.del);
        clrTV=(TextView) findViewById(R.id.clr);
        enterTV=(TextView) findViewById(R.id.enter);


        processTVPress(tv,0);
        processTVPress(delTV,1);
        processTVPress(clrTV,2);
        processTVPress(enterTV,3);

    }

    public void processPress(MotionEvent e, int tv) {
        if(e.getAction() == MotionEvent.ACTION_UP){
            Log.e("Touch button",String.valueOf(tv));
            //vibrate(numberPad[tv]);
        }
        if(e.getAction() == MotionEvent.ACTION_DOWN){
            Log.e("remove finger",String.valueOf(tv));
            mvibrator.cancel();
            //SystemClock.sleep(1000);

        }
        if(e.getAction() == MotionEvent.ACTION_HOVER_ENTER){
            Log.e("HoverEnter",String.valueOf(tv));
            mvibrator.cancel();
            //SystemClock.sleep(1000);

        }
        if(e.getAction() == MotionEvent.ACTION_MOVE){
            Log.e("Moving",String.valueOf(tv));
            mvibrator.cancel();
            //SystemClock.sleep(1000);

        }
        if(e.getAction() == MotionEvent.ACTION_HOVER_MOVE){
            Log.e("HoverMoving",String.valueOf(tv));
            mvibrator.cancel();
            SystemClock.sleep(1000);

        }
        if(e.getAction() == MotionEvent.ACTION_HOVER_EXIT){
            Log.e("HoverExit",String.valueOf(tv));
            mvibrator.cancel();
            //SystemClock.sleep(1000);

        }
        //long presses the button and it would vibrate according to morse
        //short press would enter the password!

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
                        if (t1==3){
                            //HAMorseCommon.writeAnswerToFile(getApplicationContext(),"fromTouchMorse");
                            HAMorseCommon.sendEmail(HALoginTouch.this);
                        }
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
    };

    class TouchVibe implements Runnable {
        @Override
        public void run() {
            down = System.currentTimeMillis();
            while (touchevent){
                if (Math.abs(down-System.currentTimeMillis())>310){
                    Log.d("TAG", "Thread");
                    down=System.currentTimeMillis();
                    mvibrator.vibrate(HABlindTutorial.timeunit);
                    count++;
                    if (count==10 || count>10){
                        count=0;
                    }
                }
            }
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

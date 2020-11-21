//package com.example.morsetranslator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.ClipData;
//import android.content.Context;
//import android.media.AudioManager;
//import android.media.ToneGenerator;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.os.Vibrator;
//import android.util.Log;
//import android.view.DragEvent;
//import android.view.MotionEvent;
//import android.view.View;
//import android.widget.TextView;
//
//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;
//import java.util.Random;
//
//public class HALoginRandom extends AppCompatActivity {
//    TextView t1;
//    TextView t2;
//    TextView t3;
//    TextView t4;
//    TextView t5;
//    TextView t6;
//    TextView t7;
//    TextView t8;
//    TextView t9;
//    TextView t0;
//    TextView input;
//    TextView input1;
//    TextView input2;
//    TextView input3;
//    String pw="";
//
//
//
//    Vibrator mvibrator;
//    boolean longPress = false;
//    int[] numberPad = {1, 2,3,4,5,6,7,8,9,0};
//    List<Integer> myArray = new ArrayList<Integer>(10);
//    List<TextView> tvArray = new ArrayList<TextView>(10);
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_h_a_login_random);
//        tvArray.add(t1 = (TextView) findViewById(R.id.tv1));
//        tvArray.add(t2 = (TextView) findViewById(R.id.tv2));
//        tvArray.add(t3 = (TextView) findViewById(R.id.tv3));
//        tvArray.add(t4 = (TextView) findViewById(R.id.tv4));
//        tvArray.add(t5 = (TextView) findViewById(R.id.tv5));
//        tvArray.add(t6 = (TextView) findViewById(R.id.tv6));
//        tvArray.add(t7 = (TextView) findViewById(R.id.tv7));
//        tvArray.add(t8 = (TextView) findViewById(R.id.tv8));
//        tvArray.add(t9 = (TextView) findViewById(R.id.tv9));
//        tvArray.add(t0 = (TextView) findViewById(R.id.tv10));
//        input = (TextView) findViewById(R.id.input);
//
//
//
//
//
//        for (int i = 0; i < 10; i++)
//            myArray.add(i);
//
//
//        Random r = new Random();
//        int randInt = (r.nextInt(4));
//        Log.e("LoginB4Rnd", String.valueOf(myArray));
//
//
//        //Collections.rotate(myArray, 3 * randInt);
//        Collections.shuffle(myArray);
//        Log.e("LoginAfRnd", String.valueOf(myArray));
//
//        for (int i = 0; i < 10; i++)
//            numberPad[i] = myArray.get(i);
//
//        t0.setText(String.valueOf(numberPad[9]));
//        t1.setText(String.valueOf(numberPad[0]));
//        t2.setText(String.valueOf(numberPad[1]));
//        t3.setText(String.valueOf(numberPad[2]));
//        t4.setText(String.valueOf(numberPad[3]));
//        t5.setText(String.valueOf(numberPad[4]));
//        t6.setText(String.valueOf(numberPad[5]));
//        t7.setText(String.valueOf(numberPad[6]));
//        t8.setText(String.valueOf(numberPad[7]));
//        t9.setText(String.valueOf(numberPad[8]));
//
//        t1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                input.setText(t1.getText());
//            }
//        });
//
//        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
//
//        //code for long press
//        Log.e("goes before this", "this");
//        longPress(t1, 0);
//        longPress(t2, 1);
//        longPress(t3, 2);
//        longPress(t4, 3);
//        longPress(t5, 4);
//        longPress(t6, 5);
//        longPress(t7, 6);
//        longPress(t8, 7);
//        longPress(t9, 8);
//        longPress(t0, 9);
//        Log.e("short press", "this");
//
//
//
//
//        //code for short press
//        Log.e("Short press", "this");
//
//
//        shortPress(t1, 0);
//        shortPress(t2, 1);
//        shortPress(t3, 2);
//        shortPress(t4, 3);
//        shortPress(t5, 4);
//        shortPress(t6, 5);
//        shortPress(t7, 6);
//        shortPress(t8, 7);
//        shortPress(t9, 8);
//        shortPress(t0, 9);
//    }
//    public void longPress(TextView t, final int numPadIdx) {
//        t.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                Log.e("inside this",String.valueOf(numberPad[numPadIdx]));
//                ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//                toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
//                SystemClock.sleep(500);
//                vibrate(numberPad[numPadIdx]);
//                return true;
//            }
//        });
//    }
//
//
//
//
//    public void processPress(MotionEvent e, int tv) {
//        if(e.getAction() == MotionEvent.ACTION_UP){
//            Log.e("Touch button",String.valueOf(tv));
//            //vibrate(numberPad[tv]);
//        }
//        if(e.getAction() == MotionEvent.ACTION_DOWN){
//            Log.e("remove finger",String.valueOf(tv));
//            mvibrator.cancel();
//            //SystemClock.sleep(1000);
//
//        }
//        if(e.getAction() == MotionEvent.ACTION_HOVER_ENTER){
//            Log.e("HoverEnter",String.valueOf(tv));
//            mvibrator.cancel();
//            //SystemClock.sleep(1000);
//
//        }
//        if(e.getAction() == MotionEvent.ACTION_MOVE){
//            Log.e("Moving",String.valueOf(tv));
//            mvibrator.cancel();
//            //SystemClock.sleep(1000);
//
//        }
//        if(e.getAction() == MotionEvent.ACTION_HOVER_MOVE){
//            Log.e("HoverMoving",String.valueOf(tv));
//            mvibrator.cancel();
//            SystemClock.sleep(1000);
//
//        }
//        if(e.getAction() == MotionEvent.ACTION_HOVER_EXIT){
//            Log.e("HoverExit",String.valueOf(tv));
//            mvibrator.cancel();
//            //SystemClock.sleep(1000);
//
//        }
//        //long presses the button and it would vibrate according to morse
//        //short press would enter the password!
//
//    }
//    public void vibrate(int num){
//
//        //ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//        //toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP,150);
//        //SystemClock.sleep(500);
//        mvibrator.vibrate(HAMorseCommon.populate()[num], -1);
//    }
//    public void shortPress(TextView tv, final int numPadIdx){
//        Log.e("shortPress",String.valueOf(tv));
//        tv.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                pw=pw+String.valueOf(numberPad[numPadIdx]);
//                input.setText(pw);
//                Log.e("password",pw);
//            }
//        });
//    }
//
//
//
//
//}
//
//

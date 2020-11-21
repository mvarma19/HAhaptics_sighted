package com.example.morsetranslator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class HAForVideo extends AppCompatActivity {


    Bundle bundle;
    TextView t1;
    TextView t2;
    TextView t3;
    TextView t4;
    TextView t5;
    TextView t6;
    TextView t7;
    TextView t8;
    TextView t9;
    TextView t10;
    TextView t11;
    TextView t12;
    TextView pwtv;
    TextView trialTV;
    TextView conditionTV;

    Thread vibthread;

    ImageView iv;
    TextView testPWtv;
    int evalPW=0;
    String fileWriteString="";
    String pwstore="";
    long down=0;
    Button dialog;
    Button enter;
    Button delete;
    Button next;
    long startTime=0;
    final Context context = this;
    int shifts=0;
    //int expCondition=HAMorseCommon.videoConditionArray[HAMorseCommon.conditionIndex];
    int trial=0;
    long gTime=0;
    //final TextView[] tvs={t1,t2,t3,t4,t5,t6,t7,t8,t9,t0,t0L,t0R};
    Vibrator mvibrator;
    boolean longPress = false;
    boolean isMoving = false;
    int[] numberPad = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0, 0};
    List<Integer> myArray = new ArrayList<Integer>(12);
    List<TextView> tvArray = new ArrayList<TextView>(12);
    Random r = new Random();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_video);
        tvArray.add(t1 = (TextView) findViewById(R.id.tv1));
        tvArray.add(t2 = (TextView) findViewById(R.id.tv2));
        tvArray.add(t3 = (TextView) findViewById(R.id.tv3));
        tvArray.add(t4 = (TextView) findViewById(R.id.tv4));
        tvArray.add(t5 = (TextView) findViewById(R.id.tv5));
        tvArray.add(t6 = (TextView) findViewById(R.id.tv6));
        tvArray.add(t7 = (TextView) findViewById(R.id.tv7));
        tvArray.add(t8 = (TextView) findViewById(R.id.tv8));
        tvArray.add(t9 = (TextView) findViewById(R.id.tv9));
        tvArray.add(t10 = (TextView) findViewById(R.id.tv10));
        tvArray.add(t11 = (TextView) findViewById(R.id.tv11));
        tvArray.add(t12 = (TextView) findViewById(R.id.tv12));
        pwtv = (TextView) findViewById(R.id.input);
        testPWtv=(TextView) findViewById(R.id.testpw);
        enter = (Button) findViewById(R.id.button_enter);
        delete = (Button) findViewById(R.id.button_delete);
        next = (Button) findViewById(R.id.button_next);
        //dialog = (Button) findViewById(R.id.dialog);
        iv=(ImageView) findViewById(R.id.keyboardpattern);
        trialTV=(TextView) findViewById(R.id.tvtrial);
        conditionTV=(TextView) findViewById(R.id.tvcondition);

        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        bundle = getIntent().getExtras();


        for (int i = 1; i < 10; i++)
            myArray.add(i);
        myArray.add(0);
        myArray.add(0);
        myArray.add(0);

        //arrangeKeypad(expCondition);
        populateKeyboard(false);
        next.setVisibility(View.INVISIBLE);
        //generateEvaluationPassword();

        //updateTV();
//        startTime= Calendar.getInstance().getTimeInMillis();
//        fileWriteString="Comment"+","+expCondition+", Condition Login Screen Evaluation of,"+HAMorseCommon.user+",@"+HAMorseCommon.dateTime()+"\n";
//        HAMorseCommon.writeAnswerToFile(getApplicationContext(),fileWriteString);

        //code for long press
        //Log.e("goes before this", "this");
//        longPress(t1, 0);
//        longPress(t2, 1);
//        longPress(t3, 2);
//        longPress(t4, 3);
//        longPress(t5, 4);
//        longPress(t6, 5);
//        longPress(t7, 6);
//        longPress(t8, 7);
//        longPress(t9, 8);
//        longPress(t10, 9);
//        longPress(t11, 10);
//        longPress(t12, 11);
//
//        processPress(t1, 0);
//        processPress(t2, 1);
//        processPress(t3, 2);
//        processPress(t4, 3);
//        processPress(t5, 4);
//        processPress(t6, 5);
//        processPress(t7, 6);
//        processPress(t8, 7);
//        processPress(t9, 8);
//        processPress(t10, 9);
//        processPress(t11, 10);
//        processPress(t12, 11);




/*
        dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // custom dialog
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.customdialog);
                dialog.setTitle("Keypad Appearance...");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("where is this");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);

                //ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.row2);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }

        });*/

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwstore != null && pwstore.length() > 0) {
                    pwstore = pwstore.substring(0, pwstore.length() - 1);
//                    keyStrokeRegister("keystroke","Del");
                }
                pwtv.setText(pwstore);

            }
        });

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (pwstore.equals(String.format("%04d", evalPW))){
//                    Log.e("Eval",String.valueOf(evalPW)+" CORRECT "+pwstore);
//
//                }else{
//                    Log.e("Eval",String.valueOf(evalPW)+" INCORRECT "+pwstore);
//                }
                Log.e("Answers","Empty Text lah");
                Toast toast1 = Toast.makeText(getApplicationContext(), "Required PIN: "+String.format("%04d", evalPW)+ "\nPIN you entered: "+pwstore, Toast.LENGTH_SHORT);
                toast1.show();
//                fileWriteString="0result,"+String.valueOf(expCondition)+","+trial+","+String.valueOf(startTime)+","+String.valueOf(Calendar.getInstance().getTimeInMillis())+","+String.format("%04d", evalPW)+","+pwstore+","+HAMorseCommon.dateTime()+"\n";
//                HAMorseCommon.writeAnswerToFile(getApplicationContext(),fileWriteString);
                //generateEvaluationPassword();
                testPWtv.setText("Entered Password: "+pwstore);
                next.setVisibility(View.VISIBLE);

            }
        });

//        next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                shifts=0;
//                arrangeKeypad(expCondition);
//                populateKeyboard(false);
//                trial++;
//                if (trial>=1){
//                    HAMorseCommon.conditionIndex++;
//                    Log.e("CONDITIONSSSSS",String.valueOf(HAMorseCommon.conditionIndex));
//                    //HAMorseCommon.conditionIndex--;
//                    if (HAMorseCommon.conditionIndex>=HAMorseCommon.videoConditionArray.length) {
//                        addToBundleAndOpenActivity(HAMainScreen.class);
//                    }else{
//                        addToBundleAndOpenActivity(HAForVideo.class);
//                    }
//                }
//                updateTV();
//                startTime= Calendar.getInstance().getTimeInMillis();
//            }
//        });

        final GestureDetector gdt = new GestureDetector(new HAForVideo.GestureListener());
        testPWtv.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(final View view, final MotionEvent event) {
                gdt.onTouchEvent(event);
                return true;
            }
        });

    }



    void sendEmail(){
        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        HAMorseCommon.sendEmail(this);
    }





    //for long press
//    public void longPress(TextView t, final int numPadIdx) {
//
//        t.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                //Log.e("inside this", String.valueOf(numberPad[numPadIdx]));
//                vibthread= new Thread(new HAForVideo.LoginVibe(numberPad[numPadIdx], true));
//                vibthread.start();
////                keyStrokeRegister("keystrokeLongPress",String.valueOf(numberPad[numPadIdx]));
//                //ToneGenerator toneGen1 = new ToneGenerator(AudioManager.STREAM_MUSIC, 100);
//                //toneGen1.startTone(ToneGenerator.TONE_CDMA_PIP, 150);
//                //SystemClock.sleep(500);
//                return true;
//
//            }
//        });

   // }



//
//    public void processPress(final TextView t,  final int  t1) {
//
//        t.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//
//                switch (event.getAction())
//                {
//                    case MotionEvent.ACTION_DOWN:
//                        //Log.e("Tag","I am here!!");
//                        down = System.currentTimeMillis();
////                        pwstore += t.getText();
////                        input.setText(pwstore);
//                        break;
//
//
//                    case MotionEvent.ACTION_MOVE:
//                        //Log.d("Tag","ACTION MOVE");
//
//                        //isMoving = true;
//                        break;
//                    case MotionEvent.ACTION_UP:
//                        //Log.d("TAG", "ACTION_UP");
//                        long diff = System.currentTimeMillis() - down;
//                        //Log.d("time is",String.valueOf(diff));
//                        long standard = 200L;
//                        if(diff>standard)
//                            break;
//                        if (pwstore.length()<4) {
//                            pwstore += String.valueOf(numberPad[t1]);
////                            keyStrokeRegister("keystroke", String.valueOf(numberPad[t1]));
//
//                        }
//                        //new Thread(new HALoginEvaluation.LoginVibe(numberPad[t1], false)).start();
//                        if (vibthread!=null){
//                            //vibthread.stop();
//                        }
//                        pwtv.setText(pwstore);
//                        //arrangeKeypad(expCondition);
//                        populateKeyboard(false);
//                        //enter.performClick();
//                        //isMoving=false;
//
//                        break;
//                }
//                return false;
//
//            }
//        });
//
//
//
//
//
//    };




//    private void updateTV(){
//        trialTV.setText("Trial "+String.valueOf(trial+1)+"/1 ");
//        conditionTV.setText(" Condition "+(String.valueOf(HAMorseCommon.conditionIndex+1))
//                +"/"+String.valueOf(HAMorseCommon.videoConditionArray.length)
//                +"("+String.valueOf(HAMorseCommon.videoConditionArray[HAMorseCommon.conditionIndex])+")");
//    }


    private void arrangeKeypad(int condition){

        if (condition==1){
            iv.setImageDrawable(getResources().getDrawable(R.drawable.noshift));

        }
        if ((condition==2 && shifts==0) || condition==6)  {

            iv.setImageDrawable(getResources().getDrawable(R.drawable.rowshift1));

            int randInt = (r.nextInt(4));
            Collections.rotate(myArray, 3 * randInt);
            //Collections.shuffle(myArray);

            for (int i = 0; i < 12; i++) {
                numberPad[i] = myArray.get(i);

            }
        }



        if ((condition==3 && shifts==0) || condition==7){

            iv.setImageDrawable(getResources().getDrawable(R.drawable.columnshift1));

            for (int i = 0; i < 12; i++)
                numberPad[i] = myArray.get(i);

            //Log.e("numberPad", Arrays.toString(numberPad));

            //using a 2d array
            List<List<Integer>> new_2d_array = new ArrayList<>();
            int k = 0;
            for (int i = 0; i < 4; i++) {
                new_2d_array.add(new ArrayList<Integer>());
                for (int j = 0; j < 3; j++) {
                    new_2d_array.get(i).add(myArray.get(k));

                    k++;
                }
                //Log.e("Before rotation", String.valueOf(new_2d_array.get(i)));
            }

            //again generating a random number
            int randInt = (r.nextInt(3));
            //int rand = (r.nextInt(3));
            for (int i = 0; i < new_2d_array.size(); i++) {
                Collections.rotate(new_2d_array.get(i), randInt);
                Log.e("this array gets rotated", String.valueOf(new_2d_array.get(i)));

            }
            k = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    numberPad[k++] = new_2d_array.get(i).get(j);
                }
            }
        }

        if ((condition==4 && shifts==0) || condition==8){

            iv.setImageDrawable(getResources().getDrawable(R.drawable.rowscolumns2));


            for (int i = 0; i < 12; i++)
                numberPad[i] = myArray.get(i);

            int randInt = (r.nextInt(3));
            Collections.rotate(myArray, 3 * randInt);

            //using a 2d array
            List<List<Integer>> new_2d_array = new ArrayList<>();
            int k = 0;
            for (int i = 0; i < 4; i++) {
                new_2d_array.add(new ArrayList<Integer>());
                for (int j = 0; j < 3; j++) {
                    new_2d_array.get(i).add(myArray.get(k));

                    k++;
                }
                //Log.e("Before rotation", String.valueOf(new_2d_array.get(i)));
            }

            //again generating a random number
            randInt = (r.nextInt(3));
            //int rand = (r.nextInt(3));
            for (int i = 0; i < new_2d_array.size(); i++) {
                Collections.rotate(new_2d_array.get(i), randInt);
                Log.e("this array gets rotated", String.valueOf(new_2d_array.get(i)));

            }
            k = 0;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    numberPad[k++] = new_2d_array.get(i).get(j);
                }
            }
        }

        shifts++;
    }

    private void populateKeyboard(boolean show){
        if (show) {
            t1.setText(String.valueOf(numberPad[0]));
            t2.setText(String.valueOf(numberPad[1]));
            t3.setText(String.valueOf(numberPad[2]));
            t4.setText(String.valueOf(numberPad[3]));
            t5.setText(String.valueOf(numberPad[4]));
            t6.setText(String.valueOf(numberPad[5]));
            t7.setText(String.valueOf(numberPad[6]));
            t8.setText(String.valueOf(numberPad[7]));
            t9.setText(String.valueOf(numberPad[8]));
            t10.setText(String.valueOf(numberPad[9]));
            t11.setText(String.valueOf(numberPad[10]));
            t12.setText(String.valueOf(numberPad[11]));
        }else{
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t6.setText("");
            t7.setText("");
            t8.setText("");
            t9.setText("");
            t10.setText("");
            t11.setText("");
            t12.setText("");
        }
    }

//    private void generateEvaluationPassword(){
//        evalPW=r.nextInt(9999);
//        testPWtv.setText("Please Enter the PIN: "+String.format("%04d", evalPW));
//    }
    void addToBundleAndOpenActivity(Class cls){
        Intent intent = new Intent(HAForVideo.this, cls);
        finish();
        startActivity(intent);

    }


//    void keyStrokeRegister(String tag, String key){
//        fileWriteString=tag+","+String.valueOf(expCondition)+","+String.valueOf(trial)+","+String.valueOf(Calendar.getInstance().getTimeInMillis())+","+key+",numberPadWas,";
//
//        for (int i=0;i<numberPad.length;i++){
//            fileWriteString=fileWriteString+String.valueOf(numberPad[i])+",";
//        }
//        fileWriteString=fileWriteString+"\n";
//
//        HAMorseCommon.writeAnswerToFile(getApplicationContext(),fileWriteString);
//
//    }

//    class LoginVibe implements Runnable {
//
//        int keynum;
//        boolean islongpressed=false;
//        LoginVibe(int number, boolean keystate){
//            keynum=number;
//            islongpressed=keystate;
//        }

//        public void run() {
//            if (islongpressed) {
//                mvibrator.vibrate(HAMorseCommon.populate()[keynum], -1);
//            }
//            if (!islongpressed) {
//                mvibrator.cancel();
//            }
//        }
//        public void stop(){
//            Log.e("Thread","Stop");
//            islongpressed=false;
//        }
//    }


    private static final int SWIPE_MIN_DISTANCE = 120;
    private static final int SWIPE_THRESHOLD_VELOCITY = 200;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if(e1.getX() - e2.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                if (Math.abs(gTime-Calendar.getInstance().getTimeInMillis())<1000){
                    populateKeyboard(true);
                    Log.e("Gesture","DETECTED");

                }else{
                    populateKeyboard(false);
                }
                gTime=Calendar.getInstance().getTimeInMillis();
                Log.e("Gesture","Left");
                return false; // Right to left
            }  else if (e2.getX() - e1.getX() > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("Gesture","Right");
                return false; // Left to right
            }

            if(e1.getY() - e2.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("Gesture","Up");
                return false; // Bottom to top
            }  else if (e2.getY() - e1.getY() > SWIPE_MIN_DISTANCE && Math.abs(velocityY) > SWIPE_THRESHOLD_VELOCITY) {
                Log.e("Gesture","Down");
                return false; // Top to bottom
            }
            return false;
        }
    }
}
//package com.example.morsetranslator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.os.Vibrator;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//public class HAVibrationControl extends AppCompatActivity {
//    MediaPlayer m1;
//    Vibrator mvibrator;
//    Context context;
//    Toast toast;
//    String user;
//    TextView tvTimeunitDisplay;
//    int timeunit=HAMorseCommon.timeunit;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_h_a_vibration_control);
//
//        final Button Plus = (Button) findViewById(R.id.plus);
//        final Button minus = (Button) findViewById(R.id.minus);
//        final Button play = (Button) findViewById(R.id.play);
//        Button tutorial = (Button) findViewById(R.id.tutorial);
//        m1 = MediaPlayer.create(this, R.raw.beep);
//        mvibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//        tvTimeunitDisplay=(TextView)findViewById(R.id.tvTimeUnit);
//
//        updateTimeUnitTV(timeunit);
//        Log.e("Bundle",String.valueOf(timeunit));
//        Plus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (timeunit <= 300) {
//                    timeunit = timeunit + 100;
//                    //vibrateDemo();
//
//                }else{
//                    mvibrator.cancel();
//                    Toast toast1 = Toast.makeText(getApplicationContext(), "Cannot Exceed more!", Toast.LENGTH_LONG);
//                    toast1.show();
//                }
//                updateTimeUnitTV(timeunit);
//            }
//        });
//
//        minus.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (timeunit >= 200) {
//                    timeunit = timeunit - 100;
//                    int duration = Toast.LENGTH_LONG;
//                    toast = Toast.makeText(getApplicationContext(), "ddd", duration);
//                    toast.show();
//                    //vibrateDemo();
//
//                }else{
//                    mvibrator.cancel();
//                    toast = Toast.makeText(getApplicationContext(), "Cannot be less than this!", Toast.LENGTH_LONG);
//                    toast.show();
//                }
//                updateTimeUnitTV(timeunit);
//            }
//        });
//
//        tutorial.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToBundleAndOpenActivity(HAMorseTutorial.class);
//            }
//        });
//        play.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                vibrateDemo();
//            }
//        });
//
//    }
//
//    public void vibrateDemo(){
//        CharSequence text = "Timeunit is" + timeunit;
//        mvibrator.cancel();
//        long[] demopattern = HAMorseCommon.populate()[0]; // 0 morse code
//        mvibrator.vibrate(demopattern, -1);
//        SystemClock.sleep(20 * timeunit);
//        m1.start();
//        SystemClock.sleep(1000);
//
//        demopattern = HAMorseCommon.populate()[5]; // 0 morse code
//        mvibrator.vibrate(demopattern, -1);
//        SystemClock.sleep(20 * timeunit);
//
//    }
//
//    void addToBundleAndOpenActivity(Class cls){
//        Intent intent = new Intent(HAVibrationControl.this, cls);
//        startActivity(intent);
//    }
//
//
//    void updateTimeUnitTV(int tu){
//        HAMorseCommon.timeunit=tu;
//        tvTimeunitDisplay.setText("Interval Time: "+String.valueOf(tu));
//    }
//
//}

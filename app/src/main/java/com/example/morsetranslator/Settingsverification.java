package com.example.morsetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settingsverification extends AppCompatActivity {


    String user = HAMorseCommon.user;
    int expCondition = HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex];



    private TextToSpeech t1;
    TextView particpant;
    Button button;
    Button change_settings;
    TextView duration_tv;
    TextView interval_tv;
    int duration=HABlindTutorial.duration;
    int interval=HABlindTutorial.interval;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_verification);
        button=(Button)findViewById(R.id.continue_button);
        duration_tv = (TextView) findViewById(R.id.duration);
        interval_tv = (TextView) findViewById(R.id.interval);
        change_settings=(Button)findViewById(R.id.change_settings);
        duration_tv.setText("Vibration Duration: " + String.valueOf(duration) + "/100");
        interval_tv.setText("Vibration Interval: " + String.valueOf(interval) + "/400");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBundleAndOpenActivity(dairystudy.class);
            }
        });
        change_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBundleAndOpenActivity(HABlindTutorial.class);
            }
        });
    }


        void addToBundleAndOpenActivity (Class cls){
            Intent intent = new Intent(Settingsverification.this, cls);



            startActivity(intent);

        }

    }






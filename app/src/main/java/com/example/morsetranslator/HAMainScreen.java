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

public class HAMainScreen extends AppCompatActivity  {


    String user = HAMorseCommon.user;
    int expCondition = HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex];


    static boolean mRecording = false;
    static boolean mRestart = false;
    private int mFrequency = 100;
    private int mSpeed = 20;
    static EditText username;
    private TextToSpeech t1;
    TextView particpant;
    //private TextToSpeech textToSpeech;
    //static CheckBox checkBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_a_main_screen);
        final Button openTutorial = (Button) findViewById(R.id.buttonMainTutorial2);
        final Button openLogin = (Button) findViewById(R.id.buttonMainLoginScreen2);
        final Button openEval = (Button) findViewById(R.id.buttonMainEvaluation2);
        Button sendData = (Button) findViewById(R.id.buttonSendData);
        Button practice=(Button)findViewById(R.id.practice);



        particpant=(TextView)findViewById(R.id.tvParticipant2);
        username = (EditText) findViewById(R.id.uname2);
        username.setText(user);







        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        if (user.matches("")) {

        }

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfUserNameIsEntered()) {

                } else {


                    HAMorseCommon.writeAnswerToFile(getApplicationContext(), "Sent from Main Screen\n");
                    HAMorseCommon.sendEmail(HAMainScreen.this);

                }
            }
        });
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addToBundleAndOpenActivity(enterPWtutorial.class);
            }
        });

        openTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfUserNameIsEntered()) {

                } else {
                    String bt = openTutorial.getText().toString();
                    addToBundleAndOpenActivity(HABlindTutorial.class);
                }
            }
        });


        openEval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfUserNameIsEntered()) {

                } else {
                    String button = openEval.getText().toString();

                    addToBundleAndOpenActivity(Settingsverification.class);
                }
            }
        });
        openLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                if (!checkIfUserNameIsEntered()) {

                } else {
                    String button = openLogin.getText().toString();

                    addToBundleAndOpenActivity(information.class);
                }
            }
        });


    }





    void addToBundleAndOpenActivity(Class cls) {
        Intent intent = new Intent(HAMainScreen.this, cls);
        HAMorseCommon.user = username.getText().toString();




        startActivity(intent);

    }

    private boolean checkIfUserNameIsEntered() {
        user = username.getText().toString();
        if (user.matches("")) {
            Log.e("Answers", "Empty Text lah");
            Toast toast1 = Toast.makeText(getApplicationContext(), "Please enter your Name/Code!", Toast.LENGTH_LONG);


            toast1.show();
            return false;
        } else {

            HAMorseCommon.user = user;
            return true;
        }

    }
}




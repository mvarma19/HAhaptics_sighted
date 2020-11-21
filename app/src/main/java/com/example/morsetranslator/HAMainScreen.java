package com.example.morsetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
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
        //checkBox = (CheckBox) findViewById(R.id.accessibility);
        //Button openForVideo = (Button) findViewById(R.id.buttonExpertEval);
        username = (EditText) findViewById(R.id.uname2);
        username.setText(user);
        //textToSpeech = new TextToSpeech(this, this);


//        final Button mButtonSpeak = findViewById(R.id.mButtonSpeak);
//
//        t1 = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
//            @Override
//            public void onInit(int status) {
//                if (status == TextToSpeech.SUCCESS) {
//                    int result = t1.setLanguage(Locale.US);
//                    if (result == TextToSpeech.LANG_MISSING_DATA
//                            || result == TextToSpeech.LANG_NOT_SUPPORTED) {
//                        Log.e("TTS", "Language not supported");
//                    } else {
//                        mButtonSpeak.setEnabled(true);
//                    }
//                } else {
//                    Log.e("Text to Speech", "Initialization failed");
//                }
//            }
//        });
//        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                speak();
//            }
//
//        });
//        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                {
//                    mButtonSpeak.setVisibility(View.VISIBLE);
//                }
//                else
//                {
//                    mButtonSpeak.setVisibility(GONE);
//                    t1.shutdown();
//                }
//            }
//        });


        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH);
//                startActivityForResult(intent, 1);


            }
        });

        //String toSpeak = user.toString();



        /*if (!HAMorseCommon.randomized){
            //Collections.shuffle(myArray);
            List<Integer> myArray = new ArrayList<Integer>(9);
            for (int i = 1; i < 8; i++) {
                if (i<=4){
                    myArray.add(i);
                }else{
                    myArray.add(i+1);
                }
            }
            Log.e("CondArray",String.valueOf(myArray));
            Collections.shuffle(myArray);
            Log.e("CondArrayRND",String.valueOf(myArray));
            for (int i = 0; i < 7; i++) {
                HAMorseCommon.conditionArray[i] = myArray.get(i);

            }
            Log.e("CondArrayReal",String.valueOf(HAMorseCommon.conditionArray));
            HAMorseCommon.randomized=true;
        }*/


        if (user.matches("")) {
            //username.setText("Roshan");
        }

        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfUserNameIsEntered()) {

                } else {
                    //t1.speak("SEND DATA", TextToSpeech.QUEUE_FLUSH, null);


                    HAMorseCommon.writeAnswerToFile(getApplicationContext(), "Sent from Main Screen\n");
                    HAMorseCommon.sendEmail(HAMainScreen.this);
                    //addToBundleAndOpenActivity(ScrollTest.class);
                }
            }
        });

        openTutorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkIfUserNameIsEntered()) {

                } else {
                    String bt = openTutorial.getText().toString();

                    //t1.speak(bt, TextToSpeech.QUEUE_FLUSH, null);

                    //Toast.makeText(bt,Toast.LENGTH_LONG,1);

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
                    //t1.speak(button, TextToSpeech.QUEUE_FLUSH, null);

                    addToBundleAndOpenActivity(dairystudy.class);
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
                    //t1.speak(button, TextToSpeech.QUEUE_FLUSH, null);
                    addToBundleAndOpenActivity(information.class);
                }
            }
        });

//        openForVideo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                user = username.getText().toString();
//                addToBundleAndOpenActivity(HAForVideo.class);
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//        if (resultCode == RESULT_OK && data != null) {
//            switch (requestCode) {
//                case 1:
//                    int intFound = getNumberFromResult(data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS));
//                    if (intFound != -1) {
//                        username.setText(String.valueOf(intFound));
//                        //t1.speak("you have entered:"+String.valueOf(intFound), TextToSpeech.QUEUE_FLUSH, null);
//
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Sorry, I didn't catch that! Please try again", Toast.LENGTH_LONG).show();
//                    }
//                    break;
//            }
//        } else {
//            Toast.makeText(getApplicationContext(), "Failed to recognize speech!", Toast.LENGTH_LONG).show();
//
//        }
//    }
//
//    private int getNumberFromResult(ArrayList<String> results)
//    {
//        for(String str:results)
//        {
//            return getIntNumberFromText(str);
//        }
//
//        return -1;
//    }
//
//    private int getIntNumberFromText(String strNum) {
//        try {
//            return Integer.parseInt(strNum);
//        }catch(NumberFormatException ex) {
//            return -1;
//        }
//    }

//    private void speak() {
//        String text = username.getText().toString();
//        t1.speak(text, TextToSpeech.QUEUE_FLUSH, null);
//    }
//    @Override
//    protected void onDestroy() {
//        if (t1 != null) {
//            t1.stop();
//            t1.shutdown();
//        }
//        super.onDestroy();
//    }
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

            //t1.speak("Please enter you code!", TextToSpeech.QUEUE_FLUSH, null);
            toast1.show();
            return false;
        } else {

            HAMorseCommon.user = user;
            return true;
        }

    }
}


//    @Override
//    public void onInit(int status) {
//
//    }
//}


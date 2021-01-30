package com.example.morsetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class homepage extends AppCompatActivity {


    TextView particpant;
    Button button;
    ImageView image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);
        button = (Button) findViewById(R.id.button);
        image = (ImageView) findViewById(R.id.imageView);


        particpant = (TextView) findViewById(R.id.tvParticipant2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToBundleAndOpenActivity(HAMainScreen.class);
            }
        });
    }


    void addToBundleAndOpenActivity(Class cls) {
        Intent intent = new Intent(homepage.this, cls);


        startActivity(intent);

    }

}







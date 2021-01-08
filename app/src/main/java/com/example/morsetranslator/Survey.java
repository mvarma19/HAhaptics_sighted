package com.example.morsetranslator;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Survey extends AppCompatActivity {
    TextView title;
    TextView confidence;
    SeekBar confidenceseek;
    TextView mentaldemand;
    SeekBar mentaldemandseek;
    TextView mental;
    TextView confidentno;
    TextView confidentyes;
    TextView mentaltv;
    TextView mentalhigh;
    EditText comments;
    String[] questionsList;

    public static ArrayList<String> selectedAnswers;

    RadioButton improve_yes;
    RadioButton improve_no;
    RadioButton again_yes;
    RadioButton again_no;
    Button bSubmit;

    int expCondition=HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex];
    String type="qualitative";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.survey);
        title = (TextView) findViewById(R.id.title);
        confidence = (TextView) findViewById(R.id.confidencetv);
        confidenceseek = (SeekBar) findViewById(R.id.cofidenceseek);
        mentaldemand = (TextView) findViewById(R.id.mentaldemand);
        mentaldemandseek = (SeekBar) findViewById(R.id.mentaldemandseek);
        mental = (TextView) findViewById(R.id.mentaldemand);
        confidentyes = (TextView) findViewById(R.id.confidentyes);
        confidentno = (TextView) findViewById(R.id.confidentno);
        mentaltv = (TextView) findViewById(R.id.mental);
        mentalhigh = (TextView) findViewById(R.id.mentalhigh);
        improve_yes = (RadioButton) findViewById(R.id.improve_yes);
        again_yes=(RadioButton)findViewById(R.id.future_yes);
        again_no=(RadioButton)findViewById(R.id.future_no);
        improve_no = (RadioButton) findViewById(R.id.improve_no);
        bSubmit = findViewById(R.id.submit);
        comments=(EditText)findViewById(R.id.comments_user);




        processSB(mentaldemandseek,mentaldemand,"Mental demand:");
        processSB(confidenceseek,confidence,"Confidence:");

        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentbyuser=comments.toString();

                String result = "Selected option: ";
                result+= (improve_yes.isChecked())?"Yes":(improve_no.isChecked())?"No":(again_yes.isChecked())?"Yes":(again_no.isChecked())?"No":"";
                String fileWriteString="0QE,"+expCondition+",,"
                        +String.valueOf(confidenceseek.getProgress())+","
                        +String.valueOf(mentaldemandseek.getProgress())+","
                        +commentbyuser +","
                        +result+","
                        




              //  result+= (improve_yes.isChecked())?"":(improve_no.isChecked())?"":(again_yes.isChecked())?"":(again_no.isChecked())?"":""




                        +String.valueOf(Calendar.getInstance().getTimeInMillis())+"\n";

                HAMorseCommon.writeAnswerToFile(getApplicationContext(),fileWriteString);
                HAMorseCommon.conditionIndex++;
                if (HAMorseCommon.conditionIndex>=HAMorseCommon.conditionArray.length){
                    sendEmail();
                }else {
                    //HAMorseCommon.conditionIndex--;
                    addToBundleAndOpenActivity(dairystudy.class);
                }
            }
        });



    }




    private void processSB(final SeekBar sb, final TextView tv, final String string){
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int sbValue=sb.getProgress();
                sbValue=sbValue;
                //tv.setText(string+String.valueOf(sbValue));
                // Write code to perform some action when progress is changed.
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Write code to perform some action when touch is started.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Write code to perform some action when touch is stopped.
                //Toast.makeText(MainActivity.this, "Current value is " + seekBar.getProgress(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        String str="";
        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.improve_yes:
                if(checked)
                    str = "Yes selected";
                break;
            case R.id.improve_no:
                if(checked)
                    str = "No Selected";
                break;
            case R.id.future_yes:
                if(checked)
                    str = "Yes selected";
                break;
            case R.id.future_no:
                if(checked)
                    str = "No Selected";
                break;
        }
        Toast.makeText(getApplicationContext(), str, Toast.LENGTH_SHORT).show();
    }


    void addToBundleAndOpenActivity(Class cls){
        Intent intent = new Intent(Survey.this, cls);
        finish();
        startActivity(intent);
    }
    void sendEmail(){
        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        HAMorseCommon.sendEmail(this);
    }
}
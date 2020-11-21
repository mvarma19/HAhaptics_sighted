package com.example.morsetranslator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class HAQaulEvaluation extends AppCompatActivity {

    SeekBar sbMental;
    SeekBar sbPhysical;
    SeekBar futyes;
    EditText comments;
//    SeekBar sbPerformance;
//    SeekBar sbEffort;
//    SeekBar sbFrustration;
//    SeekBar sbSecurity;

    TextView tvMental;
    TextView tvPhysical;
    TextView future;

//    TextView tvEffort;
//    TextView tvFrustration;
//    TextView tvSecurity;

    Button bSubmit;

    int expCondition=HAMorseCommon.conditionArray[HAMorseCommon.conditionIndex];
    String type="qualitative";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_a_qaul_evaluation);

        sbMental=findViewById(R.id.mental);
        sbPhysical=findViewById(R.id.physical);
        future=findViewById(R.id.future);
        futyes=findViewById(R.id.futyes);
        comments=findViewById(R.id.comments_user);
//        sbPerformance=findViewById(R.id.performance);
//        sbEffort=findViewById(R.id.effort);
//        sbFrustration=findViewById(R.id.frustration);
//        sbSecurity=findViewById(R.id.security);

        tvMental=findViewById(R.id.mentaltv);
        tvPhysical=findViewById(R.id.physicaltv);
       //future=findViewById(R.id.future);

//        tvEffort=findViewById(R.id.efforttv);
//        tvFrustration=findViewById(R.id.frustrationtv);
//        tvSecurity=findViewById(R.id.securitytv);

        bSubmit = findViewById(R.id.submit);

        processSB(sbMental, tvMental, "Confidence: ");
        processSB(sbPhysical, tvPhysical, "Improvement : ");
        processSB(futyes, future, "Future Use: ");
//        processSB(sbPerformance, tvPerformance, "Performance Demand: ");
//        processSB(sbEffort, tvEffort, "Effort: ");
//        processSB(sbFrustration, tvFrustration, "Frustration: ");
//        processSB(sbSecurity, tvSecurity, "Sense of Security: ");


        bSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fileWriteString="0QE,"+expCondition+",,"
                        +String.valueOf(sbMental.getProgress())+","
                        +String.valueOf(sbPhysical.getProgress())+","
                      +String.valueOf(futyes.getProgress())+","
                        +String.valueOf(comments)+","
//                        +String.valueOf(sbPerformance.getProgress())+","
//                        +String.valueOf(sbEffort.getProgress())+","
//                        +String.valueOf(sbFrustration.getProgress())+","
//                        +String.valueOf(sbSecurity.getProgress())+","
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

    void addToBundleAndOpenActivity(Class cls){
        Intent intent = new Intent(HAQaulEvaluation.this, cls);
        finish();
        startActivity(intent);
    }
    void sendEmail(){
        getIntent().addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);  getIntent().addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        HAMorseCommon.sendEmail(this);
    }
}

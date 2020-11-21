//package com.example.morsetranslator;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.content.Context;
//import android.content.Intent;
//import android.media.MediaPlayer;
//import android.media.MediaScannerConnection;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.SystemClock;
//import android.os.Vibrator;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.CheckBox;
//import android.widget.TextView;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import static androidx.core.content.FileProvider.getUriForFile;
//
//public class HAMorseTutorial extends AppCompatActivity {
//
//
//    long[][] patterns={{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0},{0,0,0,0,0,0,0,0,0,0}};
//
//    int currentNumber=0;
//    int timeunit=100;
//    String user;
//    Bundle bundle;
//
//    int[] sounds={R.raw.beep,R.raw.one, R.raw.two,R.raw.three,R.raw.four,R.raw.five,R.raw.six,R.raw.seven,R.raw.eight,R.raw.nine};
//    MediaPlayer m1;//=MediaPlayer.create(this,sounds[currentNumber]);
//
//    TextView displayNumber;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_h_a_tutorial);
//        Button prvButton = (Button) findViewById(R.id.previous);
//        //Button Play=(Button)findViewById(R.id.Pla);
//        final CheckBox soundPlay=(CheckBox) findViewById(R.id.playSoundCheckBox);
//        //    Button Repeat=(Button)findViewById(R.id.Repeat);
//        Button Next=(Button)findViewById(R.id.next);
//        final Button Vibrate=(Button)findViewById(R.id.vibrate);
//        Button Settings=(Button)findViewById(R.id.settings);
//        Button Home=(Button)findViewById(R.id.home);
//        final Vibrator mvibrator;
//        bundle=getIntent().getExtras();
//        displayNumber=(TextView)findViewById(R.id.display_id);
//
//        //timeunit=bundle.getInt("timeunit");
//        retrieveItemsFromBundle();
//        displayNumber.setText(Integer.toString(currentNumber));
//
//        Settings.setEnabled(false);
//
//        mvibrator=(Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
//
//
//        /*Play.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                //m1.start();
//                fileSaving();
//
//            }
//        });*/
//        Next.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentNumber<9) {
//                    currentNumber++;
//                }else{
//                    currentNumber=0;
//                }
//                displayNumber.setText(Integer.toString(currentNumber));
//            }
//        });
//
//        prvButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentNumber>0) {
//                    currentNumber--;
//                }else{
//                    currentNumber=9;
//                }
//                displayNumber.setText(Integer.toString(currentNumber));
//            }
//        });
//        Settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToBundleAndOpenActivity(HAVibrationControl.class);
//            }
//        });
//
//        Home.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                addToBundleAndOpenActivity(HAMainScreen.class);
//                //startActivityForResult(intent,1);
//            }
//        });
//
//
//        Vibrate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.e("Hi",String.valueOf(soundPlay.isChecked()));
//                if (soundPlay.isChecked()){
//                    m1 = MediaPlayer.create(HAMorseTutorial.this, sounds[currentNumber]);
//                    m1.start();
//                }
//                populate();
//                SystemClock.sleep(2000);
//                mvibrator.vibrate(patterns[currentNumber], -1);
//
//            }
//        });
//
//
//
//
//    }
//
//    public void populate(){
//
//
//
//        patterns=HAMorseCommon.populate();
//        // {0, dot, gap, dot, gap, dot, gap, dot, gap, dot}};
//    }
//    public void fileSaving(){
//        try {
//            // Creates a file in the primary external storage space of the
//            // current application.
//            // If the file does not exists, it is created.
//            File filePath = new File(this.getFilesDir(), "docs");
//            if (!filePath.exists()){
//                filePath.mkdir();
//                //newFile.createNewFile();
//                // Adds a line to the file
//                //Uri contentUri = getUriForFile(this, "com.example.morsetranslator.fileprovider", newFile);
//            }
//            File newFile = new File(filePath, "new2_image.txt");
//            File testFile = new File(this.getExternalFilesDir(null), "TestFile1.txt");
//            Uri paths = getUriForFile(this, "com.example.morsetranslator.provider", newFile);
//            Log.e("URIFilePath",paths.toString());
//            BufferedWriter writerImage = new BufferedWriter(new FileWriter(newFile, true /*append*/));
//            writerImage.write("Current number is :" + String.valueOf(currentNumber)+"\n");
//            writerImage.close();
//            Log.e("FilePath",getApplicationContext().getApplicationContext().getPackageName()+"/TestFile.txt");
//
//
//            if (!testFile.exists()){
//                testFile.createNewFile();
//                // Adds a line to the file
//                BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true /*append*/));
//                writer.write("This is a test file.");
//                writer.close();
//            }else{
//
//                BufferedWriter writer = new BufferedWriter(new FileWriter(testFile, true /*append*/));
//                writer.write("Current number is :" + String.valueOf(currentNumber)+"\n");
//                writer.close();
//                Log.e("FilePath",getApplicationContext().getApplicationContext().getPackageName()+"/TestFile.txt");
//            }
//            // Refresh the data so it can seen when the device is plugged in a
//            // computer. You may have to unplug and replug the device to see the
//            // latest changes. This is not necessary if the user should not modify
//            // the files.
//            MediaScannerConnection.scanFile(this,
//                    new String[]{testFile.toString()},
//                    null,
//                    null);
//            //String filename="contacts_sid.txt";
//            //File testFile1 = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
//            Uri path = Uri.fromFile(testFile);
//            Log.e("FilePath",path.toString());
//            Intent emailIntent = new Intent(Intent.ACTION_SEND);
//// set the type to 'email'
//            emailIntent .setType("vnd.android.cursor.dir/email");
//            String to[] = {"roshan82@gmail.com"};
//            emailIntent .putExtra(Intent.EXTRA_EMAIL, to);
//// the attachment
//            emailIntent .putExtra(Intent.EXTRA_STREAM, paths);
//// the mail subject
//            emailIntent .putExtra(Intent.EXTRA_SUBJECT, "Subject");
//            startActivity(Intent.createChooser(emailIntent , "Send email..."));
//        } catch (IOException e) {
//            Log.e("ReadWriteFile", "Unable to write to the file."+e);
//        }
//    }
//    void addToBundleAndOpenActivity(Class cls){
//        Intent intent = new Intent(HAMorseTutorial.this, cls);
//        Log.e("MainTimeunit", String.valueOf(timeunit));
//        Bundle bundle=new Bundle();
//        bundle.putInt("timeunit",timeunit);
//        bundle.putString("userName",user);
//        intent.putExtras(bundle);
//        startActivity(intent);
//        Log.e("SentBundle",String.valueOf(bundle));
//    }
//
//    void retrieveItemsFromBundle(){
//        if (bundle!=null) {
//            timeunit = bundle.getInt("timeunit");
//            user=bundle.getString("userName");
//        }
//    }
//}

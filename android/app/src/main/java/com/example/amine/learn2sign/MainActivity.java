package com.example.amine.learn2sign;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.facebook.stetho.Stetho;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    public static String INTENT_ID = "INTENT_ID";
    public static String INTENT_EMAIL = "INTENT_EMAIL";
    public static String INTENT_WORD = "INTENT_WORD";
    public static String INTENT_URI = "INTENT_URI";
    public static String INTENT_SERVER_ADDRESS = "INTENT_SERVER_ADDRESS";

    static final int REQUEST_VIDEO_CAPTURE = 1;
    LoggingUtil logger = null;
    //LoggingUtil logger = new LoggingUtil(this.getClass());

    @BindView(R.id.rg_practice_learn)
    RadioGroup rg_practice_learn;

    @BindView(R.id.rb_learn)
    RadioButton rb_learn;

    @BindView(R.id.rb_practice)
    RadioButton rb_practice;

    @BindView(R.id.rb_grade)
    RadioButton rb_grade;

    @BindView(R.id.sp_words)
    Spinner sp_words;

    @BindView(R.id.sp_ip_address)
    Spinner sp_ip_address;

    @BindView(R.id.vv_video_learn)
    VideoView vv_video_learn;

    @BindView(R.id.vv_record)
    VideoView vv_record;

    @BindView(R.id.bt_record)
    Button bt_record;

    @BindView(R.id.bt_reject)
    Button bt_reject;

    @BindView(R.id.bt_send)
    Button bt_send;

    @BindView(R.id.bt_proceed)
    Button bt_proceed;

    @BindView(R.id.bt_cancel)
    Button bt_cancel;


    @BindView(R.id.ll_after_record)
    LinearLayout ll_after_record;

    @BindView(R.id.ll_after_video)
    LinearLayout ll_after_video;

    @BindView(R.id.tv_practice_random_word)
    TextView tv_practice_random_word;

    @BindView(R.id.bt_record_practice)
    Button bt_record_practice;

    String path;
    String returnedURI;
    String old_text = "";
    SharedPreferences sharedPreferences;
    WordListActions wordListActions = new WordListActions();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind xml to activity
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        bt_proceed.setVisibility(View.GONE);
        rb_learn.setChecked(true);
        bt_cancel.setVisibility(View.GONE);
        bt_reject.setVisibility(View.GONE);
        bt_send.setVisibility(View.GONE);
        ll_after_video.setVisibility(View.GONE);
        tv_practice_random_word.setVisibility(View.GONE);
        bt_record_practice.setVisibility(View.GONE);

        logger = new LoggingUtil();

        try {
            this.checkWritePermissionForLogging();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rg_practice_learn.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId==rb_learn.getId()) {

                    logger.logClick("Radio Button",rb_learn.getText().toString());
                    Toast.makeText(getApplicationContext(),"Learn",Toast.LENGTH_SHORT).show();
                    vv_video_learn.setVisibility(View.VISIBLE);
                    sp_words.setVisibility(View.VISIBLE);
                    sp_ip_address.setVisibility(View.VISIBLE);
                    bt_reject.setVisibility(View.GONE);
                    ll_after_video.setVisibility(View.GONE);
                    bt_record.setVisibility(View.VISIBLE);
                    bt_cancel.setVisibility(View.GONE);
                    bt_send.setVisibility(View.GONE);


                    vv_record.setVisibility(View.GONE);
                    tv_practice_random_word.setVisibility(View.GONE);
                    bt_record_practice.setVisibility(View.GONE);
                    bt_proceed.setVisibility(View.GONE);
                }

                else if ( checkedId==rb_practice.getId()) {

                    logger.logClick("Radio Button",rb_practice.getText().toString());
                    //if(wordListActions.minimumNumberOfVideosCompleted()) {
                    if(1+1==2){

                        Toast.makeText(getApplicationContext(), "Practice", Toast.LENGTH_SHORT).show();
                        // Things that become invisible
                        vv_video_learn.setVisibility(View.GONE);
                        bt_proceed.setVisibility(View.GONE);
                        sp_words.setVisibility(View.GONE);
                        bt_cancel.setVisibility(View.GONE);
                        sp_ip_address.setVisibility(View.GONE);
                        ll_after_video.setVisibility(View.GONE);
                        bt_record.setVisibility(View.GONE);
                        ll_after_record.setVisibility(View.GONE);



                        tv_practice_random_word.setText(wordListActions.randomWord());
                        tv_practice_random_word.setVisibility(View.VISIBLE);
                        bt_record_practice.setVisibility(View.VISIBLE);

                    }
                    else{

                        rb_learn.setChecked(true);
                        rb_practice.setChecked(false);
                        Toast.makeText(getApplicationContext(),"Please complete 3 videos for each of 25 words before proceeding to practice",Toast.LENGTH_SHORT).show();
                        //vv_video_learn.setVisibility(View.VISIBLE);
                        //sp_words.setVisibility(View.VISIBLE);
                        //sp_ip_address.setVisibility(View.VISIBLE);
                        //ll_after_video.setVisibility(View.GONE);

                        //tv_practice_random_word.setVisibility(View.GONE);
                        //bt_record_practice.setVisibility(View.GONE);
                    }


                    // Things that become visible

                } else if (checkedId == rb_grade.getId()){


                    logger.logClick("Radio Button",rb_grade.getText().toString());
                    Toast.makeText(getApplicationContext(),"Grade",Toast.LENGTH_SHORT).show();
                    bt_proceed.setVisibility(View.GONE);
                    vv_video_learn.setVisibility(View.VISIBLE);

                    bt_cancel.setVisibility(View.GONE);
                    vv_record.setVisibility(View.VISIBLE);
                    sp_words.setVisibility(View.GONE);
                    sp_ip_address.setVisibility(View.GONE);
                    ll_after_video.setVisibility(View.VISIBLE);
                    tv_practice_random_word.setVisibility(View.GONE);
                    bt_record_practice.setVisibility(View.GONE);
                    bt_record.setVisibility(View.GONE);
                    bt_reject.setVisibility(View.VISIBLE);


                }
            }
        });

        sp_words.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String text = sp_words.getSelectedItem().toString();
                if(!old_text.equals(text)) {

                    logger.logClick("Word Spinner Element - Spinner Item - ",text);
                    path = "";
                    play_video(text);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sp_ip_address.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                logger.logClick("IP Address Spinner Element - Spinner Item - ",sp_ip_address.getSelectedItem().toString());
                sharedPreferences.edit().putString(INTENT_SERVER_ADDRESS, sp_ip_address.getSelectedItem().toString()).apply();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                if(mediaPlayer!=null)
                    mediaPlayer.start();
             }
        };
        vv_record.setOnCompletionListener(onCompletionListener);
        vv_video_learn.setOnCompletionListener(onCompletionListener);
        vv_record.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv_record.start();
            }
        });
        vv_video_learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vv_video_learn.start();
            }
        });
        sharedPreferences =  this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        Intent intent = getIntent();
        if(intent.hasExtra("INTENT_EMAIL") && intent.hasExtra("INTENT_ID")) {
            Toast.makeText(this,"User : " + intent.getStringExtra("INTENT_EMAIL"),Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this,"Already Logged In",Toast.LENGTH_SHORT).show();

        }
    }

    public void checkWritePermissionForLogging() throws IOException {

        if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                Log.i("Just Print",Environment.getExternalStorageDirectory().toString());
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }else{

            logger.getLogger(this.getClass());
        }
    }

    public void play_video(String text) {
        old_text = text;
        if(text.equals("About")) {
             path = "android.resource://" + getPackageName() + "/" + R.raw._about;
        } else if(text.equals("And")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._and;
        } else if (text.equals("Can")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._can;
        }else if (text.equals("Cat")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._cat;
        }else if (text.equals("Cop")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._cop;
        }else if (text.equals("Cost")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._cost;
        }else if (text.equals("Day")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._day;
        }else if (text.equals("Deaf")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._deaf;
        }else if (text.equals("Decide")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._decide;
        }else if (text.equals("Father")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._father;
        }else if (text.equals("Find")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._find;
        }else if (text.equals("Go Out")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._go_out;
        }else if (text.equals("Gold")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._gold;
        }else if (text.equals("Goodnight")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._good_night;
        }else if (text.equals("Hearing")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._hearing;
        }else if (text.equals("Here")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._here;
        }else if (text.equals("Hospital")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._hospital;
        }else if (text.equals("Hurt")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._hurt;
        }else if (text.equals("If")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._if;
        }else if (text.equals("Large")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._large;
        }else if (text.equals("Hello")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._hello;
        }else if (text.equals("Help")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._help;
        }else if (text.equals("Sorry")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._sorry;
        }else if (text.equals("After")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._after;
        }else if (text.equals("Tiger")) {
            path = "android.resource://" + getPackageName() + "/" + R.raw._tiger;
        }
        if(!path.isEmpty()) {
            Uri uri = Uri.parse(path);
            vv_video_learn.setVideoURI(uri);
            vv_video_learn.start();
        }

    }
    @OnClick(R.id.bt_record)
    public void record_video() {
        logger.logClick("Button",bt_record.getText().toString());
        bt_cancel.setText("CANCEL");
        bt_send.setText("PROCEED");
        bt_send.setVisibility(View.VISIBLE);
        vv_video_learn.setVisibility(View.GONE);


         if( ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {

             // Permission is not granted
             // Should we show an explanation?

             if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                     Manifest.permission.CAMERA)) {
                 // Show an explanation to the user *asynchronously* -- don't block
                 // this thread waiting for the user's response! After the user
                 // sees the explanation, try again to request the permission.
             } else {
                 // No explanation needed; request the permission
                 ActivityCompat.requestPermissions(this,
                         new String[]{Manifest.permission.CAMERA},
                         101);

                 // constant. The callback method gets the
                 // result of the requeMY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                 //                 // app-defined int st.
             }
         }


         if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            // Permission has already been granted

             File f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign");
             if (!f.exists()) {
                 f.mkdirs();
             }
             Intent t = new Intent(this,VideoActivity.class);
             t.putExtra("recordButtonId","bt_record");
             t.putExtra(INTENT_WORD,sp_words.getSelectedItem().toString());
             //wordListActions.addWord(sp_words.getSelectedItem().toString());
             //wordListActions.setMinimumNumberOfVideosCompletedToTrue();
             startActivityForResult(t,9999);





 /*           File m = new File(Environment.getExternalStorageDirectory().getPath() + "/Learn2Sign");
            if(!m.exists()) {
                if(m.mkdir()) {
                    Toast.makeText(this,"Directory Created",Toast.LENGTH_SHORT).show();
                }
            }

            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            takeVideoIntent.putExtra(EXTRA_DURATION_LIMIT, 10);

            if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            }*/
        }
    }
    @OnClick(R.id.bt_proceed)
    public void goToGrade(){
        logger.logClick("Button",bt_proceed.getText().toString());
        Toast.makeText(getApplicationContext(),"Grade",Toast.LENGTH_SHORT).show();
        bt_proceed.setVisibility(View.GONE);
        bt_send.setText("ACCEPT");
        bt_send.setVisibility(View.VISIBLE);

        sp_words.setVisibility(View.GONE);
        sp_ip_address.setVisibility(View.GONE);
        ll_after_video.setVisibility(View.VISIBLE);
        tv_practice_random_word.setVisibility(View.GONE);

        vv_record.setVisibility(View.GONE);
        play_video(tv_practice_random_word.getText().toString());
        vv_video_learn.setVisibility(View.GONE);
        bt_record_practice.setVisibility(View.GONE);
        bt_record.setVisibility(View.GONE);
        //rb_grade.setSelected(true);
        rb_grade.performClick();

    }

    @OnClick(R.id.bt_record_practice)
    public void record_practice_video() {

        logger.logClick("Button",bt_record_practice.getText().toString());
        bt_send.setText("PROCEED");
        bt_cancel.setText("CANCEL");
        ll_after_record.setVisibility(View.VISIBLE);



        if( ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.CAMERA)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA},
                        101);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


        if ( ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED ) {

            // Permission is not granted
            // Should we show an explanation?


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
            } else {
                // No explanation needed; request the permission
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        100);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }

        } else {
            // Permission has already been granted
            File f = new File(Environment.getExternalStorageDirectory(), "Learn2Sign");
            if (!f.exists()) {
                f.mkdirs();
            }
            Intent t = new Intent(this,VideoActivity.class);
            t.putExtra("recordButtonId","bt_record_practice");

            t.putExtra(INTENT_WORD,tv_practice_random_word.getText());
            startActivityForResult(t,9999);

 /*           File m = new File(Environment.getExternalStorageDirectory().getPath() + "/Learn2Sign");
            if(!m.exists()) {
                if(m.mkdir()) {
                    Toast.makeText(this,"Directory Created",Toast.LENGTH_SHORT).show();
                }
            }

            Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            takeVideoIntent.putExtra(EXTRA_DURATION_LIMIT, 10);

            if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
            }*/
        }
    }
    @OnClick(R.id.bt_send)
    public void sendToServer() {
        logger.logClick("Button",bt_send.getText().toString());
        Toast.makeText(this,"Send to Server",Toast.LENGTH_SHORT).show();
        Intent t = new Intent(this,UploadActivity.class);
        startActivity(t);
    }

    @OnClick(R.id.bt_cancel)
    public void cancel() {
        logger.logClick("Button",bt_cancel.getText().toString());
        vv_record.setVisibility(View.GONE);
        if(rb_learn.isSelected()) {
            vv_video_learn.setVisibility(View.VISIBLE);
        }
        bt_record.setVisibility(View.GONE);
        bt_send.setVisibility(View.GONE);
        bt_cancel.setVisibility(View.GONE);
        bt_record_practice.setVisibility(View.VISIBLE);
        tv_practice_random_word.setVisibility(View.VISIBLE);
        sp_words.setEnabled(true);
        bt_proceed.setVisibility(View.GONE);
        rb_learn.setEnabled(true);
        rb_practice.setEnabled(true);
        rb_grade.setEnabled(true);

    }


    @OnClick(R.id.bt_reject)
    public void reject() {
        logger.logClick("Button",bt_reject.getText().toString());
        vv_record.setVisibility(View.GONE);
        if(rb_learn.isSelected()) {
            vv_video_learn.setVisibility(View.VISIBLE);
        }
       /* bt_record.setVisibility(View.GONE);
        bt_send.setVisibility(View.GONE);
        bt_cancel.setVisibility(View.GONE);
        bt_record_practice.setVisibility(View.VISIBLE);
        tv_practice_random_word.setVisibility(View.VISIBLE);
        sp_words.setEnabled(true);
        bt_proceed.setVisibility(View.GONE);
        rb_learn.setEnabled(true);
        rb_practice.setEnabled(true);
        rb_grade.setEnabled(true); */
       rb_practice.performClick();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

    Log.e("OnActivityresult",requestCode+" "+resultCode);
        if(requestCode==9999 && resultCode == 8888) {
            if(intent.hasExtra(INTENT_URI)) {
                returnedURI = intent.getStringExtra(INTENT_URI);
                bt_proceed.setVisibility(View.VISIBLE);
                vv_record.setVisibility(View.VISIBLE);
                bt_record.setVisibility(View.GONE);
                bt_record_practice.setVisibility(View.GONE);
                tv_practice_random_word.setVisibility(View.GONE);
                bt_send.setVisibility(View.GONE);
                bt_cancel.setVisibility(View.VISIBLE);
                sp_words.setEnabled(false);
                rb_learn.setEnabled(true);
                rb_practice.setEnabled(true);
                rb_grade.setEnabled(true);
                bt_reject.setVisibility(View.GONE);
                vv_record.setVideoURI(Uri.parse(returnedURI));
                sharedPreferences.edit().putInt("recorded_"+sp_words.getSelectedItem().toString(), sharedPreferences.getInt("recorded_"+sp_words.getSelectedItem().toString(),0)+1).apply();

            }

        }

        if(requestCode==9999 && resultCode==7777)
        {
            if(intent!=null) {
                //create folder
                if (intent.hasExtra(INTENT_URI)) {
                    returnedURI = intent.getStringExtra(INTENT_URI);
                    File f = new File(returnedURI);
                    f.delete();
                    sharedPreferences.edit().putInt("canceled_"+sp_words.getSelectedItem().toString(), sharedPreferences.getInt("canceled_"+sp_words.getSelectedItem().toString(),0)+1).apply();

                }
            }

        }

        /*if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            final Uri videoUri = intent.getData();


            vv_record.setVisibility(View.VISIBLE);
            vv_record.setVideoURI(videoUri);
            vv_record.start();
            play_video(sp_words.getSelectedItem().toString());
            bt_record.setVisibility(View.GONE);
            int i=0;
            File n = new File(Environment.getExternalStorageDirectory().getPath() + "/Learn2Sign/"
                    + sharedPreferences.getString(INTENT_ID,"0000")+"_"+sp_words.getSelectedItem().toString()+"_0" + ".mp4");
            while(n.exists()) {
                i++;
                n = new File(Environment.getExternalStorageDirectory().getPath() + "/Learn2Sign/"
                        + sharedPreferences.getString(INTENT_ID,"0000")+"_"+sp_words.getSelectedItem().toString()+"_"+i + ".mp4");
            }
            SaveFile saveFile = new SaveFile();
            saveFile.execute(n.getPath(),videoUri.toString());

            bt_send.setVisibility(View.VISIBLE);
            bt_cancel.setVisibility(View.VISIBLE);

            sp_words.setEnabled(false);
            rb_learn.setEnabled(false);
            rb_practice.setEnabled(false);
        }*/
    }

    //Menu Item for logging out
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.menu_logout:
                if(sharedPreferences.edit().remove("INTENT_ID").commit() && sharedPreferences.edit().remove("INTENT_EMAIL").commit()) {
                    sharedPreferences.edit().putInt(getString(R.string.logout), sharedPreferences.getInt(getString(R.string.logout),0)+1).apply();
                    startActivity(new Intent(this, LoginActivity.class));
                    this.finish();
                    return true;
                }
            case R.id.menu_upload_server:
                sharedPreferences.edit().putInt(getString(R.string.gotoupload), sharedPreferences.getInt(getString(R.string.gotoupload),0)+1).apply();
                Intent t = new Intent(this,UploadActivity.class);
                startActivity(t);

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public class SaveFile extends AsyncTask<String, String, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            FileOutputStream fileOutputStream = null;
            FileInputStream fileInputStream = null;
            try {
                fileOutputStream = new FileOutputStream(strings[0]);
                fileInputStream = (FileInputStream) getContentResolver().openInputStream(Uri.parse(strings[1]));
                Log.d("msg", fileInputStream.available() + " ");
                byte[] buffer = new byte[1024];
                while (fileInputStream.available() > 0) {

                    fileInputStream.read(buffer);
                    fileOutputStream.write(buffer);
                    publishProgress(fileInputStream.available()+"");
                }

                fileInputStream.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(String... values) {

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            Toast.makeText(getApplicationContext(),"Video Saved Successfully",Toast.LENGTH_SHORT).show();
        }
    }
}

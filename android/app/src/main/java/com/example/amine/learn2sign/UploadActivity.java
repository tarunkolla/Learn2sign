package com.example.amine.learn2sign;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;


//import static com.example.amine.learn2sign.LoginActivity.INTENT_ID;
//import static com.example.amine.learn2sign.LoginActivity.INTENT_SERVER_ADDRESS;

public class UploadActivity extends AppCompatActivity {

    public static String INTENT_ID = "INTENT_ID";
    public static String INTENT_EMAIL = "INTENT_EMAIL";
    public static String INTENT_WORD = "INTENT_WORD";
    public static String INTENT_URI = "INTENT_URI";
    public static String INTENT_SERVER_ADDRESS = "INTENT_SERVER_ADDRESS";
    @BindView(R.id.rv_videos)
    RecyclerView rv_videos;
    @BindView(R.id.tv_filename)
    TextView tv_filename;
    @BindView(R.id.pb_progress)
    ProgressBar progressBar;
    UploadListAdapter uploadListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);
        ButterKnife.bind(this);

        rv_videos.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));


        File m = new File(Environment.getExternalStorageDirectory().getPath() + "/Learn2Sign");
        if(m.exists()) {
            if(m.isDirectory()) {
                File[] videos =  m.listFiles();
                try {
                    for (int i = 0; i < videos.length; i++) {
                        String path = videos[i].getPath();
                        Log.d("msg", path);
                    }
                }
                catch(NullPointerException e){

                }
            }
        }
        uploadListAdapter = new UploadListAdapter(m.listFiles(), this.getApplicationContext());
        rv_videos.setAdapter(uploadListAdapter);


    }
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_upload, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        String id = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE).getString("INTENT_ID","00000000");

        //respond to menu item selection
        switch (item.getItemId()) {
            case R.id.menu_upload:
                String server_ip = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE).getString(INTENT_SERVER_ADDRESS, "10.211.17.171");
                //upload to Server now
                File[] toUpload = uploadListAdapter.getVideos();
                boolean[] checked = uploadListAdapter.getChecked();
                if(uploadListAdapter.minimumConditionAchieved()) {

                    Log.d("msg", server_ip);
                    for (int i = 0; i < checked.length; i++) {
                        RequestParams params = new RequestParams();
                        if (checked[i]) {
                            params.put("checked", 1);
                        } else {
                            params.put("checked", 0);
                        }
                        try {
                            params.put("uploaded_file", toUpload[i]);
                            params.put("id", id);

                        } catch (FileNotFoundException e) {
                        }


                        // send request
                        AsyncHttpClient client = new AsyncHttpClient();

                        client.post("http://" + server_ip + "/upload_video.php", params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                                // handle success response
                                Log.e("msg success", statusCode + "");
                                if (statusCode == 200)
                                    Toast.makeText(UploadActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                else
                                    Toast.makeText(UploadActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                                // handle failure response
                                Log.e("msg fail", statusCode + "");

                                Toast.makeText(UploadActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onProgress(long bytesWritten, long totalSize) {
                                tv_filename.setText(bytesWritten + " out of " + totalSize);

                                super.onProgress(bytesWritten, totalSize);
                            }


                            @Override
                            public void onStart() {
                                tv_filename.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.VISIBLE);
                                super.onStart();
                            }

                            @Override
                            public void onFinish() {

                                tv_filename.setVisibility(View.GONE);
                                progressBar.setVisibility(View.GONE);
                                super.onFinish();
                            }
                        });

                        /*
                        UploadFile uploadFile = new UploadFile();
                        uploadFile.execute(uploadListAdapter.getVideos()[i].getPath());*/

                    }
                    Toast.makeText(this, "Upload to Server", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(this, "Please select 3 videos for each of 25 signs", Toast.LENGTH_LONG).show();
                }

                File n = new File(getFilesDir().getPath());
                File f = new File(n.getParent()+"/shared_prefs/" + getPackageName() +".xml");
                AsyncHttpClient client_logs = new AsyncHttpClient();
                RequestParams params = new RequestParams();
                try {
                    params.put("uploaded_file",f);
                    params.put("id",id);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                client_logs.post("http://"+server_ip+"/upload_log_file.php", params, new AsyncHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                        if(statusCode==200)
                            Toast.makeText(UploadActivity.this, "Done", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(UploadActivity.this, "Log File could not be uploaded", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                        Toast.makeText(UploadActivity.this, "Log File could not be uploaded", Toast.LENGTH_SHORT).show();

                    }
                });



            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


package com.example.amine.learn2sign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {



    public static String INTENT_ID = "INTENT_ID";
    public static String INTENT_EMAIL = "INTENT_EMAIL";
    public static String INTENT_WORD = "INTENT_WORD";
    public static String INTENT_URI = "INTENT_URI";
    public static String INTENT_SERVER_ADDRESS = "INTENT_SERVER_ADDRESS";

    @BindView(R.id.et_email)
    EditText et_email;

    @BindView(R.id.et_id)
    EditText et_id;

    @BindView(R.id.bt_login)
    Button bt_login;

    String email;
    String id;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        if(sharedPreferences.contains(INTENT_ID) && sharedPreferences.contains(INTENT_EMAIL)) {
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
            this.finish();

        }
    }

    @OnClick(R.id.bt_login)
    public void login() {
        if(et_email.getText().toString().isEmpty() || et_id.getText().toString().isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("ALERT");
            alertDialog.setMessage("Please Enter Login Information!");
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else {
            email = et_email.getText().toString();
            id = et_id.getText().toString();

            Intent intent = new Intent(this,MainActivity.class);
            intent.putExtra(INTENT_EMAIL,email);
            intent.putExtra(INTENT_ID,id);

            if(sharedPreferences.edit().putString(INTENT_EMAIL,email).commit() &&
            sharedPreferences.edit().putString(INTENT_ID,id).commit()) {

                sharedPreferences.edit().putInt(getString(R.string.login), sharedPreferences.getInt(getString(R.string.login),0)+1).apply();
                startActivity(intent);
                this.finish();

            }


        }
    }

}

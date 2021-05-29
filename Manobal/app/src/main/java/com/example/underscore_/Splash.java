package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

public class Splash extends AppCompatActivity {

    private static final String PREFERENCE ="data" ;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        sharedPreferences=getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        new Handler().postDelayed(() -> checkData(),2000);
    }
    public void checkData()
    {
        boolean flag;
        String nickName,gender;
        nickName=sharedPreferences.getString("nickName","");
        gender=sharedPreferences.getString("gender","");
        if(nickName.equals("")&&gender.equals(""))
        {
            flag=true;
        }
        else
        {
            flag=false;
        }
        setIntent(flag);
    }
    public void setIntent(boolean flag)
    {
        Intent intent;
        if(flag) {
            intent = new Intent(Splash.this, StartActivity.class);
        }
        else
        {
            intent = new Intent(Splash.this, MainActivity.class);
        }
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
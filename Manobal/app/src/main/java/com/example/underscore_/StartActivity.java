package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    public static final String PREFERENCE ="data";
    ImageButton btnGo;
    EditText etNickname,etGender;
    TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        btnGo = findViewById(R.id.btnGo);
        etNickname = findViewById(R.id.etNickname);
        etGender = findViewById(R.id.etGender);
        tvSkip = findViewById(R.id.tvSkip);
        btnGo.setOnClickListener(v -> {
            hideKeyboard(v);
            String nickName, gender;
            nickName = etNickname.getText().toString().trim();
            gender = etGender.getText().toString().trim();
            if(TextUtils.isEmpty(nickName)||TextUtils.isEmpty(gender))
            {
                Toast.makeText(this, "Fields can't be empty!\nUse Skip to login without entering your details", Toast.LENGTH_LONG).show();
            }
            else {
                setData(nickName,gender);
            }
        });
        String skip=tvSkip.getText().toString();
        Spannable spannable=new SpannableString(skip);
        spannable.setSpan(new UnderlineSpan(),0,skip.length(),0);
        tvSkip.setText(spannable);
        tvSkip.setOnClickListener(v ->{
            setData("Anonymous","NA");
        });
    }

    public void setData(String nickName, String gender) {
        SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("nickName",nickName);
        editor.putString("gender",gender);
        editor.apply();
        setIntent();
    }
    public void setIntent()
    {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    public void hideKeyboard(View view){
        InputMethodManager inputMethodManager=(InputMethodManager) this.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(),0);
    }
}
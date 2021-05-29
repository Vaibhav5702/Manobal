package com.example.underscore_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.UnderlineSpan;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SosActivity extends AppCompatActivity {

    public static final String PREFERENCE ="data" ;
    private static final int REQUEST_CODE_SEND_SMS =1 ;
    TextView sosText;
    ImageButton imgSave;
    EditText etNumber;
    String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);
        sosText=findViewById(R.id.sosText);
        imgSave=findViewById(R.id.imgSave);
        etNumber=findViewById(R.id.etNumber);
        imgSave.setOnClickListener(v->
        {
            number=etNumber.getText().toString().trim();
            saveNum();
        });
        String learn=sosText.getText().toString();
        Spannable spannable=new SpannableString(learn);
        spannable.setSpan(new UnderlineSpan(),0,learn.length(),0);
        sosText.setText(spannable);
        sosText.setOnClickListener(v ->{
            AlertDialog dialog=new AlertDialog.Builder(this)
                    .setTitle("SOS")
                    .setMessage("Use this SOS Button at times when you do not feel well, are anxious or just feeling tensed to talk to a person " +
                            "whom you think can help you. Add a mobile number in the text" +
                            " field below so that the next time you click on the SOS button an auto-generated text" +
                            " would be sent to that person to call or talk to you.")
                    .setPositiveButton("Okay", (dialog1, which) -> dialog1.dismiss()).show();
        });
    }

    private void saveNum() {
        if(TextUtils.isEmpty(number))
        {
            Toast.makeText(this, "Please enter a Mobile Number", Toast.LENGTH_SHORT).show();
        }
        else
        {
            permissionReq();
        }
    }
    private void permissionReq() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Permission is NOT granted
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                new AlertDialog.Builder(this)
                        .setTitle("Need Permissions")
                        .setMessage("Send message permission is needed to send the SMS")
                        .setCancelable(false)
                        .setPositiveButton("Ok", (dialog, which) -> requestPermissions(new String[]{Manifest.permission.SEND_SMS}, 1))
                        .show();
            } else {
                ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.SEND_SMS}, REQUEST_CODE_SEND_SMS);
            }

        } else {
            // Permission is Granted
            SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("mobile",number);
            editor.apply();
            finish();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CODE_SEND_SMS) {

            if (grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Permission Granted
                SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString("mobile",number);
                editor.apply();
                finish();
            } else {
                //Permission NOT granted
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.SEND_SMS)) {
                    //This block here means PERMANENTLY DENIED PERMISSION
                    new AlertDialog.Builder(this)
                            .setMessage("You have permanently denied this permission, go to settings to enable this permission")
                            .setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    gotoApplicationSettings();
                                }
                            })
                            .setNegativeButton("Cancel", null)
                            .setCancelable(false)
                            .show();



                } else {
                    //
                    Toast.makeText(this, "Cannot save the number as permission denied", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    private void gotoApplicationSettings() {

        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.getPackageName(), null);
        intent.setData(uri);
        startActivity(intent);

    }
}
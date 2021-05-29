package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;


import android.os.Bundle;
import android.telephony.SmsManager;


import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements CardAdapter.ItemClick {

    private static final String PREFERENCE = "data";
    List<Card> arrayList =new ArrayList<>();
    RecyclerView recyclerView;
    CardAdapter cardAdapter;
    RecyclerView.LayoutManager layoutManager;
    String[] headings;
    Random random;
    TextView tvRandom;
    Button sos;
    ImageButton dots_btn;
    TextView heyText;
    CircleImageView btnGo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGo=findViewById(R.id.btnGo);
        heyText=findViewById(R.id.heyText);
        tvRandom=findViewById(R.id.tvRandom);
        sos=findViewById(R.id.sos_btn);
        dots_btn=findViewById(R.id.dots_btn);
        headings=getResources().getStringArray(R.array.headings);
        random=new Random();
        int index=random.nextInt(headings.length);

        tvRandom.setText(headings[index]);

        SharedPreferences preferences=getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
        String nickname=preferences.getString("nickName","");

        if (!nickname.equals("Anonymous")){
            heyText.setText("Hey "+ nickname+ ",\nCheer Up!");
        }

        arrayList.add(0,new Card("sleep"));
        arrayList.add(1,new Card("meditate"));
        arrayList.add(2,new Card("anxiety"));
        arrayList.add(3,new Card("heartbreak"));
        arrayList.add(4,new Card("exam_stress"));
        arrayList.add(5,new Card("lonely"));
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);

        btnGo.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, UnityPlayerActivity.class)));

        layoutManager=new GridLayoutManager(this,2,RecyclerView.VERTICAL,false){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(layoutManager);
        cardAdapter=new CardAdapter(this,arrayList);
        recyclerView.setAdapter(cardAdapter);
        sos.setOnClickListener(v -> callSos());

        dots_btn.setOnClickListener(v -> {
            BottomSheetDialog bottomSheetDialog=new BottomSheetDialog(MainActivity.this,R.style.BottomSheetDialogTheme);
            View bottomSheetView= LayoutInflater.from(getApplicationContext()).inflate(R.layout.bottom_sheet,
                                                        findViewById(R.id.bottomSheetContainer));
            bottomSheetView.findViewById(R.id.edit_nickname).setOnClickListener(v1 -> {
                AlertDialog dialog=new AlertDialog.Builder(MainActivity.this).setView(R.layout.nickname_alert)
                        .setPositiveButton("Confirm", (dialog1, which) -> {
                            EditText editText=findViewById(R.id.nick_et);
                            String nick=editText.getText().toString().trim();
                            if (TextUtils.isEmpty(nick))
                                Toast.makeText(MainActivity.this, "Please Enter a nickname", Toast.LENGTH_SHORT).show();
                            else {
                                SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE,Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString("nickName",nick);
                                editor.apply();
                            }
                        }).setNegativeButton("Cancel",null)
                        .show();
                dialog.getWindow().setBackgroundDrawableResource(R.color.alert_dialog);

                bottomSheetDialog.dismiss();
            });

            bottomSheetView.findViewById(R.id.edit_sos).setOnClickListener(v1 -> {
                Intent intent=new Intent(MainActivity.this,SosActivity.class);
                startActivity(intent);
                bottomSheetDialog.dismiss();
            });
            bottomSheetDialog.setContentView(bottomSheetView);
            bottomSheetDialog.show();
        });
    }

    private void callSos() {
        SharedPreferences sharedPreferences=getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        String number=sharedPreferences.getString("mobile","");
        if(number.equals(""))
        {
            Intent intent=new Intent(MainActivity.this,SosActivity.class);
            startActivity(intent);
        }
        else
        {

            Intent intent=new Intent();
            PendingIntent pi=PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

            SmsManager sms= SmsManager.getDefault();
            sms.sendTextMessage(number, null, "Your friend needs your help, please call or talk to your friend by some means.", pi,null);

            Toast.makeText(this, "Sent Successfully", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onItemClick(int i) {
        switch (i){
            case 0:
                startActivity(new Intent(MainActivity.this,SleepActivity.class));
                break;

            case 1:
                startActivity(new Intent(MainActivity.this,Meditation.class));
                break;

            case 2:

            case 3:

            case 4:

            case 5:
                break;

            default:

        }
    }
}
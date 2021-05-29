package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import java.util.ArrayList;

public class Meditation extends AppCompatActivity {

    CardView songCard,exerciseCard,shortMeditation,longMeditation;
    ArrayList<String> song,exercise;
    ConstraintLayout chooseLayout,chooseMeditation;
    Animation moveToLeft,moveToRight,backToScreen,moveAway;
    public static final int RQCODE=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meditation);
        songCard=findViewById(R.id.songCard);
        exerciseCard=findViewById(R.id.exerciseCard);
        shortMeditation=findViewById(R.id.shortMeditation);
        longMeditation=findViewById(R.id.longMeditation);
        chooseLayout=findViewById(R.id.chooseLayout);
        chooseMeditation=findViewById(R.id.chooseMeditation);

        chooseMeditation.setVisibility(View.GONE);


        createAnim();

        chooseLayout.startAnimation(moveToLeft);

        song=new ArrayList();
        song.add("https://underscore.file.core.windows.net/songs/meditation_song1.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        song.add("https://underscore.file.core.windows.net/songs/meditaion_song2.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        song.add("https://underscore.file.core.windows.net/songs/mediation_song3.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");

        songCard.setOnClickListener(v ->{
            playSong("song");
        } );
        exerciseCard.setOnClickListener(v ->{
            playSong("exercise");
        });
    }

    public void playSong(String choice){
        Intent intent=new Intent(Meditation.this,SongMeditation.class);
        if(choice.equals("song")) {
            intent.putExtra("music", song);
            startActivityForResult(intent,RQCODE);
        }
        else{
            chooseLayout.startAnimation(moveAway);
            chooseMeditation.setVisibility(View.VISIBLE);
            chooseMeditation.startAnimation(moveToLeft);

            shortMeditation.setOnClickListener(v -> {
                exercise=new ArrayList<>();
                exercise.add("https://underscore.file.core.windows.net/songs/meditation_short.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
                intent.putExtra("music",exercise);
                startActivityForResult(intent,RQCODE);
            });

            longMeditation.setOnClickListener(v -> {
                exercise=new ArrayList<>();
                exercise.add("https://underscore.file.core.windows.net/songs/long_meditation.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
                intent.putExtra("music",exercise);
                startActivityForResult(intent,RQCODE);
            });
        }
    }

    @Override
    public void onBackPressed() {
        if (chooseMeditation.getVisibility()==View.VISIBLE) {
            chooseMeditation.setVisibility(View.GONE);
            chooseLayout.startAnimation(backToScreen);
            chooseMeditation.startAnimation(moveToRight);
        }
        else
            super.onBackPressed();
    }


    public void createAnim(){
        moveToLeft= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_to_left);
        moveToRight= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_to_right);
        backToScreen= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.back_to_screen);
        moveAway= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_away);
    }
}
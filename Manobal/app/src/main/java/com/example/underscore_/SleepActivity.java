package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class SleepActivity extends AppCompatActivity {

    CardView songCard,visualizationCard,exerciseCard;
    ArrayList<String> song,exercise,visualization;
    public static final int RQCODE=1;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        songCard=findViewById(R.id.songCard);
        exerciseCard=findViewById(R.id.exerciseCard);
        visualizationCard=findViewById(R.id.visualizationCard);
        animation= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_to_left);
        songCard.startAnimation(animation);
        exerciseCard.startAnimation(animation);
        visualizationCard.startAnimation(animation);

        song=new ArrayList();
        song.add("https://underscore.file.core.windows.net/songs/sleep_song1.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        song.add("https://underscore.file.core.windows.net/songs/sleep_song2.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        song.add("https://underscore.file.core.windows.net/songs/sleep_song3.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        song.add("https://underscore.file.core.windows.net/songs/sleep_song4.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        exercise=new ArrayList<>();
        exercise.add("https://underscore.file.core.windows.net/songs/exercise1sleep.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        visualization=new ArrayList<>();
        visualization.add("https://underscore.file.core.windows.net/songs/sleep_visualization.mp3?sv=2020-02-10&ss=bfqt&srt=o&sp=rx&se=2021-06-16T02:57:30Z&st=2021-05-15T18:57:30Z&spr=https,http&sig=QMqfjJ9twN3UDKaNeG0vFfqeYaGNFBWDRIq0qIgiQ%2FQ%3D");
        songCard.setOnClickListener(v ->{
            playSong("song");
        } );

        visualizationCard.setOnClickListener(v -> {
            playSong("visualization");
        });

        exerciseCard.setOnClickListener(v ->{
            playSong("exercise");
        });
    }

    public void playSong(String choice){
        Intent intent=new Intent(SleepActivity.this,SongSleepActivity.class);
        if(choice.equals("song"))
            intent.putExtra("music",song);
        else if(choice.equals("exercise"))
            intent.putExtra("music",exercise);
        else
            intent.putExtra("music",visualization);
        startActivityForResult(intent,RQCODE);
    }


}
package com.example.underscore_;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import pl.droidsonroids.gif.GifImageView;

public class SongSleepActivity extends AppCompatActivity {

    ImageButton play_pause;
    TextView tvRelax, tvResources;
    ImageView sleepingIcon;
    MediaPlayer mediaPlayer;
    boolean pause = false;
    GifImageView songProgress;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_sleep);
        play_pause = findViewById(R.id.play_pause);
        songProgress = findViewById(R.id.songProgress);
        tvRelax = findViewById(R.id.tvRelax);
        sleepingIcon = findViewById(R.id.sleepingIcon);
        tvResources = findViewById(R.id.tvResources);
        mediaPlayer = new MediaPlayer();
        playSong();
        play_pause.setOnClickListener(v -> {
            if (pause) {
                pause = false;
                play_pause.setImageResource(R.drawable.pause_button);
                mediaPlayer.start();
            } else {
                pause = true;
                play_pause.setImageResource(R.drawable.play_button);
                mediaPlayer.pause();
            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        play_pause.setImageResource(R.drawable.play_button);
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            flag=1;
        }
        else {
            mediaPlayer.reset();
            flag=-1;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        play_pause.setImageResource(R.drawable.pause_button);
        if (flag==1)
            mediaPlayer.start();
        else if (flag==-1){
            playSong();
        }
    }

    @Override
    public void onBackPressed() {
        mediaPlayer.stop();
        mediaPlayer.reset();
        super.onBackPressed();
    }

    public void playSong() {
        Intent intent = getIntent();
        ArrayList<String> music = intent.getStringArrayListExtra("music");
        Random random = new Random();
        int num = random.nextInt(music.size());
        try {
            mediaPlayer.setDataSource(music.get(num));
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(mp ->
            {
                songProgress.setVisibility(View.GONE);
                tvRelax.setVisibility(View.VISIBLE);
                sleepingIcon.setVisibility(View.VISIBLE);
                play_pause.setVisibility(View.VISIBLE);
                tvResources.setVisibility(View.GONE);
                mp.start();
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                        setResult(2);
                        SongSleepActivity.this.finish();
                    }
                });
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
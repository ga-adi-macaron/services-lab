package com.justinwells.myapplication;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {
    ImageButton play,pause;
    Button stop;



    public static final String PLAY_MUSIC = "play";
    public static final String PAUSE_MUSIC = "pause";
    public static final String STOP_MUSIC = "stop";
    public static final String KEY = "Im_a_key";

    boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        isPlaying = false;

        play = (ImageButton)findViewById(R.id.play_button);
        play.setOnClickListener(listener);

        stop = (Button)findViewById(R.id.stop_button);
        stop.setOnClickListener(listener);

        pause = (ImageButton)findViewById(R.id.pause_button);
        pause.setOnClickListener(listener);


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.play_button:
                    if (!isPlaying) {
                        Intent intent = new Intent(MainActivity.this, MyMediaService.class);
                        intent.putExtra(KEY, PLAY_MUSIC);
                        startService(intent);
                        isPlaying = true;
                    }
                    break;
                case R.id.pause_button:
                    if (isPlaying) {
                        Intent intent = new Intent(MainActivity.this, MyMediaService.class);
                        intent.putExtra(KEY, PAUSE_MUSIC);
                        startService(intent);
                        isPlaying = false;
                    }
                    break;
                case R.id.stop_button:
                    Intent intent = new Intent(MainActivity.this, MyMediaService.class);
                    intent.putExtra(KEY, STOP_MUSIC);
                    startService(intent);
                    isPlaying = false;
                    break;
            }
        }
    };
}

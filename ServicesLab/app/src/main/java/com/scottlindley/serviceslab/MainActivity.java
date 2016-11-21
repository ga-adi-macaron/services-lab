package com.scottlindley.serviceslab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    public static final String INTENT_ACTION_KEY = "action";
    public static final String PLAY_INTENT_VALUE = "play";
    public static final String PAUSE_INTENT_VALUE = "pause";
    public static final String STOP_INTENT_VALUE = "stop";

    private ImageView mSongImage, mPlayPause, mStop;
    private boolean isPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpViews();
    }

    public void setUpViews(){
        mSongImage = (ImageView)findViewById(R.id.song_image);
        mPlayPause = (ImageView)findViewById(R.id.play_pause);
        mStop = (ImageView)findViewById(R.id.stop);

        mPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPlaying){
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    intent.putExtra(INTENT_ACTION_KEY, PLAY_INTENT_VALUE);
                    startService(intent);
                    mPlayPause.setImageResource(R.drawable.ic_pause_black_24dp);
                    isPlaying = true;
                }else{
                    Intent intent = new Intent(MainActivity.this, MusicService.class);
                    intent.putExtra(INTENT_ACTION_KEY, PAUSE_INTENT_VALUE);
                    mPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                    startService(intent);
                    isPlaying = false;
                }
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(INTENT_ACTION_KEY, STOP_INTENT_VALUE);
                mPlayPause.setImageResource(R.drawable.ic_play_arrow_black_24dp);
                startService(intent);
                isPlaying = false;
            }
        });
    }
}

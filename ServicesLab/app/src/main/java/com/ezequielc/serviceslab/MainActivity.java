package com.ezequielc.serviceslab;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView mPlay, mStop;
    private boolean mIsPlaying;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicService();
    }

    private void musicService(){
        mPlay = (ImageView) findViewById(R.id.play);
        mStop = (ImageView) findViewById(R.id.stop);

        mPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                startService(intent);
                if (!mIsPlaying) {
                    mPlay.setImageResource(android.R.drawable.ic_media_pause);
                    mIsPlaying = true;
                } else {
                    mPlay.setImageResource(android.R.drawable.ic_media_play);
                    mIsPlaying = false;
                }
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                stopService(intent);
            }
        });
    }
}

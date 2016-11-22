package com.colinbradley.musicplayerapp;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button mPlay, mPause, mStop;
    public static final String PLAY = "play";
    public static final String PAUSE = "pause";
    public static final String STOP = "stop";
    public static final String ACTION = "action";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlay = (Button)findViewById(R.id.playbutton);
        mPause = (Button)findViewById(R.id.pause);
        mStop = (Button)findViewById(R.id.stopbutton);


        //String url = "https://soundcloud.com/user-900020571/edc-orlando-promomix";

        //Intent intent = new Intent(MainActivity.this, MyService.class);
        //intent.putExtra("url", url);



            mPlay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra(ACTION, PLAY);
                    startService(intent);
                }
            });

            mPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra(ACTION, PAUSE);
                    startService(intent);
                }
            });

            mStop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, MyService.class);
                    intent.putExtra(ACTION, STOP);
                    startService(intent);
                }
            });


    }
}

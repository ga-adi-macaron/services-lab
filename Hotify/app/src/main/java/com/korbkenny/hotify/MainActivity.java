package com.korbkenny.hotify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView mPlayPause, mStop;
    public static String PLAY = "PLAY";
    public static String PAUSE = "PAUSE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayPause = (TextView) findViewById(R.id.playpausebutton);
        mPlayPause.setText(PLAY);
        mStop = (TextView) findViewById(R.id.stopbutton);

        mPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this,PlayerService.class);
                if(mPlayPause.getText().equals(PLAY)){
                    intent.putExtra("action",PLAY);
                    mPlayPause.setText(PAUSE);
                }else{
                    intent.putExtra("action",PAUSE);
                    mPlayPause.setText(PLAY);
                }
                startService(intent);
            }
        });

        mStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlayPause.setText(PLAY);
                Intent intent = new Intent(MainActivity.this,PlayerService.class);
                stopService(intent);
            }
        });

    }
}

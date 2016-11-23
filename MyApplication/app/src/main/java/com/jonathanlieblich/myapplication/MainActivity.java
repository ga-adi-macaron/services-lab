package com.jonathanlieblich.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button)findViewById(R.id.play_btn);
        Button stop = (Button)findViewById(R.id.stop_btn);
        Button pause = (Button)findViewById(R.id.pause_btn);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch(view.getId()) {
                    case R.id.pause_btn:
                        Intent pauseIntent = new Intent(MainActivity.this, MusicPlayService.class);
                        pauseIntent.putExtra("action", "pause");
                        startService(pauseIntent);
                        break;
                    case R.id.play_btn:
                        Intent playIntent = new Intent(MainActivity.this, MusicPlayService.class);
                        playIntent.putExtra("action", "play");
                        startService(playIntent);
                        break;
                    case R.id.stop_btn:
                        Intent stopIntent = new Intent(MainActivity.this, MusicPlayService.class);
                        stopIntent.putExtra("action", "stop");
                        startService(stopIntent);
                        break;
                }
            }
        };
        play.setOnClickListener(onClick);
        stop.setOnClickListener(onClick);
        pause.setOnClickListener(onClick);
    }
}

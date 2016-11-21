package com.joelimyx.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button mPlay, mStop, mPause;
    private boolean isPlay = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPlay = (Button) findViewById(R.id.play);
        mStop = (Button) findViewById(R.id.stop);
        mPlay.setOnClickListener(this);
        mStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(MainActivity.this,MediaService.class);
        switch (view.getId()){
            case R.id.play:
                if (isPlay) {
                    mPlay.setText("Pause");
                    isPlay = false;
                }else{
                    mPlay.setText("Play");
                    isPlay = true;
                    intent.putExtra("Pause",true);
                }
                startService(intent);
                break;
            case R.id.stop:
                if (!isPlay){
                    intent.putExtra("Stop",2);
                    mPlay.setText("Play");
                    isPlay = true;
                    startService(intent);
                }else
                    stopService(intent);
                break;
        }
    }
}

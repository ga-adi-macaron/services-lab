package com.justinwells.myapplication;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by justinwells on 11/21/16.
 */

public class MyMediaService extends Service {
    private MediaPlayer player = null;
    private Handler handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        player = MediaPlayer.create(MyMediaService.this, R.raw.rickastley_artists);

        HandlerThread thread = new HandlerThread("MusicThread");
        thread.start();

        Looper looper = thread.getLooper();
        handler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String buttonPressed = (String) msg.obj;

                switch (buttonPressed) {
                    case MainActivity.PLAY_MUSIC:
                        try {
                            player.prepare();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        player.start();
                        Toast.makeText(MyMediaService.this, "I work!", Toast.LENGTH_SHORT).show();
                        break;
                    case MainActivity.PAUSE_MUSIC:
                        player.pause();
                        break;
                    case MainActivity.STOP_MUSIC:
                        player.stop();
                        player.release();
                        player = null;
                        break;
                }

            }
        };


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (player == null) {
            player = MediaPlayer.create(MyMediaService.this, R.raw.rickastley_artists);
        }

        Message message = handler.obtainMessage();
        message.obj = intent.getStringExtra(MainActivity.KEY);

        handler.sendMessage(message);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        player = null;
    }
}

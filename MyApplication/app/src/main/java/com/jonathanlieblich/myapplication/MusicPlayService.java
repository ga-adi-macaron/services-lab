package com.jonathanlieblich.myapplication;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;

/**
 * Created by jonlieblich on 11/21/16.
 */

public class MusicPlayService extends Service {
    MediaPlayer mMediaPlayer = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = MediaPlayer.create(this, R.raw.bensound_funnysong);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String buttonPressed = intent.getStringExtra("action");
        switch(buttonPressed) {
            case "play":
                mMediaPlayer.start();
                break;
            case "pause":
                mMediaPlayer.pause();
                break;
            case "stop":
                mMediaPlayer.stop();
                break;
        }
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mMediaPlayer != null)
            mMediaPlayer.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}

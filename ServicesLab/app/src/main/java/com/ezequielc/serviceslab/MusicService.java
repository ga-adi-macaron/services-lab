package com.ezequielc.serviceslab;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by student on 11/21/16.
 */

public class MusicService extends Service {
    private MediaPlayer mMediaPlayer;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.bensound_funnysong);

        HandlerThread handlerThread = new HandlerThread("MusicThread");
        handlerThread.start();

        Looper looper = handlerThread.getLooper();

        mHandler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                } else {
                    mMediaPlayer.start();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = mHandler.obtainMessage();
        message.obj = "Music";
        mHandler.sendMessage(message);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}

package com.joelimyx.myapplication;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;

/**
 * Created by Joe on 11/21/16.
 */

public class MediaService extends Service implements MediaPlayer.OnPreparedListener{
    MediaPlayer mMediaPlayer = null;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        HandlerThread handlerThread = new HandlerThread("Media thread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                if (msg.obj!= null && (boolean)msg.obj){
                    mMediaPlayer.pause();
                }else if (msg.arg1==2){
                    stopSelf();
                } else if (mMediaPlayer==null) {
                    mMediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.rick_ashley);
                    mMediaPlayer.setOnPreparedListener(MediaService.this);
                    try {
                        mMediaPlayer.prepareAsync();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }else {
                    mMediaPlayer.start();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = mHandler.obtainMessage();
        if (intent.getIntExtra("Stop",-1)==2){
            message.arg1 = intent.getIntExtra("Stop",-1);
        }else {
            message.obj = intent.getBooleanExtra("Pause", false);
        }
        mHandler.sendMessage(message);
        return START_NOT_STICKY;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mMediaPlayer.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer = null;
    }
}

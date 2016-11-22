package com.colinbradley.musicplayerapp;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

/**
 * Created by colinbradley on 11/21/16.
 */

public class MyService extends Service {

    public static final String TAG = "MyService";
    public static final String URL = "https://www.dropbox.com/s/3rwv1j27x5wk9bz/Alright%2C%20Game%20Face%20On.ogg?dl=1";

    MediaPlayer mMediaPlayer = null;
    Handler mHandler;


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String action = (String)msg.obj;
                switch (action){
                    case MainActivity.PLAY:
                        Log.d(TAG, "handleMessage: " + action);
                        mMediaPlayer.start();
                        break;
                    case MainActivity.PAUSE:
                        Log.d(TAG, "handleMessage: " + action);
                        mMediaPlayer.pause();
                        break;
                    case MainActivity.STOP:
                        Log.d(TAG, "handleMessage: " + action);
                        mMediaPlayer.stop();
                        mMediaPlayer.release();
                        break;
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message msg = mHandler.obtainMessage();
        msg.obj = intent.getStringExtra(MainActivity.ACTION);
        if (mMediaPlayer == null){
            mMediaPlayer = MediaPlayer.create(MyService.this, R.raw.goodtimesroll);
        }
        Log.d(TAG, "onStartCommand: " + msg.obj.toString());
        mHandler.sendMessage(msg);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null){
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}

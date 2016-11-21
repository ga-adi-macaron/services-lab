package com.scottlindley.serviceslab;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Scott Lindley on 11/21/2016.
 */

public class MusicService extends Service{
    private MediaPlayer mPlayer = null;
    private Handler mHandler;

    private static final String TAG = "MusicService";

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mPlayer = MediaPlayer.create(MusicService.this, R.raw.my_song);
        HandlerThread handlerThread = new HandlerThread("MusicServiceThread");
        handlerThread.start();
        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String mediaCommand = (String) msg.obj;
                switch (mediaCommand) {
                    case MainActivity.PLAY_INTENT_VALUE:
                        Log.d(TAG, "handleMessage: " + mediaCommand);
                        mPlayer.start();
                        break;
                    case MainActivity.PAUSE_INTENT_VALUE:
                        Log.d(TAG, "handleMessage: " + mediaCommand);
                        mPlayer.pause();
                        break;
                    case MainActivity.STOP_INTENT_VALUE:
                        Log.d(TAG, "handleMessage: " + mediaCommand);
                        mPlayer.stop();
                        mPlayer.release();
                        mPlayer = null;
                        break;
                }
            }

        };
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(mPlayer == null){
            mPlayer = MediaPlayer.create(MusicService.this, R.raw.my_song);
        }
        Message message = mHandler.obtainMessage();
        message.obj = intent.getStringExtra(MainActivity.INTENT_ACTION_KEY);
        Log.d(TAG, "onStartCommand: "+message.obj.toString());
        mHandler.sendMessage(message);
        return START_NOT_STICKY;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mPlayer!=null){
            mPlayer.release();
            mPlayer = null;
        }
    }
}

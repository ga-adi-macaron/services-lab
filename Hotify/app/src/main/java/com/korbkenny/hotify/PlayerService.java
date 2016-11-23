package com.korbkenny.hotify;

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

/**
 * Created by KorbBookProReturns on 11/21/16.
 */

public class PlayerService extends Service implements MediaPlayer.OnCompletionListener{
    MediaPlayer mPlayer = null;
    private Handler mHandler;
    String action;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mPlayer = MediaPlayer.create(PlayerService.this,R.raw.peachmoon);
        mPlayer.setOnCompletionListener(PlayerService.this);

        HandlerThread hThread = new HandlerThread("hThread");
        hThread.start();
        Looper looper = hThread.getLooper();
        mHandler = new Handler(looper){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


                String theAction = (String)msg.obj;

                if(theAction.equals("PLAY")){
                    mPlayer.start();
                }

                if(theAction.equals("PAUSE")){
                    mPlayer.pause();
                }

            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = mHandler.obtainMessage();
        action = intent.getStringExtra("action");
        message.obj = action;
        mHandler.sendMessage(message);

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        mPlayer.stop();
        mPlayer.release();
        stopSelf();
    }

    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        stopSelf();
    }
}

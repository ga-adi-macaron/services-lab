package net.serkanbal.hotify;

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

import java.io.IOException;

/**
 * Created by Serkan on 21/11/16.
 */

public class MediaPlayerService2 extends Service {
    private MediaPlayer mPlayer;
    private Handler mHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        HandlerThread handlerThread = new HandlerThread("MyThread");
        handlerThread.start();

        Looper looper = handlerThread.getLooper();

        mHandler = new Handler(looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                String command = (String) msg.obj;

                switch (command) {
                    case "play":
                        if (mPlayer == null) {
                            mPlayer = new MediaPlayer();
                            String url = "https://www.dropbox.com/s/3rwv1j27x5wk9bz/Alright%2C%20Game%20Face%20On.ogg?dl=1";
                            mPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                            try {
                                mPlayer.setDataSource(url);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            try {
                                mPlayer.prepare(); // might take long! (for buffering, etc)
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            mPlayer.start();

                            mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                @Override
                                public void onCompletion(MediaPlayer mediaPlayer) {
                                    mPlayer.stop();
                                    mPlayer.reset();
                                    mPlayer = null;
                                }
                            });
                        } else {
                            mPlayer.start();
                        }

                        break;
                    case "pause":
                        if (mPlayer != null) {
                            if (mPlayer.isPlaying()) {
                                mPlayer.pause();
                            }
                        }
                        break;
                    case "stop":
                        if (mPlayer != null) {
                            mPlayer.stop();
                            mPlayer.reset();
                            mPlayer = null;
                        }
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Message message = mHandler.obtainMessage();

        String command = intent.getStringExtra("action");

        message.obj = command;

        mHandler.sendMessage(message);
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

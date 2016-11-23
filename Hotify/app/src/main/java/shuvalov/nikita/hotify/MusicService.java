package shuvalov.nikita.hotify;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;

public class MusicService extends Service {
    private Handler mHandler;
    private MediaPlayer mMediaPlayer;
    private int mPausedPoint;

    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = MediaPlayer.create(this, R.raw.nocturne_op9);
        mPausedPoint=0;

        HandlerThread handlerThread = new HandlerThread("MusicThreadHandler");
        handlerThread.start();

        Looper looper = handlerThread.getLooper();
        mHandler = new Handler(looper) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                if (msg.obj.equals(MainActivity.TOGGLE_PLAY) && mMediaPlayer.isPlaying()) {
                    mMediaPlayer.pause();
                    mPausedPoint = mMediaPlayer.getCurrentPosition();

                } else if (msg.obj.equals(MainActivity.TOGGLE_PLAY) && !mMediaPlayer.isPlaying()) {
                    mMediaPlayer.seekTo(mPausedPoint);
                    mMediaPlayer.start();
                }
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Message message = mHandler.obtainMessage();
        if (intent.getStringExtra(MainActivity.DO_WHAT).equals(MainActivity.TOGGLE_PLAY)){
            message.obj = MainActivity.TOGGLE_PLAY;
        } else if (intent.getStringExtra(MainActivity.DO_WHAT).equals(MainActivity.STOP)){
            message.obj = MainActivity.STOP;
        }
        mHandler.sendMessage(message);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        mMediaPlayer.stop();
        mMediaPlayer.release();
    }
}

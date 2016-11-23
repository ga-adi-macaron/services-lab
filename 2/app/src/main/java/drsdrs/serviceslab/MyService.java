package drsdrs.serviceslab;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

public class MyService extends Service implements MediaPlayer.OnPreparedListener {

    public static final String KEY = "option";

    public static final String PLAY = "play";
    public static final String PAUSE = "pause";
    public static final String STOP = "stop";


    private MediaPlayer mMediaPlayer;


    public void onCreate() {

        mMediaPlayer = MediaPlayer.create(this, R.raw.intro);
        // no need to call prepare(); create() does that for you

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String choice = intent.getStringExtra(KEY);


        switch (choice) {
            case PLAY:
                mMediaPlayer.start();
                break;
            case PAUSE:
                mMediaPlayer.pause();
                break;
            case STOP:
                mMediaPlayer.stop();
                stopSelf();
                break;
        }


        return START_NOT_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {

    }
}

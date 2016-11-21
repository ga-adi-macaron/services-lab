package net.serkanbal.hotify;

import android.app.IntentService;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Handler;

import java.io.IOException;

/**
 * Created by Serkan on 21/11/16.
 */

public class MediaPlayerService extends IntentService {
    Handler mHandler;
    MediaPlayer mPlayer;

    public MediaPlayerService() {
        super("MediaPlayerService");
        mHandler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mPlayer = new MediaPlayer();

        final String play = intent.getStringExtra("play");
        String pause = intent.getStringExtra("pause");
        String stop = intent.getStringExtra("stop");

        if (play.equals("play")) {
            String url = "https://www.dropbox.com/s/3rwv1j27x5wk9bz/Alright%2C%20Game%20Face%20On.ogg?dl=1"; // your URL here
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
        }

        if (pause.equals("pause")) {
            mPlayer.pause();
        }
    }

}

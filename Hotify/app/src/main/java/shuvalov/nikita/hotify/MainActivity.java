package shuvalov.nikita.hotify;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public MediaPlayer mMediaPlayer;
    public static final String TOGGLE_PLAY="toggle_play";
    public static final String STOP = "stop";
    public static final String DO_WHAT="fried_wontons";

    Button mToggleButton, mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMediaPlayer= MediaPlayer.create(this, R.raw.nocturne_op9);

        mToggleButton = (Button)findViewById(R.id.play_button);
        mStopButton = (Button)findViewById(R.id.stop_button);

        mToggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(DO_WHAT, TOGGLE_PLAY);
                startService(intent);

            }
        });
        mStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MusicService.class);
                intent.putExtra(DO_WHAT, STOP);
                stopService(intent);
            }
        });

    }
}

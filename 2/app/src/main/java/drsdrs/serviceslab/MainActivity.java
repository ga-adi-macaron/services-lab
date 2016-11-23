package drsdrs.serviceslab;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mPlayButton;
    private Button mPauseButton;
    private Button mStopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPlayButton = (Button) findViewById(R.id.play_button);
        mPauseButton = (Button) findViewById(R.id.pause_button);
        mStopButton = (Button) findViewById(R.id.stop_button);

        mPlayButton.setOnClickListener(this);
        mPauseButton.setOnClickListener(this);
        mStopButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(MainActivity.this, MyService.class);

        switch (view.getId()) {

            case R.id.play_button:
                intent.putExtra(MyService.KEY,MyService.PLAY);
                startService(intent);
                break;
            case R.id.pause_button:
                intent.putExtra(MyService.KEY,MyService.PAUSE);
                startService(intent);
                break;
            case R.id.stop_button:
                intent.putExtra(MyService.KEY,MyService.STOP);
                startService(intent);
                break;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(MainActivity.this,MyService.class));
    }
}

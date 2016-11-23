package com.elysium.sonicboom;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button mPlay, mPause, mStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        setViews();

        if(hasInternetConnection()) {

            Intent intent = new Intent(MainActivity.this, BoomService.class);
            startService(intent);
        }

        else
            Toast.makeText(MainActivity.this, "No Internet connection", Toast.LENGTH_SHORT).show();
    }

    void setViews() {

        mPlay = (Button) findViewById(R.id.play_button);
        mPause = (Button) findViewById(R.id.pause_button);
        mStop = (Button) findViewById(R.id.stop_button);

        mPlay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BoomService.class);
                startService(intent);

            }
        });

        /**mPause.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BoomService.class);
            }
        });*/

        mStop.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, BoomService.class);
                stopService(intent);
            }
        });
    }

    boolean hasInternetConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    @Override
    protected void onStop() {
        super.onStop();

        stopService(new Intent(MainActivity.this, BoomService.class));
    }
}

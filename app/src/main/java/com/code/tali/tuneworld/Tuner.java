package com.code.tali.tuneworld;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Tuner extends AppCompatActivity {
    private MusicPlayerService player;
    boolean serviceBound = false;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MusicPlayerService.LocalBinder binder = (MusicPlayerService.LocalBinder) service;
            player = binder.getService();
            serviceBound = true;


        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            serviceBound = false;
        }
    };
    private void playAudio(String media) {
        //Check is service is active

        Intent playerIntent = new Intent(this, MusicPlayerService.class);
        playerIntent.putExtra("media", media);
        startService(playerIntent);
        bindService(playerIntent, serviceConnection, Context.BIND_AUTO_CREATE);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);
        Button aButton = (Button) findViewById(R.id.aButton);
        aButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("http://www.philharmonia.co.uk/assets/audio/samples/saxophone/saxophone_A4_very-long_cresc-decresc_normal.mp3");
            }
        });
        Button dButton = (Button) findViewById(R.id.dButton);
        dButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("http://www.philharmonia.co.uk/assets/audio/samples/saxophone/saxophone_D5_15_forte_normal.mp3");
            }
        });
        Button gButton = (Button) findViewById(R.id.gButton);
        gButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("http://www.philharmonia.co.uk/assets/audio/samples/saxophone/saxophone_G4_15_forte_normal.mp3");
            }
        });
        Button cButton = (Button) findViewById(R.id.cButton);
        cButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio("http://www.philharmonia.co.uk/assets/audio/samples/saxophone/saxophone_C5_very-long_cresc-decresc_normal.mp3");
            }
        });
    }
}

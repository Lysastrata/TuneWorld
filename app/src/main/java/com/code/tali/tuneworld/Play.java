package com.code.tali.tuneworld;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class Play extends AppCompatActivity {
    private MusicPlayerService player;
    boolean serviceBound = false;
    Random random = new Random();
    String[] clips = {
      "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_A3_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_A4_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_A5_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_A6_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_As3_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_As4_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_As5_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_As6_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_B3_15_mezzo-piano_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_B5_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_B6_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_C3_15_piano_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_C4_15_mezzo-piano_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_C5_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_C7_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Cs3_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Cs4_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Cs5_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Cs6_15_pianissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_D3_15_pianissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_D4_15_mezzo-piano_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_D5_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_D6_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_D7_15_piano_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Ds3_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Ds4_15_mezzo-piano_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Ds5_15_mezzo-piano_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Ds6_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_E3_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_E4_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_E5_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_F3_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_F4_15_mezzo-piano_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_F5_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_F6_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Fs3_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Fs4_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Fs5_15_forte_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Fs6_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_G3_15_pianissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_G4_15_forte_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_G5_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_G6_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Gs3_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Gs4_15_fortissimo_arco-normal.mp3", "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Gs5_15_fortissimo_arco-normal.mp3",
            "http://www.philharmonia.co.uk/assets/audio/samples/viola/viola_Gs6_15_forte_arco-normal.mp3"
    };

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
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean("ServiceState", serviceBound);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        serviceBound = savedInstanceState.getBoolean("ServiceState");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (serviceBound) {
            unbindService(serviceConnection);
            //service is active
            player.stopSelf();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        ConstraintLayout musicButton = (ConstraintLayout) findViewById(R.id.musicBox);



        musicButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                System.out.println("Touch working");


                switch (event.getAction()) {

                    case MotionEvent.ACTION_DOWN:

                        Random rnd = new Random();
                        int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                        view.setBackgroundColor(color);
                        int rand = random.nextInt(clips.length);
                        playAudio(clips[rand]);

//                    case MotionEvent.ACTION_UP:
//                        check ++;
//                        System.out.println(check);
//                        System.out.println("up");
//                    case MotionEvent.ACTION_POINTER_DOWN:
//                        check ++;
//                        System.out.println(check);
//                        System.out.println("mor");
//                        rand = random.nextInt(clips.length);
//                        playAudio(clips[rand]);
//
                }


                return true;
            }
        });


    }


}

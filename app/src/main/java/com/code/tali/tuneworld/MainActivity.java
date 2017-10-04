package com.code.tali.tuneworld;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button tunerButton = (Button) findViewById(R.id.tunerButton);
        tunerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goTuner = new Intent(getApplicationContext(), Tuner.class);
                startActivity(goTuner);
            }
        });
        Button favoriteButton = (Button) findViewById(R.id.favoriteButton);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFavorites = new Intent(getApplicationContext(), Favorites.class);
                startActivity(goFavorites);
            }
        });
        Button playButton = (Button) findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPlay = new Intent(getApplicationContext(), Play.class);
                startActivity(goPlay);
            }
        });
    }
}

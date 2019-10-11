package com.example.pumpkinsmasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class GameOverActivity extends AppCompatActivity {

    int Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();

        if(intent.hasExtra("Score"))
        {
            Score = intent.getExtras().getInt("Score");
        }
    }

    @Override
    public void onBackPressed()
    {

    }

    public void play_again(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }

    public void high_scores(View view)
    {
        Intent intent = new Intent(this, HighScoresActivity.class);
        intent.putExtra("Score", Score);
        startActivity(intent);
    }
}

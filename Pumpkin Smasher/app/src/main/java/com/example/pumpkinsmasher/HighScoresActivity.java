package com.example.pumpkinsmasher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HighScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_scores);

        TextView score_TextView = (TextView)findViewById(R.id.score_TextView);

        Intent intent = getIntent();

        if(getIntent().hasExtra("Score"))
        {
            int Score = intent.getExtras().getInt("Score");
            score_TextView.setText("Score: " + String.valueOf(Score));
        }
    }
}

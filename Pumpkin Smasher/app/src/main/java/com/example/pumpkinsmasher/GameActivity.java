package com.example.pumpkinsmasher;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    List<ImageView> pumpkins;
    List<ImageView> boots;

    TextView scoreTextView;
    ConstraintLayout constraintLayout;

    int random_pumpkin;
    int random_boot;

    int old_score = 0;
    int new_score = 0;

    long time = 1000;
    boolean GameIsOver = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        scoreTextView = (TextView)findViewById(R.id.score_TextView);
        constraintLayout = (ConstraintLayout)findViewById(R.id.constraintLayout);

        CreatePumpkinList();
        CreateBootList();

        setPumpkinsInvisible();
        setBootsInvisible();

        RunGame();
    }

    @Override
    public void onBackPressed()
    {

    }

    public void CreatePumpkinList()
    {
        pumpkins = new ArrayList<ImageView>();
        pumpkins.add((ImageView)findViewById(R.id.pumpkin1));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin2));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin3));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin4));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin5));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin6));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin7));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin8));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin9));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin10));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin11));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin12));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin13));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin14));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin15));
        pumpkins.add((ImageView)findViewById(R.id.pumpkin16));
    }

    public void CreateBootList()
    {
        boots = new ArrayList<ImageView>();
        boots.add((ImageView)findViewById(R.id.boot1));
        boots.add((ImageView)findViewById(R.id.boot2));
        boots.add((ImageView)findViewById(R.id.boot3));
        boots.add((ImageView)findViewById(R.id.boot4));
        boots.add((ImageView)findViewById(R.id.boot5));
        boots.add((ImageView)findViewById(R.id.boot6));
        boots.add((ImageView)findViewById(R.id.boot7));
        boots.add((ImageView)findViewById(R.id.boot8));
        boots.add((ImageView)findViewById(R.id.boot9));
        boots.add((ImageView)findViewById(R.id.boot10));
        boots.add((ImageView)findViewById(R.id.boot11));
        boots.add((ImageView)findViewById(R.id.boot12));
        boots.add((ImageView)findViewById(R.id.boot13));
        boots.add((ImageView)findViewById(R.id.boot14));
        boots.add((ImageView)findViewById(R.id.boot15));
        boots.add((ImageView)findViewById(R.id.boot16));
    }

    public void setPumpkinsInvisible()
    {
        for(int i=0; i <= 15; i++)
        {
            pumpkins.get(i).setVisibility(View.INVISIBLE);
        }
    }

    public void setBootsInvisible()
    {
        for(int i=0; i <= 15; i++)
        {
            boots.get(i).setVisibility(View.INVISIBLE);
        }
    }

    public void RunGame()
    {
        Runnable myRunnable = new Runnable()
        {
            @Override
            public void run()
            {
                while (!GameIsOver)
                {
                    GenerateRandomNumbers();

                    scoreTextView.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            pumpkins.get(random_pumpkin).setVisibility(View.VISIBLE);
                            boots.get(random_boot).setVisibility(View.VISIBLE);
                            scoreTextView.setText("Score: " + Integer.toString(new_score));
                        }
                    });

                    try
                    {
                        Thread.sleep(time);
                    }

                    catch(Exception e)
                    {
                        gameOver();
                    }

                    pumpkins.get(random_pumpkin).setVisibility(View.INVISIBLE);
                    boots.get(random_boot).setVisibility(View.INVISIBLE);

                    if(random_pumpkin == random_boot && old_score == new_score)
                    {
                        gameOver();
                    }

                    old_score = new_score;
                }
            }
        };

        Thread myThread = new Thread(myRunnable);
        myThread.start();
    }

    public void GenerateRandomNumbers()
    {
        Random rand = new Random();
        random_pumpkin = rand.nextInt(15);
        random_boot = rand.nextInt(15);
    }

    public void hit_click(View view)
    {
        if(random_pumpkin == random_boot)
        {
            pumpkins.get(random_pumpkin).setVisibility(View.INVISIBLE);
            new_score++;

            if(time > 100)
            {
                time = time - 50;
            }
        }

        else
        {
            gameOver();
        }

    }

    public void gameOver()
    {
        GameIsOver = true;
        Intent intent = new Intent(this, GameOverActivity.class);
        intent.putExtra("Score", new_score);
        startActivity(intent);
    }

}

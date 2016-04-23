package lii2.colorgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    RelativeLayout root;
    Button RedButton, BlueButton, GreenButton, YellowButton;
    TextView TimerBox, ScoreBox, HighScoreBox;
    Random rand;
    int colorID;
    int playerScore, HighScore, Level;
    long startTime = 0;

    Handler timerHandler = new Handler();

    //this ends the game if over time and updates the timerbox
    Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            long millis = System.currentTimeMillis() - startTime;
            int seconds =  (int) (millis / 1000);
            int decis = (int) ((millis % 1000)/10);

            TimerBox.setText(String.format("Time: %d.%02d", seconds, decis));
            timerHandler.postDelayed(this, 10);
            if ( seconds > 4 ) {
                endGame();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        root = (RelativeLayout) findViewById(R.id.background);

        RedButton = (Button) findViewById(R.id.redButton);
        BlueButton = (Button) findViewById(R.id.blueButton);
        GreenButton = (Button) findViewById(R.id.greenButton);
        YellowButton = (Button) findViewById(R.id.yellowButton);

        TimerBox = (TextView) findViewById(R.id.timerBox);
        ScoreBox = (TextView) findViewById(R.id.scoreBox);
        HighScoreBox = (TextView) findViewById(R.id.highScoreBox);

        rand = new Random();

        randomColor();

        startTime =  System.currentTimeMillis();

        timerHandler.postDelayed(timerRunnable, 0);


        playerScore = 0;

        SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
        HighScore = prefs.getInt("highScore", 0); //get high score
        HighScoreBox.setText(String.format("High Score: %d",HighScore));
    }

    //randomly selects a color from yellow, blue, green and red
    public void randomColor() {
        int randInt = rand.nextInt(100);

        if (0 < randInt && randInt <= 24) {
            root.setBackgroundColor(Color.RED);
            colorID = Color.RED;
        } else if (24 < randInt && randInt <= 48 ) {
            root.setBackgroundColor(Color.BLUE);
            colorID = Color.BLUE;
        } else if (48 < randInt && randInt <= 72) {
            root.setBackgroundColor(Color.YELLOW);
            colorID = Color.YELLOW;
        } else if (72 < randInt && randInt <= 96) {
            root.setBackgroundColor(Color.GREEN);
            colorID = Color.GREEN;
        } else if (96 < randInt && randInt <= 100) {
        root.setBackgroundColor(Color.WHITE);
        colorID = Color.WHITE;
        }
    }

    public void endGame(){
        timerHandler.removeCallbacks(timerRunnable);
        root.setBackgroundColor(Color.WHITE);

        if(playerScore>HighScore){
            SharedPreferences prefs = this.getSharedPreferences("myPrefsKey", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            editor.putInt("highScore", playerScore);
            editor.commit();
        }

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void endGame(View view) {
        endGame();
    }

    public void resetTimer() {
        timerHandler.removeCallbacks(timerRunnable);
        startTime =  System.currentTimeMillis();
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public void pressRedButton(View view) {
        if (colorID == Color.RED) {
            resetTimer();
            randomColor();
            playerScore = playerScore + 1;
            ScoreBox.setText(String.format("Score: %d", playerScore));
        }else{
            endGame();
        }
    }

    public void pressGreenButton(View view) {
        if (colorID == Color.GREEN) {
            resetTimer();
            randomColor();
            playerScore = playerScore + 1;
            ScoreBox.setText(String.format("Score: %d", playerScore));
        }else{
            endGame();
        }
    }

    public void pressBlueButton(View view) {
        if (colorID == Color.BLUE) {
            resetTimer();
            randomColor();
            playerScore = playerScore + 1;
            ScoreBox.setText(String.format("Score: %d", playerScore));
        }else{
            endGame();
        }
    }

    public void pressYellowButton(View view) {
        if (colorID == Color.YELLOW) {
            resetTimer();
            randomColor();
            playerScore = playerScore + 1;
            ScoreBox.setText(String.format("Score: %d", playerScore));
        }else{
            endGame();
        }
    }

    public void pressWhiteButton(View view) {
        if (colorID == Color.WHITE) {
            resetTimer();
            randomColor();
            playerScore = playerScore + 5;
            ScoreBox.setText(String.format("Score: %d", playerScore));
        }else{
            endGame();
        }
    }

}

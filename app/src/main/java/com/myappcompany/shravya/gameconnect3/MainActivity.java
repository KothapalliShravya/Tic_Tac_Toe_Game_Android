package com.myappcompany.shravya.gameconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    boolean redPlayer = true;

    boolean hasWon = false;

    int player = 1;

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int[] gameState = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    public void put(View view) {

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 0 && !hasWon) {

            gameState[tappedCounter] = player;

            counter.setTranslationY(-1000);

            if (redPlayer) {

                redPlayer = false;

                player = 2;

                counter.setImageResource(R.drawable.red);

            } else {

                redPlayer = true;

                player = 1;

                counter.setImageResource(R.drawable.yellow);

            }

            counter.animate().translationYBy(1000).setDuration(1000);

            for (int[] position : winningPositions) {

                if (gameState[position[0]] == gameState[position[1]] && gameState[position[1]] == gameState[position[2]] && gameState[position[0]] != 0) {

                    String winner = "";

                    if (player == 1) {

                        winner = "Yellow";

                        hasWon = true;

                    } else {

                        winner = "Red";

                        hasWon = true;

                    }

                    Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

                    TextView winnerText = (TextView) findViewById(R.id.winnerText);

                    winnerText.setText(winner + " has won!");

                    playAgainButton.setVisibility(View.VISIBLE);

                    winnerText.setVisibility(View.VISIBLE);

                }

            }

        }

    }

    public void playAgain(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAgainButton);

        TextView winnerText = (TextView) findViewById(R.id.winnerText);

        playAgainButton.setVisibility(View.INVISIBLE);

        winnerText.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

        for(int i = 0; i < gridLayout.getChildCount(); i++) {

            ImageView counter = (ImageView) gridLayout.getChildAt(i);

            counter.setImageDrawable(null);

        }

        redPlayer = true;

        hasWon = false;

        player = 1;

        for (int i = 0; i < gameState.length; i++) {

            gameState[i] = 0;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

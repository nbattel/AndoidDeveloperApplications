package com.myappcompany.nick.connect3game;

import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0: yellow, 1: red, 2: empty

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    int activePlayer = 0;

    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;

        Log.i("Tag", counter.getTag().toString());

        //Getting the tag as a string from the object and parsing it to an int to later be stored in the array
        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        //Setting each element in the array to it's corresponding coloured game object
        gameState[tappedCounter] = activePlayer;

        counter.setTranslationY(-1500);

        if(activePlayer == 0)
        {
            counter.setImageResource(R.drawable.yellow);
            activePlayer = 1;
        }
        else
        {
            counter.setImageResource((R.drawable.red));
            activePlayer = 0;
        }

        counter.animate().translationYBy(1500).rotation(3600).setDuration(100);

        for (int[] winningPosiiton : winningPositions)
        {
            if (gameState[winningPosiiton[0]] == gameState[winningPosiiton[1]] && gameState[winningPosiiton[1]] == gameState[winningPosiiton[2]] && gameState[winningPosiiton[0]] != 2)
            {

                String winner = "";
                if(activePlayer == 1)
                {
                    winner = "Yellow";
                }
                else
                {
                   winner = "Red";
                }
                Toast.makeText(this, winner + " has Won!", Toast.LENGTH_LONG).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

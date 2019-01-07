package com.myappcompany.nick.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{

    int randomNumber;

    public void generateRandomNumber() //Method to generate a random number.
    {
        Random rand = new Random();

        randomNumber = rand.nextInt(20) + 1;
        //20 is the maximum and 1 is the minimum.
    }

    public void guess(View view)
    {
        EditText number = (EditText) findViewById(R.id.number);

        int userGuess = Integer.parseInt(number.getText().toString());
        //Converting the number the user entered in the text field to an integer.

        Log.i("Entered Value", number.getText().toString());

        Log.i("Info", Integer.toString(randomNumber));

        String message;

        if (userGuess < randomNumber)
        {
           message = "Higher!";
        }
        else if (userGuess > randomNumber)
        {
            message = "Lower!";
        }
        else
        {
            message = "You got it! Try again!";
            generateRandomNumber();
            //Generating a random number after the user guesses the random number correct.
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        //Displaying a message at the bottom of the screen to inform the user whether their guess was right, too high, or too low.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();
    }
}

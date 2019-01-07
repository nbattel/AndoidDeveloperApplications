package com.myappcompany.nick.numbershapes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class Number
    {

        int number;

        //Calculating square numbers
        public boolean SquareNumber()
        {

            int x = 1;
            int squareNumber = 1;

            while ( squareNumber < number)
            {
                squareNumber = x * x;
                x++;
            }

            if (number == squareNumber)
            {
                return true;
            }
            else
            {
                return false;
            }

        }

        //Calculating triangular numbers
        public boolean TriangularNumber()
        {
            int x = 1;
            int triangularNumber = 1;

            while( triangularNumber < number)
            {
                x++;
                triangularNumber = x * (x + 1)/2;
            }

            if (number == triangularNumber)
            {
                return true;
            }
            else
            {
                return false;
            }
        }

    }

    public void testNumber(View view)
    {
        Log.i("Info", "Button Pressed!");

        EditText editText = (EditText) findViewById(R.id.editText);

        String message;

        if (editText.getText().toString().isEmpty())
        {
            message = "Please enter a number!";
        }
        else
        {

            Number myNumber = new Number();
            myNumber.number = Integer.parseInt(editText.getText().toString());

            if (myNumber.SquareNumber() && myNumber.TriangularNumber()) {
                message = editText.getText().toString() + " is a square number and a triangular number.";
            } else if (myNumber.TriangularNumber()) {
                message = editText.getText().toString() + " is a triangular number but not a square number.";
            } else if (myNumber.SquareNumber()) {
                message = editText.getText().toString() + " is a square number but not a triangular number.";
            } else {
                message = editText.getText().toString() + " is neither a square number nor a triangular number.";
            }
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

package com.myappcompany.nick.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view)
    {
        EditText currencyEditText = (EditText) findViewById(R.id.currencyEditText);
        Log.i("Info", "Working!");

        String amntInPounds = currencyEditText.getText().toString();
        //converting the amount in pounds to a string variable called amntInPounds

        double currency = Double.parseDouble(amntInPounds);
        //Converting amntInpounds to a currency of type double

        double temp = currency * 1.73;
        //Calculating the amount of pounds in dollars

        String amntInDollars = String.format("%.2f", temp);
        //Converting the calculated dollar amount to a string of only two decimal places

        Toast.makeText(this, "£" + amntInPounds + " is " + "$" + amntInDollars, Toast.LENGTH_LONG).show();
        //Displaying the converted amount through a toast
    }    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

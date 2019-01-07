package com.myappcompany.nick.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.*;
import java.net.*;

public class MainActivity extends AppCompatActivity {

    final static String API_KEY = "b7a728cac148defa0d03e4b8fefade34";

    public void convert(View view)
    {
        button.setEnabled(false);
        EditText currencyEditText = (EditText) findViewById(R.id.currencyEditText);
        Log.i("Info", "Working!");

        String amntInPounds = currencyEditText.getText().toString();
        //converting the amount in pounds to a string variable called amntInPounds

        double currency = Double.parseDouble(amntInPounds);
        //Converting amntInpounds to a currency of type double

        final String[] responseFromAPI = new String[1];

        double rate;
        try {

            Thread thread = new Thread(new Runnable(){
                @Override
                public void run(){
                    //code to do the HTTP request
                    String urlToRead = "http://data.fixer.io/api/latest?access_key=" + API_KEY + "&symbols=CAD";
                    StringBuilder result = new StringBuilder();

                    Log.i("URL:", urlToRead);

                    try {
                        URL url = new URL(urlToRead);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                        Log.i("Connection: ", conn.toString());

                        conn.setRequestMethod("GET");
                        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                        Log.i("buffer: ", rd.toString());

                        String line;
                        while ((line = rd.readLine()) != null) {
                            Log.i("LINE: ", line);
                            result.append(line);
                        }

                        rd.close();
                        responseFromAPI[0] = (String)(result.toString());
                        Log.i("responseFromAPI: ", responseFromAPI[0]);
                    } catch ( Exception e ) {}
                }
            });
            thread.start();

            Thread.sleep(400);
            String response = responseFromAPI[0];
            Log.i("Response", response);
            String toSearchFor = "\"CAD\":";
            int index = response.lastIndexOf(toSearchFor)+toSearchFor.length(); // start of the rate

            String rateAsString = "";
            for (int i = index; i < response.length(); i++) {
                if (Character.isDigit(response.charAt(i)) || response.charAt(i)=='.') {
                    rateAsString = new StringBuilder().append(rateAsString).append(response.charAt(i)).toString();
                }
            }
            rate = Double.parseDouble(rateAsString);
            Log.i("Response", response);
            Log.i("Index", Integer.toString(index));
            Log.i("Rate", Double.toString(rate));

            double temp = currency * rate;
            //Calculating the amount of Euros in dollars

            String amntInDollars = String.format("%.2f", temp);
            //Converting the calculated dollar amount to a string of only two decimal places

            Toast.makeText(this, "€" + amntInPounds + " is " + "$" + amntInDollars, Toast.LENGTH_LONG).show();
            //Displaying the converted amount through a toast

        } catch ( Exception e )
        {
            Log.i("Info", e.getMessage());
            rate = 0;
            Log.i("USING DEFAULT RATE", "1.53");
        }

        button.setEnabled(true);
    }
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
    }
}

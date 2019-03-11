package com.tutorials.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("StartActivity","onCreate()");
        // hier kann man zb eine db verbindung herstellen

        //Button myButton = (Button) findViewById(R.id.button2);
        //myButton.setT
        TextView myTextView = (TextView) findViewById(R.id.statusText);
        myTextView.setText("Blubber die Blubb!");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("StartActivity","onStart()");
        // the activity is about to become visible

        // hier könnte man ergebnisse refreshen
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("StartActivity","onResume()");
        // the activity has become visible (its now resumed)
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("StartActivity","onPause()");
        // Another activity is taking focus (this activity is about to be "paused")

        // es kann sein, dass hier schon "schluss" ist. (onStop & onDestroy) werden manchmal garnicht mehr
        // ausgeführt, daher sollte man spätestens hier daten abspeichern
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("StartActivity","onStop()");
        // The activity is no longer visible (its now "stopped")
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("StartActivity","onDestroy()");
        // The activity is about to be destroyed.
    }




    public void openAnotherActivity(View view) {
        // Methode wird als onClick Handler verwendet

        Log.d("StartActivity","Open another Activity");

        Intent intent = new Intent(this, AnotherActivity.class);
        // AnotherActivity.class => ist die vollständige Referenz des Codes zur Klasse

        startActivity(intent);

    }

}

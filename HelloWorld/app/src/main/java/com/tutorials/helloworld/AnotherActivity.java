package com.tutorials.helloworld;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
    }


    public void openFabDemo(View view) {
        Log.d("AnotherActivity","Open FAB Button was touched.");
        Intent intent = new Intent(this, fabDemo.class); // from -> to view

        startActivity(intent);
    }
}

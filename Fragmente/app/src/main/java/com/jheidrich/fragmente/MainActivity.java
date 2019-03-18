package com.jheidrich.fragmente;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity
    implements ButtonFragment.OnFragmentInteractionListener {

    ListView toDoList;
    String[] aufgaben = {
        "Aufgabe #1",
        "Aufgabe #2",
        "Aufgabe #3",
        "Aufgabe #4",
        "Aufgabe #5",
        "Aufgabe #6",
        "Aufgabe #7",
        "Aufgabe #8",
        "Aufgabe #9",
        "Aufgabe #10",
        "Aufgabe #11",
        "Aufgabe #12",
        "Aufgabe #13",
        "Aufgabe #14",
        "Aufgabe #15"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList = (ListView) findViewById(R.id.todolist);
        toDoList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, aufgaben));

        //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_placeholder, new ButtonFragment()).commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
}

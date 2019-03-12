package com.jheidrich.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toDoList = (ListView) findViewById(R.id.toDoList);

        // Using Array for List
        // using default List Item (using android.R...)
        // toDoList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, toDoItems));

        // using own created element in list:
        //toDoList.setAdapter(new ArrayAdapter<String>(this, R.layout.todoitem, toDoItems));

        // Using Class for List

        toDoList.setAdapter(new ToDoAdapter(this));
    }
}

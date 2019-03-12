package com.jheidrich.todolist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;


public class MainActivity extends AppCompatActivity {

    //ListView toDoList;
    RecyclerView toDoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //toDoList = (ListView) findViewById(R.id.toDoList);

        // Using Array for List
        // using default List Item (using android.R...)
        // toDoList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1, toDoItems));

        // using own created element in list:
        //toDoList.setAdapter(new ArrayAdapter<String>(this, R.layout.todoitem, toDoItems));

        // Using Class for List
        //toDoList.setAdapter(new ToDoAdapter(this));

        initViewContent();
    }
    // wechsel zwischen den Views
    /*public static Intent createToDoIntent(Context context, int id) {
        Intent intent = new Intent(context, ToDoActivity.class);
        intent.putExtra("com.jheidrich.toDoID", id);
        return intent;
    }
*/

    private void initViewContent() {
        toDoList = (RecyclerView)findViewById(R.id.toDoListRecycler);
        toDoList.setHasFixedSize(true); // performance ++
        toDoList.setLayoutManager(new LinearLayoutManager(this));
        toDoList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        toDoList.setAdapter(ToDoAdapter.getMySingelton(this));
    }

    @Override
    protected void onResume() {
        super.onResume();
        initViewContent();
    }
}
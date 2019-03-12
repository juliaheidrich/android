package com.jheidrich.todolist;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

public class ToDoActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);

        // prepare fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.toDoContainer);

        if(fragment == null) {
            fragment = new ToDoFragment();
            fragmentManager.beginTransaction().add(R.id.toDoContainer,fragment).commit();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}

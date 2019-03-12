package com.jheidrich.todolist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ToDoPagerActivity extends FragmentActivity {

    private List<ToDoItem> toDoItemList;
    private ViewPager toDoPager;


    public static Intent createIntent(Context context, int id) {
        Intent intent = new Intent(context, ToDoPagerActivity.class);
        intent.putExtra("com.jheidrich.toDoID", id);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPagerActivity();
    }

    private void initPagerActivity() {
        setContentView(R.layout.activity_todopager);

        toDoPager = (ViewPager)findViewById(R.id.toDoPager);
        toDoItemList = ToDoAdapter.getMySingelton(this).getToDoItems();

        FragmentManager fragmentManager = getSupportFragmentManager();
        toDoPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int i) {
                ToDoItem toDoItem = toDoItemList.get(i);
                return ToDoFragment.createInstance(toDoItem.getId());
            }
            @Override
            public int getCount() {
                return toDoItemList.size();
            }
        });

        int id = (int) getIntent().getSerializableExtra("com.jheidrich.toDoID");
        for (int i = 0; i< toDoItemList.size(); i++){
            if(toDoItemList.get(i).getId() == id) {
                toDoPager.setCurrentItem(i);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPagerActivity();
    }
}

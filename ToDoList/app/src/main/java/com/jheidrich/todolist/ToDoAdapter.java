package com.jheidrich.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ToDoAdapter extends BaseAdapter {

    private ArrayList<ToDoItem> toDoItems;
    private MainActivity activity;

    public ToDoAdapter(MainActivity activity) {
        this.activity = activity;

        toDoItems = new ArrayList<ToDoItem>();
        for(int i = 1; i <= 15; i++){
            toDoItems.add(new ToDoItem("Aufgabe -" + i));
        }
    }


    @Override
    public int getCount() {
        return toDoItems.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return toDoItems.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ToDoItem toDoItem = toDoItems.get(position);

        //Layout preparing
        LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.todoitem, parent, false);
        // false = wir binden uns nicht an die View Gruppe sondern auf eine einzelne Row

        TextView toDoTitle = (TextView) row.findViewById(R.id.toDoItem);
        toDoTitle.setText(toDoItem.getTitle());

        return row;
    }
}

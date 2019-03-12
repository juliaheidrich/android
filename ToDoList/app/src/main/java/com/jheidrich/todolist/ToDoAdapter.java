package com.jheidrich.todolist;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */


// Not needed in recycler public class ToDoAdapter extends BaseAdapter
public class ToDoAdapter extends RecyclerView.Adapter{

    private ArrayList<ToDoItem> toDoItems;
    private MainActivity activity;

    private static class ToDoViewHolder extends RecyclerView.ViewHolder {
        public TextView toDoTitle;
        public CheckBox toDoItemIsDoneCheckBox;
        public ToDoViewHolder(View view){
            super(view);
            toDoTitle = (TextView) view.findViewById(R.id.toDoTitle);
            toDoItemIsDoneCheckBox = (CheckBox) view.findViewById(R.id.toDoIsDone);
        }
    }

    public ToDoAdapter(MainActivity activity) {
        this.activity = activity;

        toDoItems = new ArrayList<ToDoItem>();
        for(int i = 1; i <= 15; i++){
            toDoItems.add(new ToDoItem("Aufgabe -" + i));
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // preparing layout (width / height)
        View view = this.activity.getLayoutInflater().inflate(R.layout.complex_item, viewGroup, false);
        return new ToDoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        // fill data
        ToDoItem toDoItem = toDoItems.get(position);
        ToDoViewHolder toDoHolder = (ToDoViewHolder)viewHolder;
        toDoHolder.toDoTitle.setText(toDoItem.getTitle());
        toDoHolder.toDoItemIsDoneCheckBox.setChecked(toDoItem.isToDoItemDone());
    }

    @Override
    public int getItemCount() {
        return toDoItems.size();
    }


    /*
    not needed in Recycler
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

    */
}

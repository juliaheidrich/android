package com.jheidrich.todolist;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class ToDoFragment extends Fragment {

    private ToDoItem toDoItem;
    private TextView toDoTitle;
    private TextView toDoIDView;
    private CheckBox isDone;

    public static ToDoFragment createInstance(int id) {
        Bundle args = new Bundle();
        args.putSerializable("com.jheidrich.toDoID", id);

        ToDoFragment fragment = new ToDoFragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // without pager
        // int toDoID = (int) getActivity().getIntent().getSerializableExtra("com.jheidrich.toDoID");

        // with pager
        int toDoID = (int) getArguments().getSerializable("com.jheidrich.toDoID");
        toDoItem = ToDoAdapter.getMySingelton(getActivity()).getToDoItem(toDoID);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_to_do, container, false);
        toDoTitle = (TextView) view.findViewById(R.id.toDoTitle);
        toDoTitle.setText(toDoItem.getTitle());

        toDoIDView = (TextView) view.findViewById(R.id.toDoID);
        toDoIDView.setText(String.valueOf(toDoItem.getId()));

        isDone = (CheckBox) view.findViewById(R.id.toDoIsDone);
        isDone.setChecked(toDoItem.isToDoItemDone());
        isDone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                toDoItem.setToDoItemDone(isChecked);
            }
        });

        return view;
    }

}

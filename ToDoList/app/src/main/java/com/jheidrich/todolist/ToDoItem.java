package com.jheidrich.todolist;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ToDoItem {
    private int id;
    private String title;
    private boolean toDoItemDone;

    public ToDoItem(String title) {
        id = (int) (Math.random() *1000) + 1;
        this.title = title;
        this.toDoItemDone = false;

        if(this.getId() % 2 == 0) {
            this.toDoItemDone = true;
        }
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isToDoItemDone() {
        return toDoItemDone;
    }

    public void setToDoItemDone(boolean toDoItemDone) {
        this.toDoItemDone = toDoItemDone;
    }
}

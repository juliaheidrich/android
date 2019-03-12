package com.jheidrich.todolist;

/**
 * https://github.com/juliaheidrich
 * (c) by Julia Heidrich
 */
public class ToDoItem {
    private int id;
    private String title;

    public ToDoItem(String title) {
        id = (int) (Math.random() *1000) + 1;
        this.title = title;
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

}

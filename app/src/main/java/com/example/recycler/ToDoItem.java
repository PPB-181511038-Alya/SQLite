package com.example.recycler;

import java.io.Serializable;

public class ToDoItem implements Serializable {
    int id;
    String toDoTask;
    String toDoSchedule;

    public ToDoItem(String toDoTask, String toDoSchedule) {
        this.toDoTask = toDoTask;
        this.toDoSchedule = toDoSchedule;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToDoTask() {
        return toDoTask;
    }

    public void setToDoTask(String toDoTask) {
        this.toDoTask = toDoTask;
    }

    public String getToDoSchedule() {
        return toDoSchedule;
    }

    public void setToDoSchedule(String toDoSchedule) {
        this.toDoSchedule = toDoSchedule;
    }
}

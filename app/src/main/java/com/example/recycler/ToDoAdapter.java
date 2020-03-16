package com.example.recycler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder> {

    ArrayList<ToDoItem> toDoList;

    public ToDoAdapter(ArrayList<ToDoItem> toDoList) {
        this.toDoList = toDoList;
    }

    @NonNull
    @Override
    public ToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.to_do_item, parent, false);
        return new ToDoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ToDoViewHolder holder, int position) {
        ToDoItem toDoItem = toDoList.get(position);
        holder.toDoTask.setText(toDoItem.getToDoTask());
        holder.toDoSchedule.setText(toDoItem.getToDoSchedule());
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    class ToDoViewHolder extends RecyclerView.ViewHolder {

        TextView toDoTask, toDoSchedule;

        public ToDoViewHolder(@NonNull View itemView) {
            super(itemView);
            toDoTask = itemView.findViewById(R.id.to_do_task);
            toDoSchedule = itemView.findViewById(R.id.to_do_schedule);
        }
    }
}


package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<ToDoItem> toDoList = new ArrayList<>();
    private RecyclerView recyclerView;

    private RecyclerView.Adapter adapter;
    private ToDoAdapter toDoAdapter;
    DBAdapter db = new DBAdapter(this);
    private int request_Code =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_to_do);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemViewCacheSize(20);
        recyclerView.setDrawingCacheEnabled(true);
        recyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);

        toDoList= initTask();

//
//        adapter = new TaskAdapter(tasks,this);
//        this.tasks.setAdapter(adapter);

        toDoAdapter = new ToDoAdapter(toDoList);
        toDoAdapter.setHasStableIds(true);
        recyclerView.setAdapter(toDoAdapter);

    }
    private ArrayList<ToDoItem> initTask(){
        db.open();
        Cursor c = db.getAllTask();
        if (c.moveToFirst()){
            do{
                toDoList.add(new ToDoItem(c.getString(0),c.getString(1)));
            }while (c.moveToNext());
        }
        db.close();

        return toDoList;
    }
    public void onClick(View view) {
        startActivityForResult(new Intent("com.example.recycler.SecondActivity"),request_Code);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == request_Code) {
            if (resultCode == RESULT_OK) {
                db.open();
                long id = db.addTask(data.getStringExtra("NEW_TASK"), data.getStringExtra("NEW_DEADLINE"));
                db.close();
                toDoList.add(new ToDoItem(data.getStringExtra("NEW_TASK"), data.getStringExtra("NEW_DEADLINE")));
                adapter = new ToDoAdapter(toDoList);
                this.recyclerView.setAdapter(adapter);
            }
        }
    }
}

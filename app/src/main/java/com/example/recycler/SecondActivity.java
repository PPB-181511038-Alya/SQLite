package com.example.recycler;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
    public void onClick(View view) {
        Intent data = new Intent();
        EditText txt_task_name = (EditText)findViewById(R.id.txtTask);
        EditText txt_task_deadline = (EditText)findViewById(R.id.txtDeadline);
        data.putExtra("NEW_TASK", txt_task_name.getText().toString());
        data.putExtra("NEW_DEADLINE", txt_task_deadline.getText().toString());
        setResult(RESULT_OK, data);
        //---closes the activity---
        finish();
    }
}

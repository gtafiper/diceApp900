package com.example.littlediceapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class LogView extends AppCompatActivity {

    public MainActivity mainAck = new MainActivity();
    public ListView logList;
    public ArrayList<Integer> log = new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_viev);

        final Intent intent = getIntent();
        final Bundle extraBundle = intent.getExtras();
        ArrayList<Integer> myIntegerArrayList = extraBundle.getIntegerArrayList("key");



        logList = findViewById(R.id.LVLog);
        log = myIntegerArrayList;

        Context context = getApplicationContext();

        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, myIntegerArrayList.toString(), duration);
        toast.show();

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                log );


        logList.setAdapter(arrayAdapter);



    }
}

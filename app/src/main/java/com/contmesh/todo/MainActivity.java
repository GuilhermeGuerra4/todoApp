package com.contmesh.todo;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ModalDialog.BottomSheetListener {

    MyRecyclerViewAdapter adapter;
    public ArrayList<Item> items;

    public void addNewTask(String task_name){
        items.add(new Item(task_name, false));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatBTC = findViewById(R.id.floatBTC);

        floatBTC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ModalDialog dia = new ModalDialog();
                dia.show(getSupportFragmentManager(), "teste");

            }
        });


        items = new ArrayList<>();
        items.add(new Item("task1", false));
        items.add(new Item("task2", true));
        items.add(new Item("task3", false));
        items.add(new Item("task4", true));


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        adapter = new MyRecyclerViewAdapter(items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void task_added(String task_name) {
        items.add(new Item(task_name, false));
        adapter.notifyDataSetChanged();
    }
}
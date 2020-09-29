package com.contmesh.todo;

import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

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
        Toolbar toolbar = findViewById(R.id.toolbarX);
        setSupportActionBar(toolbar);
        FloatingActionButton floatBTC = findViewById(R.id.floatBTC);

        floatBTC.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ModalDialog dia = new ModalDialog();
                dia.show(getSupportFragmentManager(), "teste");
            }
        });

        items = new ArrayList<>();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayout empty = (LinearLayout) findViewById(R.id.empty);
        adapter = new MyRecyclerViewAdapter(items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        if(items.isEmpty()){
            recyclerView.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public void task_added(String task_name) {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayout empty = (LinearLayout) findViewById(R.id.empty);
        items.add(new Item(task_name, false));
        adapter.notifyDataSetChanged();
        if(recyclerView.getVisibility() == View.INVISIBLE){
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }
    }
}
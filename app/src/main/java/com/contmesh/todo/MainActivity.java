package com.contmesh.todo;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import androidx.core.app.TaskStackBuilder;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ModalDialog.BottomSheetListener, MyRecyclerViewAdapter.Test {

    MyRecyclerViewAdapter adapter;
    public ArrayList<Item> items;

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

        items = new itemsController(MainActivity.this).getTodo();

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
        long last_id = new itemsController(MainActivity.this).create(task_name);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
        LinearLayout empty = (LinearLayout) findViewById(R.id.empty);

        items.add(new Item(last_id, task_name, false));

        adapter.notifyDataSetChanged();
        if(recyclerView.getVisibility() == View.INVISIBLE){
            recyclerView.setVisibility(View.VISIBLE);
            empty.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void task_deleted(int position) {
        Item item = items.get(position);
        new itemsController(MainActivity.this).delete(item.getId());
        items.remove(position);
        adapter.notifyDataSetChanged();
        Toast.makeText(MainActivity.this, "Item deleted", Toast.LENGTH_SHORT).show();
        if(items.isEmpty()){
            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
            LinearLayout empty = (LinearLayout) findViewById(R.id.empty);
            recyclerView.setVisibility(View.INVISIBLE);
            empty.setVisibility(View.VISIBLE);
        }
    }
}
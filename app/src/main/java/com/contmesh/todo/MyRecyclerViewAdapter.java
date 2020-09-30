package com.contmesh.todo;


import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;
import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {


    private ArrayList<Item> items;


    MyRecyclerViewAdapter(ArrayList<Item> items){
        this.items = items;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater mLayoutInflater = LayoutInflater.from(context);
        ViewHolder viewHolder;
        View view;
        view = mLayoutInflater.inflate(R.layout.todo_item, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Item item = items.get(position);
        holder.check.setText(item.getText());
        holder.check.setChecked(item.getChecked());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public interface Test{
        void task_deleted(int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox check;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {

                }

            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View view) {
                    Test test = (Test) view.getContext();
                    test.task_deleted(getAdapterPosition());
                    return false;
                }
            });

            check = itemView.findViewById(R.id.check);
        }


    }
}

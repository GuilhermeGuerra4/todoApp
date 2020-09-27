package com.contmesh.todo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
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
        View view = mLayoutInflater.inflate(R.layout.todo_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
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

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public CheckBox check;

        public ViewHolder(View itemView) {
            super(itemView);
            check = itemView.findViewById(R.id.check);
        }
    }
}

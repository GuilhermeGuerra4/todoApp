package com.contmesh.todo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class itemsController extends DatabaseHandler {
    public itemsController(@Nullable Context context) {
        super(context);
    }

    public long create(String task_name){
        ContentValues values = new ContentValues();
        values.put("task_name", task_name);
        SQLiteDatabase sql = this.getWritableDatabase();
        long last_id = sql.insert("items", null, values);
        sql.close();
        return last_id;
    }
    public int count() {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM items";
        int recordCount = db.rawQuery(sql, null).getCount();
        db.close();
        return recordCount;
    }

    public void delete(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String args[] = {String.valueOf(id)};
        db.delete("items", "id=?", args);
        db.close();
    }

    public ArrayList<Item> getTodo(){
        ArrayList<Item> items = new ArrayList<>();
        String sql = "SELECT * FROM items ORDER BY id ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                long id = Long.parseLong(cursor.getString(cursor.getColumnIndex("id")));
                String task_name = cursor.getString(cursor.getColumnIndex("task_name"));
                Item item = new Item(id, task_name, false);
                items.add(item);
            }while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return items;
    }
}

package com.contmesh.todo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "super_database";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE items (id INTEGER PRIMARY KEY AUTOINCREMENT, task_name TEXT)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int new_version, int old_version) {
        String sql = "DROP TABLE IF EXISTS items";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}

package com.example.demo2.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseConnection {

    private DatabaseHelper databaseHelper;
    private SQLiteDatabase database;

    public DatabaseConnection(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public void open() {
        database = databaseHelper.getWritableDatabase();
    }

    public void insertUser(String name) {
        String table = "user";
        ContentValues values = new ContentValues();
        values.put("name", name);
        database.insert(table, null, values);
    }

    public List<String> selectUsers() {
        String table = "user";
        Cursor result = database.query(table, null, null, null, null, null,
                null);
        List<String> users = new ArrayList<>();
        if (result != null && result.moveToFirst()) {
            do {
                users.add(result.getString(1));

            } while (result.moveToNext());
            result.close();
        }
        return users;
    }

    public String selectUser(int id) {
        String table = "user";
        String[] columns = {
                "name"
        };
        String selection = "id = ?";
        String[] selectionArgs = {
                Integer.toString(id)
        };
        Cursor result = database.query(table, columns, selection, selectionArgs,
                null, null, null);
        String user = null;
        if (result != null && result.moveToFirst()) {
            user = result.getString(0);
        }
        return user;
    }

    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final int databaseVersion = 1;
        private static final String databaseName = "data";

        public DatabaseHelper(Context context) {
            super(context, databaseName, null, databaseVersion);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS user (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT); ");
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion,
                              int newVersion) {
            database.execSQL("DROP TABLE IF EXISTS user;");
            onCreate(database);
        }
    }
}

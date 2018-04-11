package com.example.davidebelvedere.spesaapp.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.davidebelvedere.spesaapp.data.DatabaseHelper;

public class DBProductManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public static final String KEY_NAME = "name";
    private static final String DATABASE_TABLE = "product";

    public DBProductManager(Context context) {
        this.context = context;
    }

    public DBProductManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String name) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        return values;
    }

    public long addListProduct(String name) {
        ContentValues initialValues = createContentValues(name);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchAllProducts() {
        return database.query(DATABASE_TABLE, new String[]{KEY_NAME}, null, null, null, null, null);
    }
}
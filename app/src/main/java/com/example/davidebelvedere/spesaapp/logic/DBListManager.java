package com.example.davidebelvedere.spesaapp.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.davidebelvedere.spesaapp.data.DatabaseHelper;
import com.example.davidebelvedere.spesaapp.data.MainSingleton;

public class DBListManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    public static final String KEY_ID="_id";
    public static final String KEY_USERNAME = "username_fk";
    public static final String KEY_NAME = "name";
    private static final String DATABASE_TABLE = "list";

    public DBListManager(Context context) {
        this.context = context;
    }

    public DBListManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String name, String username_fk) {
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, name);
        values.put(KEY_USERNAME, username_fk);
        return values;
    }

    public long addList(String name,String username_fk) {
        ContentValues initialValues = createContentValues(name, username_fk);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchAllLists() {
        return database.query(DATABASE_TABLE,null, KEY_USERNAME + "=?", new String[]{MainSingleton.getCurrentUser().getUsername()}, null, null, null);
    }

    public Cursor fetchListById(int id) {
        return database.query(DATABASE_TABLE, new String[]{KEY_NAME}, KEY_ID + "=?", new String[]{""+id}, null, null, null);
    }

    public void deleteList(int id){
        database.delete(DATABASE_TABLE,KEY_ID+"=?",new String[]{""+id});
    }

    public void updateList(int item,String name,String username_fk) {

        ContentValues values = createContentValues(name, username_fk);

        if(username_fk!=""){
            database.update(DATABASE_TABLE,values,KEY_ID+"=?",new String[]{""+item});

        }
    }
}

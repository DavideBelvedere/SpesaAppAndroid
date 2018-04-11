package com.example.davidebelvedere.spesaapp.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.davidebelvedere.spesaapp.data.DatabaseHelper;

public class DBUserManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;


    private static final String DATABASE_TABLE = "user";
    public static final String KEY_USERNAME = "username";
    public static final String KEY_NAME = "name";
    public static final String KEY_SURNAME = "surname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    public DBUserManager(Context context) {
        this.context = context;
    }

    public DBUserManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(String username, String email, String name, String surname, String password) {
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, username);
        values.put(KEY_NAME, name);
        values.put(KEY_SURNAME, surname);
        values.put(KEY_EMAIL, email);
        values.put(KEY_PASSWORD, password);

        return values;
    }


    public long createUser(String username, String email, String name, String surname, String password) {
        ContentValues initialValues = createContentValues(username, email, name, surname, password);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public boolean updateUser(String username, String email, String name, String surname, String password) {
        ContentValues updateValues = createContentValues(username, email, name, surname, password);
        return database.update(DATABASE_TABLE, updateValues, KEY_USERNAME + "=" + username, null) > 0;
    }

    public boolean deleteUser(String username) {
        return database.delete(DATABASE_TABLE, KEY_USERNAME + "=" + username, null) > 0;
    }

    public Cursor fetchAllUser() {
        return database.query(DATABASE_TABLE, new String[]{KEY_USERNAME, KEY_NAME, KEY_SURNAME, KEY_EMAIL}, null, null, null, null, null);
    }

    public Cursor login(String username, String password) {
        return database.query(DATABASE_TABLE, new String[]{KEY_USERNAME, KEY_NAME, KEY_SURNAME, KEY_EMAIL}, KEY_USERNAME + "=? AND " + KEY_PASSWORD + "=? ", new String[]{username, password}, null, null, null);
    }

    public Cursor isAlreadyRegistered(String username) {
        return database.query(DATABASE_TABLE, new String[]{KEY_USERNAME}, KEY_USERNAME + "=?", new String[]{username}, null, null, null);
    }
}

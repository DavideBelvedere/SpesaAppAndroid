package com.example.davidebelvedere.spesaapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydatabase.db";
    private static final int DATABASE_VERSION = 1;
    // Lo statement SQL di creazione del database
    private static final String DATABASE_CREATE = "CREATE TABLE user (username VARCHAR(255) primary key, email VARCHAR(255),name VARCHAR(255),password VARCHAR(255), surname VARCHR(255));";
    private static final String DATABASE_CREATE2 = "CREATE TABLE list (id_ integer primary key autoincrement, name VARCHAR(255),username_fk VARCHAR(255),FOREIGN KEY (username_fk) REFERENCES user(username));";
    private static final String DATABASE_CREATE3 = "CREATE TABLE product (id_ integer primary key autoincrement, name VARCHAR(255));";
    private static final String DATABASE_CREATE4 = "CREATE TABLE list_product (id_list_fk integer, id_product_fk integer, quantita integer,PRIMARY KEY(id_list_fk,id_product_fk) ,FOREIGN KEY (id_list_fk) REFERENCES list(id_),FOREIGN KEY (id_product_fk) REFERENCES product(id_));";

    // Costruttore
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
        database.execSQL(DATABASE_CREATE2);
        database.execSQL(DATABASE_CREATE3);
        database.execSQL(DATABASE_CREATE4);
    }

    // Questo metodo viene chiamato durante l'upgrade del database,
    // ad esempio quando viene incrementato il numero di versione
    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS user");
        database.execSQL("DROP TABLE IF EXISTS list");
        database.execSQL("DROP TABLE IF EXISTS product");
        database.execSQL("DROP TABLE IF EXISTS list_product");
        onCreate(database);
    }
}
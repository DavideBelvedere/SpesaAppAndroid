package com.example.davidebelvedere.spesaapp.logic;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.davidebelvedere.spesaapp.data.DatabaseHelper;

public class DBListProductManager {
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Context context;

    private static final String DATABASE_TABLE = "list_product";
    private static final String DATABASE_TABLE1 = "product";
    private static final String KEY_ID_LIST = "id_list_fk";
    public static final String KEY_ID_PRODUCT = "_id";
    private static final String KEY_QUANTITA = "quantita";

    public DBListProductManager(Context context) {
        this.context = context;
    }

    public com.example.davidebelvedere.spesaapp.logic.DBListProductManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    private ContentValues createContentValues(int lista, int prodotto, int quantità) {
        ContentValues values = new ContentValues();
        if (lista != -1) {
            values.put(KEY_ID_LIST, lista);
        }
        values.put(KEY_ID_PRODUCT, prodotto);
        values.put(KEY_QUANTITA, quantità);
        return values;
    }


    public long addListProduct(int lista, int prodotto, int quantità) {

        ContentValues initialValues = createContentValues(lista, prodotto, quantità);
        return database.insertOrThrow(DATABASE_TABLE, null, initialValues);
    }

    public Cursor fetchAllProducts(int id) {
        return database.query(DATABASE_TABLE, null, KEY_ID_LIST + "=?", new String[]{"" + id}, null, null, null);
    }

    public void updateProduct(int listId, int productId, int quantità, int productIdOld) {
        ContentValues updateValues = createContentValues(listId, productId, quantità);
        database.update(DATABASE_TABLE, updateValues, KEY_ID_LIST + "=? AND " + KEY_ID_PRODUCT + "=?", new String[]{"" + listId, "" + productIdOld});
    }
}

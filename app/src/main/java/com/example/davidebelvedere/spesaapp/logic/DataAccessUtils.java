package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.data.ProductList;

/**
 * Created by corsista on 09/04/2018.
 */

public class DataAccessUtils {
    public static void initDataSource(Context context) {
        List<ProductList> lista =new ArrayList<>();

        DBUtility.initListDB(context);
        Cursor result= DBUtility.getDBListManager().fetchAllLists();

        if (result == null || result.getCount() == 0) {
        } else {
            while (result.moveToNext()){
                lista.add(new ProductList(result.getString(result.getColumnIndexOrThrow(DBListManager.KEY_NAME))));
            }
            result.close();
        }
        MainSingleton.getInstance().addItemList(lista);

    }
    public static List<ProductList> getDataSourceItemList(Context context){
        return MainSingleton.getInstance().getItemList();
    }

    public static int addItem(String item){
        List<ProductList> lista = MainSingleton.getInstance().getItemList();
        lista.add(new ProductList(item));
        MainSingleton.getInstance().addItemList(lista);
        return (lista.size()-1);
    }

    public static void removeItem(int position){
        List<ProductList> lista = MainSingleton.getInstance().getItemList();
        lista.remove(position);
    }

    public static void initDataSourceProduct(Context context) {
        List<String> lista =new ArrayList<>();

        DBUtility.initListDB(context);
        Cursor result= DBUtility.getDBListManager().fetchAllLists();

        if (result == null || result.getCount() == 0) {
        } else {
            while (result.moveToNext()){
                lista.add(result.getString(result.getColumnIndexOrThrow(DBListManager.KEY_NAME)));
            }
            result.close();
        }
        MainSingleton.getInstance().addItemProductList(lista);

    }

    public static int addItemProduct(String item){
        List<String> lista = MainSingleton.getInstance().getItemProductList();
        lista.add(item);
        MainSingleton.getInstance().addItemProductList(lista);
        return (lista.size()-1);
    }

    public static void removeItemProduct(int position){
        List<String> lista = MainSingleton.getInstance().getItemProductList();
        lista.remove(position);
    }
}

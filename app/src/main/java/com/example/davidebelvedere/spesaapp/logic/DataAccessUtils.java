package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.data.ProductList;

/**
 * Created by corsista on 09/04/2018.
 */

public class DataAccessUtils {
    public static void initDataSource(Context context) {
        List<ProductList> lista =MainSingleton.getInstance().getItemList();

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
}

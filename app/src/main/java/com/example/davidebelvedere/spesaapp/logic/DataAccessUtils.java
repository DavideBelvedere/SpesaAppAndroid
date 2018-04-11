package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.Toast;

import java.util.List;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.data.UserList;
import com.example.davidebelvedere.spesaapp.ui.activity.MainActivity;
import com.example.davidebelvedere.spesaapp.ui.activity.UserListActivity;

/**
 * Created by corsista on 09/04/2018.
 */

public class DataAccessUtils {
    public static void initDataSource(Context context) {
        List<UserList> lista = MainSingleton.getInstance().getItemList();

        DBUtility.initListDB(context);
        Cursor result= DBUtility.getDBListManager().fetchAllLists();

        if (result == null || result.getCount() == 0) {
        } else {
            while (result.moveToNext()){
                lista.add(new UserList(result.getString(result.getColumnIndexOrThrow(DBListManager.KEY_NAME))));
            }
            result.close();
        }
        MainSingleton.getInstance().addItemList(lista);

    }
    public static List<UserList> getDataSourceItemList(Context context){
        return MainSingleton.getInstance().getItemList();
    }

    public static void addItem(String item){
        List<UserList> lista = MainSingleton.getInstance().getItemList();
        lista.add(new UserList(item));
        MainSingleton.getInstance().addItemList(lista);
    }

    public static void removeItem(int position){
        List<UserList> lista = MainSingleton.getInstance().getItemList();
        lista.remove(position);
    }
}

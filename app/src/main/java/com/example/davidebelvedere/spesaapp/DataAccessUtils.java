package com.example.davidebelvedere.spesaapp;

import android.content.Context;

import java.util.List;

/**
 * Created by corsista on 09/04/2018.
 */

public class DataAccessUtils {
    public static void initDataSource(Context context) {
        List<UserList> lista =DataContainerSingleton.getInstance().getItemList();
        lista.add(new UserList("Daily shopz"));
        lista.add(new UserList("Weekly shopz"));
        lista.add(new UserList("Monthly shopz"));
        lista.add(new UserList("Schifezze"));
        DataContainerSingleton.getInstance().setItemList(lista);


    }
    public static List<UserList> getDataSourceItemList(Context context){
        return DataContainerSingleton.getInstance().getItemList();
    }

    public static void addItem(String item){
        List<UserList> lista = DataContainerSingleton.getInstance().getItemList();
        lista.add(new UserList(item));
        DataContainerSingleton.getInstance().setItemList(lista);
    }

    public static void removeItem(int position){
        List<UserList> lista = DataContainerSingleton.getInstance().getItemList();
        lista.remove(position);
    }
}

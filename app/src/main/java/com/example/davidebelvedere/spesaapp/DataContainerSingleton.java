package com.example.davidebelvedere.spesaapp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by corsista on 09/04/2018.
 */

class DataContainerSingleton {
    private static final DataContainerSingleton ourInstance = new DataContainerSingleton();


    private List<UserList> itemList;
    static DataContainerSingleton getInstance() {
        return ourInstance;
    }

    private DataContainerSingleton() {
        this.itemList= new ArrayList<>();
    }
    public void setItemList(List<UserList> itemList) {
        this.itemList = itemList;
    }

    public List<UserList> getItemList() {
        return this.itemList;
    }
}

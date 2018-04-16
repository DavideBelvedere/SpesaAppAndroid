package com.example.davidebelvedere.spesaapp.data;


import com.example.davidebelvedere.spesaapp.logic.DBListManager;
import com.example.davidebelvedere.spesaapp.logic.DBListProductManager;
import com.example.davidebelvedere.spesaapp.logic.DBProductManager;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;

import java.util.ArrayList;
import java.util.List;

public class MainSingleton {
    private static DBUserManager dbUserManager;
    private static User currentUser;
    private List<ProductList> itemList;
    private List<String> itemProductList;
    private static DBListManager dbListManager;
    private static DBListProductManager dbListProductManager;
    private static DBProductManager dbProductManager;
    private static MainSingleton mySingleton = new MainSingleton();

    private MainSingleton() {
        itemList = new ArrayList<>();
    }

    public static MainSingleton getInstance() {
        return mySingleton;
    }


    public DBUserManager getDBUserManager() {
        return dbUserManager;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        MainSingleton.currentUser = currentUser;
    }

    public DBListManager getDBListManager() {
        return dbListManager;
    }

    public void setDBUserManager(DBUserManager dbManager) {
        MainSingleton.dbUserManager = dbManager;
    }

    public void setDBListManager(DBListManager dbListManager) {
        MainSingleton.dbListManager = dbListManager;
    }

    public void addItemList(List<ProductList> itemList) {
        this.itemList = itemList;
    }

    public void addItemProductList(List<String> itemProductList) {
        this.itemProductList = itemProductList;
    }

    public List<ProductList> getItemList() {
        return this.itemList;
    }

    public List<String> getItemProductList() {
        return this.itemProductList;
    }

    public DBProductManager getDBProductManager() {
        return dbProductManager;
    }
    public void setDBProductManager(DBProductManager dbProductManager) {
        MainSingleton.dbProductManager = dbProductManager;
    }

    public DBListProductManager getDBListProductManager() {
        return dbListProductManager;
    }
    public void setDBListProductManager(DBListProductManager dbListProductManager) {
        MainSingleton.dbListProductManager = dbListProductManager;
    }

}
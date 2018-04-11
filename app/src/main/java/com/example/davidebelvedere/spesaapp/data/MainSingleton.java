package com.example.davidebelvedere.spesaapp.data;



import com.example.davidebelvedere.spesaapp.logic.DBListManager;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;

import java.util.ArrayList;
import java.util.List;

public class MainSingleton {
    private static DBUserManager dbUserManager;
    private static User currentUser;
    private List<UserList> itemList;
    private static DBListManager dbListManager;
    private static MainSingleton mySingleton = new MainSingleton();

    private MainSingleton() {

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

    public void setDBListManager(DBListManager dbManager) {
        MainSingleton.dbListManager = dbManager;
    }

    public void addItemList(List<UserList> itemList) {
        this.itemList = itemList;
    }

    public List<UserList> getItemList() {
        return this.itemList;
    }

}
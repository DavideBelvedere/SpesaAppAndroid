package com.example.davidebelvedere.spesaapp.data;

import com.example.davidebelvedere.spesaapp.logic.DBUserManager;

public class MainSingleton {
    private static DBUserManager dbUserManager;
    private static User currentUser;
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

    public void setDBUserManager(DBUserManager dbManager) {
        MainSingleton.dbUserManager = dbManager;
    }
}
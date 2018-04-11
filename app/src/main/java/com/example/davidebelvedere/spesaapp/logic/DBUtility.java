package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;

public class DBUtility {
    public static void initUserDB(Context context) {

        MainSingleton.getInstance().setDBUserManager(new DBUserManager(context));
        DBUtility.getDBUserManager().open();


    }

    public static DBUserManager getDBUserManager() {
        return MainSingleton.getInstance().getDBUserManager();
    }

}

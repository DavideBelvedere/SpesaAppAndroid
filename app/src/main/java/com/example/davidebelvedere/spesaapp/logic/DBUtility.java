package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;
import android.net.Uri;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;

public class DBUtility {
    public static void initUserDB(Context context) {

        MainSingleton.getInstance().setDBUserManager(new DBUserManager(context));
        DBUtility.getDBUserManager().open();


    }


    public static void initListDB(Context context) {

        MainSingleton.getInstance().setDBListManager(new DBListManager(context));
        DBUtility.getDBListManager().open();


    }

    public static void initProductDB(Context context) {

        MainSingleton.getInstance().setDBProductManager(new DBProductManager(context));
        DBUtility.getDBProductManager().open();


    }

    public static void initListProductDB(Context context) {

        MainSingleton.getInstance().setDBListProductManager(new DBListProductManager(context));
        DBUtility.getDBListProductManager().open();


    }
    public static DBUserManager getDBUserManager() {
        return MainSingleton.getInstance().getDBUserManager();
    }

    public static DBListManager getDBListManager() {
        return MainSingleton.getInstance().getDBListManager();
    }
    public static String getStringFromUri(Uri uri){
        String stringUri;
        stringUri = uri.toString();
        return stringUri;
    }
    public static Uri getUriFromString(String stringUri){
        Uri uri = Uri.parse(stringUri);
        return uri;
    }

    public static DBProductManager getDBProductManager() {
        return MainSingleton.getInstance().getDBProductManager();
    }

    public static DBListProductManager getDBListProductManager() {
        return MainSingleton.getInstance().getDBListProductManager();
    }
}

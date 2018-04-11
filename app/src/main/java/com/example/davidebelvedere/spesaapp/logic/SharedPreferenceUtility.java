package com.example.davidebelvedere.spesaapp.logic;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.format.DateFormat;

import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.data.User;
import com.example.davidebelvedere.spesaapp.ui.activity.MainActivity;
import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Locale;

public class SharedPreferenceUtility {
    private static final String FILEONPREFERENCES = "UserInfo";
    private static final String TOKEN = "token";
    private static final String USER = "user";

    public static void writeLoggedUserOnSharedPref(Context context, User currentUser) {
        Gson gson = new Gson();
        SharedPreferences sharedPref = context.getSharedPreferences(FILEONPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(TOKEN, "" + System.currentTimeMillis());
        editor.putString(USER, gson.toJson(currentUser));
        editor.apply();

    }

    public static boolean isUserValid(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILEONPREFERENCES, Context.MODE_PRIVATE);

        String token = sharedPref.getString(TOKEN, "");
        if (token.equals("")) {
            return false;
        } else if ((getDay(Long.parseLong(token)) != getDay(System.currentTimeMillis())) || (getMonth(Long.parseLong(token)) != getMonth(System.currentTimeMillis()))) {
            return false;
        } else {
            return true;
        }

    }

    public static void saveCurrentUser(Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(FILEONPREFERENCES, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String user = sharedPref.getString(USER, "");
        if (user != "") {
            MainSingleton.setCurrentUser(gson.fromJson(user, User.class));
        }
    }

    private static int getDay(long time) {
        Calendar cal = Calendar.getInstance(Locale.ITALY);
        cal.setTimeInMillis(time);

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    private static int getMonth(long time) {
        Calendar cal = Calendar.getInstance(Locale.ITALY);
        cal.setTimeInMillis(time);

        return cal.get(Calendar.MONTH);
    }


}

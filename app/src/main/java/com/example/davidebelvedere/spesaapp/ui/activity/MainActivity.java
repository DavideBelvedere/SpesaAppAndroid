package com.example.davidebelvedere.spesaapp.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.data.User;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;
import com.example.davidebelvedere.spesaapp.logic.DataAccessUtils;
import com.example.davidebelvedere.spesaapp.logic.SharedPreferenceUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SharedPreferenceUtility.isUserValid(getApplicationContext())) {
            SharedPreferenceUtility.saveCurrentUser(getApplicationContext());
            Intent intent = null;
            intent = new Intent(MainActivity.this, UserListActivity.class);
            startActivity(intent);

            //Toast.makeText(getApplicationContext(), MainSingleton.getCurrentUser().toString(), Toast.LENGTH_LONG).show();
        } else {
            setContentView(R.layout.activity_main);

            final EditText username = (EditText) findViewById(R.id.username);
            final EditText password = (EditText) findViewById(R.id.password);
            Button accedi = (Button) findViewById(R.id.accedi);
            Button registrati = (Button) findViewById(R.id.registrati);


            accedi.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DBUtility.initUserDB(getApplicationContext());
                    Cursor result = DBUtility.getDBUserManager().login(String.valueOf(username.getText()), String.valueOf(password.getText()));

                    if (result == null || result.getCount() == 0) {
                        Toast.makeText(getApplicationContext(), "PASSWORD O USERNAME ERRATI", Toast.LENGTH_LONG).show();
                    } else {
                        result.moveToFirst();
                        //Toast.makeText(getApplicationContext(), "" + result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_NAME)), Toast.LENGTH_LONG).show();
                        User user = new User(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_USERNAME)), result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_NAME)), result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_SURNAME)), result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_EMAIL)), result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_IMGPROFILE)));
                        SharedPreferenceUtility.writeLoggedUserOnSharedPref(getApplicationContext(), user);
                        SharedPreferenceUtility.saveCurrentUser(getApplicationContext());
                        Intent intent = null;
                        if ((result.getInt(result.getColumnIndexOrThrow(DBUserManager.KEY_USERNAME))) == 0) {
                            intent = new Intent(MainActivity.this, UserListActivity.class);

                        } else {
                             intent = new Intent(MainActivity.this, UserListActivity.class);
                        }
                        startActivity(intent);
                    }


                }
            });

            registrati.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBUtility.getDBUserManager().close();
    }
}

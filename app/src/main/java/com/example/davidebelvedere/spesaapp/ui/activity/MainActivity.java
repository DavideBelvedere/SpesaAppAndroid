package com.example.davidebelvedere.spesaapp.ui.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
                    Toast.makeText(getApplicationContext(), "" + result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_NAME)), Toast.LENGTH_LONG).show();
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DBUtility.getDBUserManager().close();
    }
}

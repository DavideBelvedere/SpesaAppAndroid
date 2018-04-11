package com.example.davidebelvedere.spesaapp.ui.activity;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText name = (EditText) findViewById(R.id.nome);
        final EditText surname = (EditText) findViewById(R.id.cognome);
        final CircleImageView imgProfile = (CircleImageView) findViewById(R.id.profile_image);

        Button already = (Button) findViewById(R.id.already);
        Button registrati = (Button) findViewById(R.id.registrati);
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath();
        Toast.makeText(getApplicationContext(),filepath, Toast.LENGTH_SHORT).show();
        registrati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBUtility.initUserDB(getApplicationContext());
                Cursor result = DBUtility.getDBUserManager().isAlreadyRegistered(String.valueOf(username.getText()));
                if (result == null || result.getCount() == 0) {
                    DBUtility.getDBUserManager().createUser(String.valueOf(username.getText()), String.valueOf(email.getText()), String.valueOf(name.getText()), String.valueOf(surname.getText()), String.valueOf(password.getText()),0,"");


                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "USERNAME GIA' ESISTENTE INSERISCINE UN'ALTRO", Toast.LENGTH_LONG).show();
                }
                try {
                    result.close();
                } catch (NullPointerException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                DBUtility.getDBUserManager().close();

            }
        });

        already.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });

        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}

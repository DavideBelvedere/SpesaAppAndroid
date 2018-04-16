package com.example.davidebelvedere.spesaapp.ui.activity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.CursorLoader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.logic.DBUserManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        if (toolbar != null) {

            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        TextView title = (TextView) findViewById(R.id.title);
        TextView name = (TextView) findViewById(R.id.name);
        TextView surname = (TextView) findViewById(R.id.surname);
        TextView username = (TextView) findViewById(R.id.username);
        TextView email = (TextView) findViewById(R.id.email);
        CircleImageView imgProfile = (CircleImageView) findViewById(R.id.imgProfile);
        DBUserManager dbUserManager = new DBUserManager(this);
        dbUserManager.open();
        String usernameSearched=MainSingleton.getCurrentUser().getUsername();
        Cursor result = dbUserManager.getUser(usernameSearched);
        if(result.moveToFirst()){
            title.setText("Benvenuto "+result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_USERNAME)));
            username.setText(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_USERNAME)));
            name.setText(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_NAME)));
            surname.setText(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_SURNAME)));
            email.setText(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_EMAIL)));
            //Log.d("uri2",""+DBUtility.getUriFromString(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_IMGPROFILE))));
            //imgProfile.setImageURI(DBUtility.getUriFromString(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_IMGPROFILE))));
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), DBUtility.getUriFromString(result.getString(result.getColumnIndexOrThrow(DBUserManager.KEY_IMGPROFILE))));
                imgProfile.setImageBitmap(bitmap);

            }catch (IOException e){
                e.printStackTrace();
            }

        } else {
            Toast.makeText(this, "Errore nel recupero dati", Toast.LENGTH_LONG).show();
        }
        dbUserManager.close();

    }



}

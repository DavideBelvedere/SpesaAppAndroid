package com.example.davidebelvedere.spesaapp.ui.activity;

import android.Manifest;
import android.app.Activity;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegisterActivity extends Activity {
    CircleImageView imgProfile;
    String imgUri = DBUtility.getStringFromUri(Uri.parse("android.resource://com.example.davidebelvedere.spesaapp/drawable/profile"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final EditText email = (EditText) findViewById(R.id.email);
        final EditText name = (EditText) findViewById(R.id.nome);
        final EditText surname = (EditText) findViewById(R.id.cognome);
        imgProfile = (CircleImageView) findViewById(R.id.profile_image);

        Button already = (Button) findViewById(R.id.already);
        Button registrati = (Button) findViewById(R.id.registrati);


        registrati.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DBUtility.initUserDB(getApplicationContext());
                Cursor result = DBUtility.getDBUserManager().isAlreadyRegistered(String.valueOf(username.getText()));
                if (result == null || result.getCount() == 0) {
                    DBUtility.getDBUserManager().createUser(String.valueOf(username.getText()), String.valueOf(email.getText()), String.valueOf(name.getText()), String.valueOf(surname.getText()), String.valueOf(password.getText()), 0, imgUri);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "USERNAME GIA' ESISTENTE INSERISCINE UN'ALTRO", Toast.LENGTH_LONG).show();
                }
                try {
                    result.close();
                } catch (NullPointerException e) {
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
                if (ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.READ_EXTERNAL_STORAGE) ==
                        PackageManager.PERMISSION_GRANTED) {
                    dispatchGetPictureFromGallery();
                } else {
                    ActivityCompat.requestPermissions(RegisterActivity.this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            0);
                }
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 0: {

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchGetPictureFromGallery();
                } else {

                }
                return;
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                Uri photoUri = data.getData();
                Log.d("uri", ""+photoUri);
                String currentPhotoPath = photoUri.getPath();
                imgProfile.setImageURI(photoUri);
                imgUri = DBUtility.getStringFromUri(photoUri);
            } else {
                Log.i(RegisterActivity.class.getName(), "Result code != OK -> request code: " +
                        requestCode);
            }
        }
    }

    protected void dispatchGetPictureFromGallery() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
        this.startActivityForResult(intent, 1);
    }

}

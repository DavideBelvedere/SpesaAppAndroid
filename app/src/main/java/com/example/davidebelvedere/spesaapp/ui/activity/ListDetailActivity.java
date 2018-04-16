package com.example.davidebelvedere.spesaapp.ui.activity;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;
import com.example.davidebelvedere.spesaapp.logic.DataAccessUtils;
import com.example.davidebelvedere.spesaapp.ui.adapter.MyCursorAdapter2;

/**
 * Created by corsista on 11/04/2018.
 */

class ListDetailActivity extends AppCompatActivity {

    private MyCursorAdapter2 customAdapter;
    private ListView lista;
    private int listId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_detail_layout);
        DataAccessUtils.initDataSourceProduct(this);
        Bundle bundle= getIntent().getExtras();
        listId=bundle.getInt("idLista");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddProductAlertDialog();
            }
        });

        DBUtility.initListProductDB(this);
        Cursor result = DBUtility.getDBListProductManager().fetchAllProducts(listId);
        customAdapter = new MyCursorAdapter2(this,result);

        lista= findViewById(R.id.listView);
        lista.setAdapter(customAdapter);
    }

    public void showAddProductAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add product");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBUtility.initProductDB(getApplicationContext());
                DBUtility.getDBProductManager().addListProduct(input.getText().toString());

                DataAccessUtils.addItemProduct(input.getText().toString());
                Cursor result3= DBUtility.getDBProductManager().fetchProductByName(input.getText().toString());
                result3.moveToFirst();
                DBUtility.getDBListProductManager().addListProduct(listId,result3.getInt(0),2);
                Cursor result = DBUtility.getDBProductManager().fetchAllProducts();
                customAdapter = new MyCursorAdapter2(getApplicationContext(),result);
                lista.setAdapter(customAdapter);
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBUtility.getDBProductManager().removeAll();
                Cursor result = DBUtility.getDBProductManager().fetchAllProducts();
                customAdapter = new MyCursorAdapter2(getApplicationContext(),result);
                lista.setAdapter(customAdapter);
                dialog.cancel();
            }
        });
        builder.show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_layout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
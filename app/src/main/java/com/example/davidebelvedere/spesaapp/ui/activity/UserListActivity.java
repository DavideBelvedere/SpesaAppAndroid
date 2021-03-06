package com.example.davidebelvedere.spesaapp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.SwipeController;
import com.example.davidebelvedere.spesaapp.data.MainSingleton;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;
import com.example.davidebelvedere.spesaapp.logic.DataAccessUtils;
import com.example.davidebelvedere.spesaapp.logic.SharedPreferenceUtility;
import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.SharedPreferenceUtility;
import com.example.davidebelvedere.spesaapp.ui.adapter.RecyclerAdapter;
import com.example.davidebelvedere.spesaapp.SwipeController;

/**
 * Created by corsista on 09/04/2018.
 */

public class UserListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    RecyclerAdapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataAccessUtils.initDataSource(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list_layout);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddListAlertDialog();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerAdapter = new RecyclerAdapter(DataAccessUtils.getDataSourceItemList(this),
                new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int item) {
                        Intent intent = new Intent(UserListActivity.this, ListDetailActivity.class);
                        intent.putExtra("idLista", item);
                        startActivity(intent);
                    }
                }, new RecyclerAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(int item,int position) {
                showListUpdatewAlertDialog(item,position);
            }
        });
        recyclerView.setAdapter(recyclerAdapter);
        SwipeController swipeController = new SwipeController(this);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void showListUpdatewAlertDialog(final int item, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Change List name");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DBUtility.initListDB(getApplicationContext());
                DBUtility.getDBListManager().updateList(item,input.getText().toString(), MainSingleton.getCurrentUser().getUsername());
                DataAccessUtils.changeItem(position,input.getText().toString());
                recyclerAdapter.notifyItemChanged(position);
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            return true;
        } else if(id == R.id.logout){
            SharedPreferenceUtility.logout(this);
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
            return true;
        }else {

            return super.onOptionsItemSelected(item);
        }
    }
    public void showAddListAlertDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add list");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            /*   int pos= DataAccessUtils.addItem(input.getText().toString());
                DBUtility.getDBListManager().addList(input.getText().toString(), MainSingleton.getCurrentUser().getUsername());*/
                int id = (int) DBUtility.getDBListManager().addList(input.getText().toString(), MainSingleton.getCurrentUser().getUsername());
                int pos = DataAccessUtils.addItem(input.getText().toString(), id);
                dialog.cancel();
                recyclerAdapter.notifyItemInserted(pos);
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(DBUtility.getDBUserManager()!=null) {
            DBUtility.getDBUserManager().close();
        }
    }
}

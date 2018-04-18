package com.example.davidebelvedere.spesaapp.ui.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.DBListProductManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;
import com.example.davidebelvedere.spesaapp.logic.DataAccessUtils;
import com.example.davidebelvedere.spesaapp.logic.SharedPreferenceUtility;

public class MyCursorAdapter2 extends CursorAdapter {
    TextView name;
    final int listId;

    public MyCursorAdapter2(Context context, Cursor cursor,int listId) {
        super(context, cursor, 0);
        this.listId=listId;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_detail_layout, parent, false);
    }

    @Override
    public void bindView(View view, final Context context, final Cursor cursor) {
        // Find fields to populate in inflated template

        name = (TextView) view.findViewById(R.id.name);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= Toast.makeText(context,"click",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        view.setOnLongClickListener(new View.OnLongClickListener() {

            public int getListId() {
                return listId;
            }

            @Override
            public boolean onLongClick(View v) {
                showEditAlertDialog(cursor.getPosition(),context,this.getListId());
                return false;
            }
        });

        Cursor result= DBUtility.getDBProductManager().fetchProductById(cursor.getString(cursor.getColumnIndexOrThrow(DBListProductManager.KEY_ID_PRODUCT)));
        result.moveToFirst();
        name.setText(result.getString(0));

    }

    private void showEditAlertDialog(final int position, final Context context, final int listId) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Edit Product");

        final EditText input = new EditText(context);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);
        builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.setNeutralButton("Delete Product", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
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


}
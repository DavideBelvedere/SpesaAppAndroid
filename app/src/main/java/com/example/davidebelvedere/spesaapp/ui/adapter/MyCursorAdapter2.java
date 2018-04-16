package com.example.davidebelvedere.spesaapp.ui.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.logic.DBListProductManager;
import com.example.davidebelvedere.spesaapp.logic.DBUtility;

public class MyCursorAdapter2 extends CursorAdapter {
    public MyCursorAdapter2(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        ImageView imageView= (ImageView) view.findViewById(R.id.imageView);
        TextView name = (TextView) view.findViewById(R.id.textView);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);


        Cursor result= DBUtility.getDBProductManager().fetchProductById(cursor.getString(cursor.getColumnIndexOrThrow(DBListProductManager.KEY_ID_PRODUCT)));
        result.moveToFirst();
        name.setText(result.getString(0));

    }


}
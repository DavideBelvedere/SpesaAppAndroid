package com.example.davidebelvedere.spesaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import com.example.davidebelvedere.spesaapp.logic.DataAccessUtils;

/**
 * Created by corsista on 09/04/2018.
 */

public class SwipeController extends Callback {

    private Context context;

    public SwipeController(Context context) {
        super();
        this.context = context;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        return makeMovementFlags(0,ItemTouchHelper.LEFT);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        showRemoveListAlertDialog(viewHolder);
    }
    public void showRemoveListAlertDialog(final RecyclerView.ViewHolder viewHolder) {

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Rimozione");
        builder.setMessage("Sei sicuro si voler rimuovere l'item");
        builder.setPositiveButton(R.string.alert_ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {


                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                DataAccessUtils.removeItem(viewHolder.getPosition());
                dialog.cancel();
            }
        });
        builder.show();
    }
}

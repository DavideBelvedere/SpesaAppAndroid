package com.example.davidebelvedere.spesaapp;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

import com.example.davidebelvedere.spesaapp.logic.DBUtility;
import com.example.davidebelvedere.spesaapp.ui.adapter.MyCursorAdapter2;
import com.example.davidebelvedere.spesaapp.ui.adapter.RecyclerAdapter;

/**
 * Created by corsista on 09/04/2018.
 */

public class SwipeController extends Callback {

    private Context context;
    RecyclerView recyclerView;
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
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        this.recyclerView=recyclerView;
        float translationX = Math.min(-dX, viewHolder.itemView.getWidth() / 6);
        viewHolder.itemView.setTranslationX(-translationX);
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
                DBUtility.initListDB(context);
                RecyclerAdapter adapter = (RecyclerAdapter) recyclerView.getAdapter();
                DBUtility.getDBListManager().deleteList(adapter.getItemByPosition(viewHolder.getLayoutPosition()).getId());
                adapter.removeAt(viewHolder.getAdapterPosition());
                dialog.cancel();
            }
        });
        builder.setNegativeButton(R.string.alert_cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                viewHolder.itemView.setTranslationX(0);

            }
        });
        builder.show();
    }
}

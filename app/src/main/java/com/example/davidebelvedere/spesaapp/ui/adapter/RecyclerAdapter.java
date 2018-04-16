package com.example.davidebelvedere.spesaapp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidebelvedere.spesaapp.R;
import com.example.davidebelvedere.spesaapp.data.ProductList;

import java.util.List;

/**
 * Created by corsista on 19/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(int item);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(int item);
    }

    private List<ProductList> dataSet;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longClickListener;

    public RecyclerAdapter(List<ProductList> dataSet, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
        this.dataSet = dataSet;
        this.listener = listener;
        this.longClickListener = longClickListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView image;
        private TextView text;
        public ViewHolder(View itemView) {
            super(itemView);
        }
        public void bind(final int itemId, final OnItemClickListener listener, final OnItemLongClickListener longClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(itemId);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClick(itemId);
                    return true;
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LinearLayout linearLayout;
        linearLayout= (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder= new ViewHolder(linearLayout);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(dataSet.get(position).getId(),listener,longClickListener);
        ImageView imageView = holder.itemView.findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);
        TextView textView = holder.itemView.findViewById(R.id.textView);
        textView.setText(dataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}
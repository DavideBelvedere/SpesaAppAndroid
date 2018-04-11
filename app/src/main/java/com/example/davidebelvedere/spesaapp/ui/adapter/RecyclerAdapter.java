package com.example.davidebelvedere.spesaapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.davidebelvedere.spesaapp.data.UserList;

import java.util.List;

/**
 * Created by corsista on 19/03/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    public interface OnItemClickListener {
        void onItemClick(String item);
    }
    public interface OnItemLongClickListener{
        void onItemLongClick(String item);
    }

    private List<UserList> dataSet;
    private final OnItemClickListener listener;
    private final OnItemLongClickListener longClickListener;

    public RecyclerAdapter(List<UserList> dataSet, OnItemClickListener listener, OnItemLongClickListener longClickListener) {
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
        public void bind(final String item, final OnItemClickListener listener,final OnItemLongClickListener longClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.onItemLongClick(item);
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
        holder.bind(dataSet.get(position).getName(),listener,longClickListener);
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
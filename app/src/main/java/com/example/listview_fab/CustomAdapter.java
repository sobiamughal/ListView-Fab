package com.example.listview_fab;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {


    ArrayList<LvItem> arrayList;
    Context context;
    Dialog myDialog;
    private OnClickListener monClickListener;


    public CustomAdapter(Context context, ArrayList<LvItem> arrayList, OnClickListener onClickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.monClickListener = onClickListener;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.lvitem, viewGroup, false);
        final MyViewHolder vHolder = new MyViewHolder(v, monClickListener);

        // myDialog = new Dialog(context);
        //myDialog.setContentView(R.layout.row);



        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int poistion) {
        holder.tv_title.setText(arrayList.get(poistion).getName());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout item_name;
        private TextView tv_title;
        OnClickListener onClickListener;

        public MyViewHolder(View view, OnClickListener onClickListener) {
            super(view);

            item_name = (LinearLayout) view.findViewById(R.id.item_id);
            tv_title = (TextView) view.findViewById(R.id.txtname);
            this.onClickListener = onClickListener;

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onItemClick(int poistion);
    }

}

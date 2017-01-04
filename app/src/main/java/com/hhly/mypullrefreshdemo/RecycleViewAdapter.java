package com.hhly.mypullrefreshdemo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * @创建者 frank
 * @时间 2017/1/4 10:07
 * @描述：${TODO}
 */

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private ArrayList<String> mList = new ArrayList<>();


    public RecycleViewAdapter(ArrayList<String> list) {
        mList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {



        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView mTextView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.text);
        }
    }
}

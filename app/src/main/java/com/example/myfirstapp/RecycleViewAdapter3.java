package com.example.myfirstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class RecycleViewAdapter3 extends RecyclerView.Adapter<RecycleViewAdapter3.MyViewHolder> {

    private Context mContext;
    private List<Hotel> mData;


    public RecycleViewAdapter3(Context mContext, List<Hotel> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;

        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.content, parent, false);
        return new MyViewHolder(view);
    }



    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.nam.setText(mData.get(position).getN());
        holder.address.setText(mData.get(position).getAddress());
        holder.rat.setText(mData.get(position).getRate());
        Picasso.with(mContext).load(mData.get(position).getImage()).into(holder.image);

    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nam;
        TextView rat,address;
        ImageView image;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);

            nam= (TextView) itemView.findViewById(R.id.text1);
            address = (TextView) itemView.findViewById(R.id.book_title_id);
            layout= (LinearLayout) itemView.findViewById(R.id.linear);
            rat=itemView.findViewById(R.id.rate);
           image=itemView.findViewById(R.id.image);


        }
    }

    @Override
    public long getItemId(int position) {


        return super.getItemId(position);
    }




}


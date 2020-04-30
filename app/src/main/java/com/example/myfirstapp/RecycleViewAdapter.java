package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Book> mData;


    public RecycleViewAdapter(Context mContext, List<Book> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.cardview_item_book, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        holder.tv_book_title.setText(mData.get(position).getTitle());
        Picasso.with(mContext).load(mData.get(position).getThumbnail()).into(holder.img_book_thumbnail);
       holder.cardView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               switch (position)
               {
                   case 0:
                       Intent i1 = new Intent(mContext, Main2Activity.class);
                       String link1="https://api.jsonbin.io/b/5eaaa8c64c87c3359a643e12";
                       i1.putExtra("link",link1);
                       mContext.startActivity(i1);
                       break;

                   case 1:
                       Intent  i2= new Intent(mContext, Main2Activity.class);
                       String link2="https://api.jsonbin.io/b/5ea830714c87c3359a6327dd";
                       i2.putExtra("link",link2);
                       mContext.startActivity(i2);
                       break;
                   case 2:
                       Intent i3 = new Intent(mContext, Main2Activity.class);
                       String link3="https://api.jsonbin.io/b/5eaab41f66e603359fe13bb6";
                       i3.putExtra("link",link3);
                       mContext.startActivity(i3);
                       break;
                   case 3:
                       Intent i4 = new Intent(mContext, Main2Activity.class);
                       String link4="https://api.jsonbin.io/b/5eaab96f07d49135ba4835c9";
                       i4.putExtra("link",link4);
                       mContext.startActivity(i4);
                       break;
                   case 4:
                       Intent i5 = new Intent(mContext, Main2Activity.class);
                       String link5="https://api.jsonbin.io/b/5eaae4f866e603359fe152b1";
                       i5.putExtra("link",link5);
                       mContext.startActivity(i5);
                       break;
                   case 5:
                       Intent i6 = new Intent(mContext, Main2Activity.class);
                       String link6="https://api.jsonbin.io/b/5eaaea294c87c3359a645cba";
                       i6.putExtra("link",link6);
                       mContext.startActivity(i6);
                       break;
                   case 6:

                       Intent i7= new Intent(mContext, Main2Activity.class);
                       String link7="https://api.jsonbin.io/b/5eaaef8b66e603359fe158c5";
                       i7.putExtra("link",link7);
                       mContext.startActivity(i7);
                       break;
                   case 7:
                       Intent i8 = new Intent(mContext, Main2Activity.class);
                       String link8="https://api.jsonbin.io/b/5eaafbdd07d49135ba485724";
                       i8.putExtra("link",link8);
                       mContext.startActivity(i8);
                       break;
               }

           }
       });

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_book_title;
        ImageView img_book_thumbnail;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);

            tv_book_title = (TextView) itemView.findViewById(R.id.book_title_id);
            img_book_thumbnail = (ImageView) itemView.findViewById(R.id.book_img_id);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);


        }
    }

}


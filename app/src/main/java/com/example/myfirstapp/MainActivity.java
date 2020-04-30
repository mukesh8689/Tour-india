package com.example.myfirstapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.ArrayList;
import java.util.List;

import static com.squareup.picasso.Picasso.with;


public class MainActivity extends AppCompatActivity {
    List<Book> lstBook ;

    private final String link1 = "https://www.holidify.com/images/cmsuploads/compressed/1831_20190228134108.jpg";
    private final String link2= "https://www.holidify.com/images/bgImages/LAKSHADWEEP-ISLANDS.jpg";
    private final String link3 = "https://holidify.com/images/attr_wiki/compressed/attr_wiki_1210.jpg";
    private final String link4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcQjtWYSABxJlc3S7EsxhPAZgtwC3ySZM96QckeFs7q2exhHYyTy";
    private final String link5  ="https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBgYk7KI_hm4WSjn1C2jwQ917Xbt5olHOPESSFF5sQduAwEniM";
    private final String link6  = "https://www.holidify.com/images/cmsuploads/compressed/Taj-Mahal_20190807173912.jpg";
    private final String link7  = "https://www.oyorooms.com/blog/wp-content/uploads/2018/07/shutterstock_655466914.jpg";
    private final String link8  =  "https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSUM4wIRgQKr1LoOqUF9vGDAsZj_2oBBUm1fpcZmenOKsGPffqE";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstBook = new ArrayList<>();




        lstBook.add(new Book("Top Destinations",link1,null));
        lstBook.add(new Book("Beaches",link2,null));
        lstBook.add(new Book("Palaces",link3,null));
        lstBook.add(new Book("Mountains",link4,null));
        lstBook.add(new Book("Temples",link5,null));
        lstBook.add(new Book("Historical places",link6,null));
        lstBook.add(new Book("Romantic places",link7,null));
        lstBook.add(new Book("Deserts",link8,null));


        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        RecycleViewAdapter myAdapter = new RecycleViewAdapter(this,lstBook);
        myrv.setLayoutManager(new GridLayoutManager(this,2));
        myrv.setAdapter(myAdapter);

    }
}

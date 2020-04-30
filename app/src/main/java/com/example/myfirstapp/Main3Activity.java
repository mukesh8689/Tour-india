package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {

    String id;
    String nam="";
    TextView text,text2,text3;
    ImageView image1,image2,image3,image4;
    Button but1,but2,but3,but4,but5;
    List<Book> lstBook ;
    RecyclerView recyclerView;
    private ProgressDialog progress_bar;


    //name of Json data
    private static final String TAG_SONGS = "records";

    private static final String TAG_NAME = "name_en";
    private  static final String TAG_DES="short_description_en";
    private static final String TAG_LAT= "latitude";
    private static final String TAG_LON = "longitude";
    private static final String TAG_LINK2= "link2";
    private static final String TAG_LINK3 = "link3";
    private static final String TAG_LINK4 = "link4";

    private static final String TAG_LINK="link1";

    JSONArray songs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        text=findViewById(R.id.name);
        text2=findViewById(R.id.des);
         text3=findViewById(R.id.gallery);
        image1=findViewById(R.id.img1);
        image2=findViewById(R.id.img2);
        image3=findViewById(R.id.img3);
        image4=findViewById(R.id.img4);
        but1=findViewById(R.id.railway);
        but2=findViewById(R.id.airport);
        but3=findViewById(R.id.hotels);
        but4=findViewById(R.id.rest);
        but5=findViewById(R.id.agency);
        lstBook = new ArrayList<>();
        Intent intent=getIntent();
        String str=intent.getStringExtra("link");
        id=intent.getStringExtra("name");
        new Main3Activity.GetSongs().execute(str);

    }

    private class GetSongs extends AsyncTask<String,Void,String> {
        String namee;
        String descr;
        String link;
        String linkk;
        String linkkk;
        String linkkkk;
        String lat;
        String lon;


        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progress_bar = new ProgressDialog(Main3Activity.this);
            progress_bar.setMessage("Fetching...");
            progress_bar.setCancelable(true);
            progress_bar.show();

        }

        protected String doInBackground(String... urls) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(urls[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream in = urlConnection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while (data != -1) {
                    char current = (char) data;
                    result += current;
                    data = reader.read();
                }


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "couldn't find", Toast.LENGTH_LONG);
            }


            if (result != null) {
                String name="";
                String link1="";
                String desc="";
                String link2="";
                String link3="";
                String link4="";
                String lati="";
                String longi="";

                try {
                    JSONObject jsonObj = new JSONObject(result);

                    songs = jsonObj.getJSONArray(TAG_SONGS);
                    for (int i = 0; i < songs.length(); i++) {
                        JSONObject c = songs.getJSONObject(i);
                         name = c.getString(TAG_NAME);


                        if(id.equals(name))
                        {
                            name=c.getString(TAG_NAME);
                             link1=c.getString(TAG_LINK);
                            desc=c.getString(TAG_DES);
                            lati=c.getString(TAG_LAT);
                            longi=c.getString(TAG_LON);
                             link2=c.getString(TAG_LINK2);
                            link3=c.getString(TAG_LINK3);
                            link4=c.getString(TAG_LINK4);
                            break;
                        }

                    }
                    namee=name;
                    descr=desc;
                    link=link1;
                    linkk=link2;
                    linkkk=link3;
                    linkkkk=link4;
                   lat=lati;
                   lon=longi;


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Log.e(" ParserError", "Error");
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if (progress_bar.isShowing())
                progress_bar.dismiss();
                text.setText(namee);
                text2.setText(descr);
            Picasso.with(Main3Activity.this).load(link).into(image1);
            Picasso.with(Main3Activity.this).load(linkk).into(image2);
            Picasso.with(Main3Activity.this).load(linkkk).into(image3);
            Picasso.with(Main3Activity.this).load(linkkkk).into(image4);

             final   String hotel="hotel";
             final String rest="restaurant";
             final String tourist="travel agency";
             final String rail="train station";
             final String air="airport";


              but3.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String nam=hotel;
                      String lati=lat;
                      String longi=lon;
                      double lat= Double.parseDouble(String.valueOf(lati));
                      double lon=Double.parseDouble(String.valueOf(longi));
                      Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+nam);
                      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                      mapIntent.setPackage("com.google.android.apps.maps");
                      startActivity(mapIntent);
                  }
              });

              but4.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String nam=rest;
                      String lati=lat;
                      String longi=lon;
                      double lat= Double.parseDouble(String.valueOf(lati));
                      double lon=Double.parseDouble(String.valueOf(longi));
                      Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+nam);
                      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                      mapIntent.setPackage("com.google.android.apps.maps");
                      startActivity(mapIntent);
                  }
              });

              but5.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String nam=tourist;
                      String lati=lat;
                      String longi=lon;
                      double lat= Double.parseDouble(String.valueOf(lati));
                      double lon=Double.parseDouble(String.valueOf(longi));
                      Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+nam);
                      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                      mapIntent.setPackage("com.google.android.apps.maps");
                      startActivity(mapIntent);
                  }
              });

              but1.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      String nam=rail;
                      String lati=lat;
                      String longi=lon;
                      double lat= Double.parseDouble(String.valueOf(lati));
                      double lon=Double.parseDouble(String.valueOf(longi));
                      Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+nam);
                      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                      mapIntent.setPackage("com.google.android.apps.maps");
                      startActivity(mapIntent);

                  }
              });

              but2.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      String nam=air;
                      String lati=lat;
                      String longi=lon;
                      double lat= Double.parseDouble(String.valueOf(lati));
                      double lon=Double.parseDouble(String.valueOf(longi));
                      Uri gmmIntentUri = Uri.parse("geo:"+lat+","+lon+"?q="+nam);
                      Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                      mapIntent.setPackage("com.google.android.apps.maps");
                      startActivity(mapIntent);
                  }
              });
        }
    }









}

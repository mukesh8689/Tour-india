package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TrainAirport extends AppCompatActivity {

    List<Hotel> thotel ;
    RecyclerView recyclerView;
    private ProgressDialog progress_bar;


    //name of Json data
    private static final String TAG_SONGS = "results";

    private static final String TAG_NAME = "name";
    private static final String TAG_LINK="vicinity";
    private static final String TAG_RATE="rating";
    private static final String TAG_ICON="icon";

    JSONArray songs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train_airport);

        thotel = new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        Intent intent=getIntent();
        String nam=intent.getStringExtra("lodging");
        String lati=intent.getStringExtra("latitude");
        String longi=intent.getStringExtra("longitude");
        double lat= Double.parseDouble(String.valueOf(lati));
        double lon=Double.parseDouble(String.valueOf(longi));


        new GetSongs().execute("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+lat + "," + lon + "&radius=30000&type="+nam+"&key=AIzaSyBLEPBRfw7sMb73Mr88L91Jqh3tuE4mKsE");
    }

    private class GetSongs extends AsyncTask<String,Void,String> {


        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progress_bar = new ProgressDialog(TrainAirport.this);
            progress_bar.setMessage("Fetching Hotels...");
            progress_bar.setCancelable(false);
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
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    songs = jsonObj.getJSONArray(TAG_SONGS);
                    for (int i = 0; i < songs.length(); i++) {
                        JSONObject c = songs.getJSONObject(i);
                        String nam = c.getString(TAG_NAME);
                        String rat=c.getString(TAG_RATE);
                        String add=c.getString(TAG_LINK);
                        String img=c.getString(TAG_ICON);
                        thotel.add(new Hotel(nam,rat,add,img));



                    }
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
            RecycleViewAdapter3 myAdapter = new RecycleViewAdapter3(TrainAirport.this,thotel);
            recyclerView.setLayoutManager(new LinearLayoutManager(TrainAirport.this));
            recyclerView.setAdapter(myAdapter);

        }
    }










}

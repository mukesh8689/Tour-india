package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class Main2Activity extends AppCompatActivity {

    List<Book> lstBook ;
    RecyclerView recyclerView;
    private ProgressDialog progress_bar;


    //name of Json data
    private static final String TAG_SONGS = "records";
    private static String str="";
    private static final String TAG_NAME = "name_en";
    private static final String TAG_LINK="link1";

    JSONArray songs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        lstBook = new ArrayList<>();
        recyclerView=findViewById(R.id.recycler);
        Intent intent=getIntent();
        str=intent.getStringExtra("link");

        new GetSongs().execute(str);

    }


    private class GetSongs extends AsyncTask<String,Void,String > {


        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            progress_bar = new ProgressDialog(Main2Activity.this);
            progress_bar.setMessage("Fetching States...");
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
                try {
                    JSONObject jsonObj = new JSONObject(result);

                    songs = jsonObj.getJSONArray(TAG_SONGS);
                    for (int i = 0; i < songs.length(); i++) {
                        JSONObject c = songs.getJSONObject(i);
                        String name = c.getString(TAG_NAME);
                        String link=c.getString(TAG_LINK);

                        lstBook.add(new Book(name,link,str));
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
            RecycleViewAdapter2 myAdapter = new RecycleViewAdapter2(Main2Activity.this,lstBook);
            recyclerView.setLayoutManager(new GridLayoutManager(Main2Activity.this,1));
            recyclerView.setAdapter(myAdapter);

        }
    }



}

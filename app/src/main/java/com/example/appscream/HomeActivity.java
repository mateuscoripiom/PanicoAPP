package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;




import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    //private RecyclerView recyclerView;
    //private RequestQueue requestQueue;
  //  private List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);

        GetData getData = new GetData();
        getData.execute();
    }

    private static String JSON_URL = "https://api.themoviedb.org/3/collection/2602?api_key=c1266995f1e4319216d8181d18012e4f&language=pt-BR";

    List<MovieModelClass> movieList;
     RecyclerView recyclerView;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 10){
            if(grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                getInternet();
            }
            else{
                {
                    Toast.makeText(HomeActivity.this, "Permissão de internet negada.", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
    public void getInternet(){
        if(ActivityCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.INTERNET) == PackageManager.PERMISSION_GRANTED){

        }
        else{
            requestPermission();
        }
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(HomeActivity.this,
                new String[]{android.Manifest.permission.INTERNET}, 10);
    }

    public class GetData extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... strings) {
            String current="";
            if(ActivityCompat.checkSelfPermission(HomeActivity.this, android.Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED){
                requestPermission();
            }
            else{


            try{
                URL url;
                HttpURLConnection urlConnection = null;

                 try{
                     url = new URL(JSON_URL);
                     urlConnection = (HttpURLConnection) url.openConnection();

                     InputStream is = urlConnection.getInputStream();
                     InputStreamReader isr = new InputStreamReader(is);

                     int data = isr.read();
                     while(data != -1){
                         current += (char) data;
                         data = isr.read();

                     }
                     return current;
                 } catch (MalformedURLException e){
                     e.printStackTrace();
                 } catch (IOException e){
                     e.printStackTrace();
                 } finally {
                     if(urlConnection != null){
                         urlConnection.disconnect();
                     }
                 }
            } catch (Exception e){
                e.printStackTrace();
            }}
            return current;
        }

        @Override
        protected void onPostExecute(String s){

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    model.setId(jsonObject1.getString("id"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImg(jsonObject1.getString("poster_path"));

                    movieList.add(model);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

            PutDataIntoRecyclerView(movieList);
        }
    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(this, movieList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(adaptery);
    }
}
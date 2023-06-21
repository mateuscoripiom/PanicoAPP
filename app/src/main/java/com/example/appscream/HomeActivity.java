package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appscream.models.MovieModel;
import com.example.appscream.request.Service;
import com.example.appscream.response.CollectionSearchResponse;
import com.example.appscream.response.MovieSearchResponse;
import com.example.appscream.utils.Credentials;
import com.example.appscream.utils.MovieApi;
import com.example.appscream.viewmodels.MovieListViewModel;

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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    ImageView imgpanico1, imgpanico2, imgpanico3, imgpanico4, imgpanico5, imgpanico6;

    Button btnteste;

    public static Integer ID = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        btnteste = (Button)findViewById(R.id.btnteste1);
        btnteste.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, FranquiaActivity.class));

            }
        });

        imgpanico1 = (ImageView) findViewById(R.id.imgpanico1);
        imgpanico1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 4232;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });

        imgpanico2 = (ImageView) findViewById(R.id.imgpanico2);
        imgpanico2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 4233;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });

        imgpanico3 = (ImageView) findViewById(R.id.imgpanico3);
        imgpanico3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 4234;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });

        imgpanico4 = (ImageView) findViewById(R.id.imgpanico4);
        imgpanico4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 41446;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });

        imgpanico5 = (ImageView) findViewById(R.id.imgpanico5);
        imgpanico5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 646385;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });

        imgpanico6 = (ImageView) findViewById(R.id.imgpanico6);
        imgpanico6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ID = 934433;
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));

            }
        });
    }

   private void GetRetrofitResponse(){
       MovieApi movieApi = Service.getMovieApi();

       Call<MovieSearchResponse> responseCall = movieApi
               .searchMovie(
                       Credentials.API_KEY,
                       "Pânico",
                       1,
                       "pt-BR"
                       );

       responseCall.enqueue(new Callback<MovieSearchResponse>() {
           @Override
           public void onResponse(Call<MovieSearchResponse> call, Response<MovieSearchResponse> response) {
               if(response.code() == 200){
                   Log.v("Tag", "the response" + response.body().toString());
                   List<MovieModel> movies = new ArrayList<>(response.body().getMovies());

                   for(MovieModel movie: movies){
                       Log.v("Tag", "The title" + movie.getTitle());
                   }
               }
               else{
                   try{
                       Log.v("Tag", "Error" + response.errorBody().string());
                   } catch (IOException e){
                       e.printStackTrace();
                   }
               }
           }

           @Override
           public void onFailure(Call<MovieSearchResponse> call, Throwable t) {
                t.printStackTrace();
           }
       });
   }

   private void GetRetrofitResponseAccordingToID(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                ID,
                Credentials.API_KEY,
                "pt-BR");

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response" + movie.getTitle() + movie.getOverview());

                }
                else{
                    try{
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieModel> call, Throwable t) {

            }
        });
   }

    private void GetRetrofitResponseAccordingToCollection(){
        MovieApi movieApi = Service.getMovieApi();
        Call<CollectionSearchResponse> responseCall = movieApi.getCollection(
                2602,
                Credentials.API_KEY,
                "pt-BR");

        responseCall.enqueue(new Callback<CollectionSearchResponse>() {
            @Override
            public void onResponse(Call<CollectionSearchResponse> call, Response<CollectionSearchResponse> response) {
                if(response.code() == 200){
                    Log.v("Tag", "the response" + response.body().toString());
                    List<MovieModel> movies = new ArrayList<>(response.body().getCollection());

                    for(MovieModel movie: movies){
                        Log.v("Tag", "The title" + movie.getTitle());
                    }
                }
                else{
                    try{
                        Log.v("Tag", "Error" + response.errorBody().string());
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<CollectionSearchResponse> call, Throwable t) {

            }
        });

        ImageButton imgbtnhome = findViewById(R.id.imgbtnhome);

        imgbtnhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, HomeActivity.class));
            }
        });

        ImageButton imgbtnfilme = findViewById(R.id.imgbtnfilme);

        imgbtnfilme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));
            }
        });

        ImageButton imgbtnserie = findViewById(R.id.imgbtnserie);

        imgbtnserie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, FilmeActivity.class));
            }
        });

        ImageButton imgbtnperfil = findViewById(R.id.imgbtnperfil);

        imgbtnperfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, activity_perfil.class));
            }
        });

        ImageButton imgbtntelefone = findViewById(R.id.imgbtntelefone);

        imgbtntelefone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this, Telefone.class));
            }
        });
    }
}
package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appscream.models.LogoModel;
import com.example.appscream.models.MovieModel;
import com.example.appscream.request.Service;
import com.example.appscream.response.MovieLogoResponse;
import com.example.appscream.response.MovieSearchResponse;
import com.example.appscream.utils.Credentials;
import com.example.appscream.utils.MovieApi;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmeActivity extends AppCompatActivity {

    TextView txtidf, txtnomef,txtdirecao;
    ImageView imgfilmef, imglogo, imgbtnvoltar;
    ImageButton imgbtnhome, imgbtnfilme, imgbtnserie, imgbtnper, imgbtntelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filme);

        imgbtnhome = findViewById(R.id.imgbtnhome4);
        imgbtnserie = findViewById(R.id.imgbtnserie4);
        imgbtntelefone = findViewById(R.id.imgbtntelefone4);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar4);

        if(HomeActivity.ID != null){
            GetRetrofitResponseAccordingToID();
        }

        if(HomeActivity.ID == 4232){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/9jCsLVdl5xo2TxhQCFzYuezrQYB.png")
                    .into(imglogo);
        }

        if(HomeActivity.ID == 4233){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/q6HC9rLBfZjuAlhneGkRPkQRtXS.png")
                    .into(imglogo);
        }

        if(HomeActivity.ID == 4234){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/wkqfrcS97zJ7frm8oeBngTqXGO3.png")
                    .into(imglogo);
        }

        if(HomeActivity.ID == 41446){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/ehNlBXbKsTSfsBYsSiuvpppRXYj.png")
                    .into(imglogo);
        }

        if(HomeActivity.ID == 646385){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/4Jd73sLEuNb1cYyJ2UPU1oCMRlh.png")
                    .into(imglogo);
        }

        if(HomeActivity.ID == 934433){
            imglogo = (ImageView)findViewById(R.id.imglogo);
            Picasso
                    .get()
                    .load("https://assets.fanart.tv/fanart/movies/934433/hdmovielogo/scream-vi-639a1857505bc.png")
                    .into(imglogo);
        }
        imgbtnhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, HomeActivity.class));
            }
        });
        imgbtnserie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, SerieActivity.class));
            }

        });
        imgbtntelefone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, Telefone.class));
            }

        });
        imgbtnvoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FilmeActivity.this, HomeActivity.class));
            }
        });
    }

    private void GetRetrofitResponseAccordingToID(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getMovie(
                HomeActivity.ID,
                Credentials.API_KEY,
                "pt-BR");
        txtidf = (TextView)findViewById(R.id.txtidf);
        imgfilmef = (ImageView)findViewById(R.id.imgfilmef);
        txtnomef = (TextView)findViewById(R.id.txtnomef);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response" + movie.getTitle() + movie.getOverview() + movie.getBackdrop_path());
                    txtidf.setText(movie.getTitle());
                    txtnomef.setText(movie.getOverview());
                    Picasso
                            .get()
                            .load("https://image.tmdb.org/t/p/w500/" + movie.getBackdrop_path())
                            .into(imgfilmef);
                    Log.v("IMAGEM", movie.getBackdrop_path());
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

    private void GetRetrofitResponseCredits(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getCredits(
                HomeActivity.ID,
                Credentials.API_KEY);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response " + movie.getKnown_for_department() + movie.getName());
                    txtdirecao.setText(movie.getName());
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
}
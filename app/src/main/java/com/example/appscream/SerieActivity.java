package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appscream.models.MovieModel;
import com.example.appscream.request.Service;
import com.example.appscream.utils.Credentials;
import com.example.appscream.utils.MovieApi;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SerieActivity extends AppCompatActivity {

    TextView txtnomef;
    ImageView imgfilmef, imglogo, imgbtnvoltar;
    ImageButton imgbtnhome, imgbtnfilme, imgbtnserie, imgbtnper, imgbtntelefone;

    public static Integer IDSerie = 62823;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serie);

        imgbtnhome = findViewById(R.id.imgbtnhome5);
        imgbtnserie = findViewById(R.id.imgbtnserie5);
        imgbtntelefone = findViewById(R.id.imgbtntelefone5);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar5);

        if(IDSerie != null){
            GetRetrofitResponseAccordingToID();
        }

            imglogo = (ImageView)findViewById(R.id.imglogo3);
            Picasso
                    .get()
                    .load("https://www.themoviedb.org/t/p/original/9jCsLVdl5xo2TxhQCFzYuezrQYB.png")
                    .into(imglogo);



        imgbtnhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(SerieActivity.this, HomeActivity.class));
            }
        });
        imgbtnserie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(SerieActivity.this, SerieActivity.class));
            }

        });
        imgbtntelefone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(SerieActivity.this, Telefone.class));
            }

        });
        imgbtnvoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(SerieActivity.this, HomeActivity.class));
            }
        });
    }
    private void GetRetrofitResponseAccordingToID(){
        MovieApi movieApi = Service.getMovieApi();
        Call<MovieModel> responseCall = movieApi.getSeries(
                IDSerie,
                Credentials.API_KEY,
                "pt-BR");
        imgfilmef = (ImageView)findViewById(R.id.imgfilmef);
        txtnomef = (TextView)findViewById(R.id.txtnomef2);
        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response" + movie.getTitle() + movie.getOverview() + movie.getBackdrop_path());
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
}
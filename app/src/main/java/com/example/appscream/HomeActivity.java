package com.example.appscream;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.pm.PackageManager;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

    Button btnteste;
    private MovieListViewModel movieListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        ObserveAnyChange();

        btnteste = (Button) findViewById(R.id.btnteste);
        btnteste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchMovieApi("Pânico", 1, "pt-BR");
            }
        });
    }

    private void ObserveAnyChange(){
        movieListViewModel.getMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels != null){
                    for(MovieModel movieModel: movieModels){
                        Log.v("Tag", "onChanged: "+movieModel.getTitle());
                    }
                }
            }
        });
    }

    private void searchMovieApi(String query, int pageNumber, String language){
        movieListViewModel.searchMovieApi(query, pageNumber, language);
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
                4232,
                Credentials.API_KEY,
                "pt-BR");

        responseCall.enqueue(new Callback<MovieModel>() {
            @Override
            public void onResponse(Call<MovieModel> call, Response<MovieModel> response) {
                if(response.code() == 200){
                    MovieModel movie = response.body();
                    Log.v("Tag", "The Response" + movie.getTitle());

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
    }
}
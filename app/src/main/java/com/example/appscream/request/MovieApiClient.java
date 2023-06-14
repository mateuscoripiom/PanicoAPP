package com.example.appscream.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.appscream.AppExecutors;
import com.example.appscream.models.MovieModel;
import com.example.appscream.response.MovieSearchResponse;
import com.example.appscream.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    private MutableLiveData<List<MovieModel>> mMovies;

    private static MovieApiClient instance;

    private RetrieveMoviesRunnable retrieveMoviesRunnable;

    public static MovieApiClient getInstance(){
        if (instance == null){
            instance = new MovieApiClient();
        } 
        return instance;
    }

    private MovieApiClient(){
        mMovies = new MutableLiveData<>();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return mMovies;
    }

    public void searchMoviesApi(String query, int pageNumber, String language){

        if(retrieveMoviesRunnable != null){
            retrieveMoviesRunnable = null;
        }

        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber, language);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 5000, TimeUnit.MICROSECONDS);
    }

    private class RetrieveMoviesRunnable implements Runnable{
        private String query;
        private int pageNumber;
        private String language;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber, String language) {
            this.query = query;
            this.pageNumber = pageNumber;
            this.language = language;
            cancelRequest = false;
        }

        @Override
        public void run() {

            try{
               Response response = getMovies(query, pageNumber, language).execute();
               if(cancelRequest){
                   return;
               }
               if(response.code() == 200){
                   List<MovieModel> list = new ArrayList<>(((MovieSearchResponse)response.body()).getMovies());
                   if(pageNumber == 1){
                       mMovies.postValue(list);
                   }else{
                       List<MovieModel> currentMovies = mMovies.getValue();
                       currentMovies.addAll(list);
                   }

               } else{
                   String error = response.errorBody().string();
                   Log.v("Tag", "Erro " + error);
                   mMovies.postValue(null);
               }
            }catch (IOException e) {
                e.printStackTrace();
                mMovies.postValue(null);
            }
            if(cancelRequest){
                return;
            }
            }
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber, String language){
            return Service.getMovieApi().searchMovie(
                    Credentials.API_KEY,
                    query,
                    pageNumber,
                    language
            );
        }
        private void cancelRequest(){
            Log.v("Tag", "Cancelando o request de busca");
            cancelRequest = true;
        }
    }

}

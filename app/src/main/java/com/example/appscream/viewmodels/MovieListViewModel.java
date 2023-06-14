package com.example.appscream.viewmodels;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appscream.models.MovieModel;
import com.example.appscream.repositories.MovieRepository;

import java.util.List;

public class MovieListViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieListViewModel(MutableLiveData<List<MovieModel>> mMovies) {
        movieRepository = MovieRepository.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies(){
        return movieRepository.getMovies();
    }

    public void searchMovieApi(String query, int pageNumber, String language){
        movieRepository.searchMovieApi(query, pageNumber, language);
    }

}

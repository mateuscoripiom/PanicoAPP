package com.example.appscream.response;

import com.example.appscream.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

//Classe para request de vários filmes
public class MovieSearchResponse {
    @SerializedName("total_results")
    @Expose()
    private int total_count;
    
    @SerializedName("results")
    @Expose()
    private List<MovieModel> movies;

    public int getTotal_count(){
        return total_count;
    }
    public List<MovieModel> getMovies(){
        return movies;
    }


}

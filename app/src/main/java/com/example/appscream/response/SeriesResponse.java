package com.example.appscream.response;

import com.example.appscream.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SeriesResponse {
    @SerializedName("results")
    @Expose
    private MovieModel movie;

    public MovieModel getSeries(){
        return movie;
    }
}

package com.example.appscream.response;

import com.example.appscream.models.LogoModel;
import com.example.appscream.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieLogoResponse {
    @SerializedName("logos")
    @Expose
    private List<MovieModel> movies;

    public List<MovieModel> getLogo(){
        return movies;
    }
}

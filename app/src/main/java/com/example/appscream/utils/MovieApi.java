package com.example.appscream.utils;

import com.example.appscream.models.MovieModel;
import com.example.appscream.response.CollectionSearchResponse;
import com.example.appscream.response.MovieSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieApi {

    //https://api.themoviedb.org/3/collection/2602?api_key=c1266995f1e4319216d8181d18012e4f&language=pt-BR
    @GET
    Call<MovieSearchResponse> searchMovie(
            @Query("api_key") String key,
            @Query("query") String query,
            @Query("page") int page,
            @Query("language") String language
    );

    @GET("3/movie/{movie_id}?")
    Call<MovieModel> getMovie(
            @Path("movie_id") int movie_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );

    @GET("3/collection/{collection_id}?")
    Call<CollectionSearchResponse> getCollection(
            @Path("collection_id") int collection_id,
            @Query("api_key") String api_key,
            @Query("language") String language
    );
}

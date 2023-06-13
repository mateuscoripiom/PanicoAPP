package com.example.appscream.models;

import android.os.Parcel;
import android.os.Parcelable;

public class MovieModel implements Parcelable {
    private String title;
    private String poster_path;
    private String backdrop_path;
    private String overview;
    private float vote_average;
    private int movie_id;

    public MovieModel(String title, String poster_path, String backdrop_path, String overview, float vote_average, int movie_id) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.movie_id = movie_id;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        movie_id = in.readInt();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public float getVote_average() {
        return vote_average;
    }

    public int getMovie_id() {
        return movie_id;
    }
    
    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i){

        parcel.writeString(title);
        parcel.writeString(poster_path);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeFloat(vote_average);
        parcel.writeInt(movie_id);
    }
}

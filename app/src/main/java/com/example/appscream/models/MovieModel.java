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
    private String logo_path;
    private String known_for_department;
    private String original_title;
    private String name;
    private String series_id;

    public MovieModel(String title, String series_id, String name, String known_for_department, String original_title, String poster_path, String backdrop_path, String overview, float vote_average, int movie_id, String logo_path) {
        this.title = title;
        this.poster_path = poster_path;
        this.backdrop_path = backdrop_path;
        this.overview = overview;
        this.vote_average = vote_average;
        this.movie_id = movie_id;
        this.logo_path = logo_path;
        this.original_title = original_title;
        this.known_for_department = known_for_department;
        this.name = name;
        this.series_id = series_id;
    }

    protected MovieModel(Parcel in) {
        title = in.readString();
        poster_path = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        vote_average = in.readFloat();
        movie_id = in.readInt();
        logo_path = in.readString();
        original_title = in.readString();
        known_for_department = in.readString();
        name = in.readString();
        series_id = in.readString();
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

    public String getLogo_path() { return logo_path;}

    public String getOriginal_title(){ return original_title;}

    public String getKnown_for_department(){ return known_for_department;}

    public String getName(){ return name;};

    public String getSeries_id(){ return series_id;}
    
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
        parcel.writeString(logo_path);
        parcel.writeString(original_title);
        parcel.writeString(known_for_department);
        parcel.writeString(name);
        parcel.writeString(series_id);
    }
}

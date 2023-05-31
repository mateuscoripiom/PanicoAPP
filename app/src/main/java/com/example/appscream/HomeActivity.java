package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private static String JSON_URL = "https://api.themoviedb.org/3/collection/2602?api_key=c1266995f1e4319216d8181d18012e4f&language=pt-BR";

    List<MovieModelClass> movieList;
    RecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }
}
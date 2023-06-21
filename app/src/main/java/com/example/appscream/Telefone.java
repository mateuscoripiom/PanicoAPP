package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Telefone extends AppCompatActivity {

    ImageButton ring, imgbtnhome, imgbtnfilme, imgbtnserie, imgbtnper, imgbtntelefone, imgbtnvoltar;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_telefone);

        ring = findViewById(R.id.imageButton10);
        imgbtnhome = findViewById(R.id.imgbtnhome7);
        imgbtnserie = findViewById(R.id.imgbtnserie7);
        imgbtntelefone = findViewById(R.id.imgbtntelefone7);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar7);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.scream);

        ring.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.start();
            }

        });

        imgbtnhome.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, HomeActivity.class));
            }
        });


        imgbtnserie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, SerieActivity.class));
            }

        });

        imgbtntelefone.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, Telefone.class));
            }

        });

        imgbtnvoltar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, HomeActivity.class));
            }
        });
    }
}
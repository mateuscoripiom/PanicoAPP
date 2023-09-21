package com.example.appscream;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Telefone extends AppCompatActivity {

    ImageButton ring, imgbtnvoltar, imgbtnhome5, imgbtnatores5, imgbtnp5, imgbtntelefone5;


    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate (savedInstanceState);
        setContentView(R.layout.activity_telefone);

        ring = findViewById(R.id.imageButton10);
        imgbtnhome5 = findViewById(R.id.imgbtnhomeinicial5);
        imgbtnatores5 = findViewById(R.id.imgbtnatoresinicial5);
        imgbtnp5 = findViewById(R.id.imgbtnpinicial5);
        imgbtntelefone5 = findViewById(R.id.imgbtntelefoneinicial5);
        imgbtnvoltar = findViewById(R.id.imgbtnvoltar7);
        final MediaPlayer mediaPlayer = MediaPlayer.create(this,R.raw.scream);

        ring.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.start();
            }

        });

        imgbtnhome5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, HomeActivity.class));
            }
        });


        imgbtnatores5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, AtoresActivity.class));
            }

        });
        imgbtnp5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(Telefone.this, PersonagensActivity.class));
            }

        });

        imgbtntelefone5.setOnClickListener(new View.OnClickListener(){
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
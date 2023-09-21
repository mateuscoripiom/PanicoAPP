package com.example.appscream;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imgPoster;
    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imgPoster = itemView.findViewById(R.id.imgPoster);
    }
}

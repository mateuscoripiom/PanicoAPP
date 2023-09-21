package com.example.appscream;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderAtor extends RecyclerView.ViewHolder{
    TextView txtnomeator, txtdescricaoator;
    ImageView imgAtor;
    public MyViewHolderAtor(@NonNull View itemView) {
        super(itemView);
        txtnomeator = itemView.findViewById(R.id.textView2);
        txtdescricaoator = itemView.findViewById(R.id.textView3);
        imgAtor = itemView.findViewById(R.id.imgAtor);
    }
}

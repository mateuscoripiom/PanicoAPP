package com.example.appscream;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderP extends RecyclerView.ViewHolder{
    TextView txtnomep, txtdescricaop;
    ImageView imgPersonagem;
    public MyViewHolderP(@NonNull View itemView) {
        super(itemView);
        txtnomep = itemView.findViewById(R.id.txtnomep);
        txtdescricaop = itemView.findViewById(R.id.txtdescricaop);
        imgPersonagem = itemView.findViewById(R.id.imgPersonagem);
    }
}

package com.example.appscream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterP extends RecyclerView.Adapter<MyViewHolderP> {
    Context personagenscontext;
    List<ItemPersonagem> itemspersonagens;

    public MyAdapterP(Context personagenscontext, List<ItemPersonagem> itemspersonagens) {
        this.personagenscontext = personagenscontext;
        this.itemspersonagens = itemspersonagens;
    }

    @NonNull
    @Override
    public MyViewHolderP onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderP(LayoutInflater.from(personagenscontext).inflate(R.layout.itempersonagem_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderP holder, int position) {
        holder.txtnomep.setText(itemspersonagens.get(position).getNomepersonagem());
        holder.txtdescricaop.setText(itemspersonagens.get(position).getDescricaopersonagem());
        Picasso
                .get()
                .load(itemspersonagens.get(position).getImagepersonagem())
                .into(holder.imgPersonagem);
    }

    @Override
    public int getItemCount() {
        return itemspersonagens.size();
    }
}

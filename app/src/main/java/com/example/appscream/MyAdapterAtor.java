package com.example.appscream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapterAtor extends RecyclerView.Adapter<MyViewHolderAtor> {
    Context atorescontext;
    List<ItemAtor> itemsatores;

    public MyAdapterAtor(Context atorescontext, List<ItemAtor> itemsatores) {
        this.atorescontext = atorescontext;
        this.itemsatores = itemsatores;
    }

    @NonNull
    @Override
    public MyViewHolderAtor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolderAtor(LayoutInflater.from(atorescontext).inflate(R.layout.itemator_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderAtor holder, int position) {
        holder.txtnomeator.setText(itemsatores.get(position).getNomeator());
        holder.txtdescricaoator.setText(itemsatores.get(position).getDescricaoator());
        Picasso
                .get()
                .load(itemsatores.get(position).getImageator())
                .into(holder.imgAtor);
    }

    @Override
    public int getItemCount() {
        return itemsatores.size();
    }
}

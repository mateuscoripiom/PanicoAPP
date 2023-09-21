package com.example.appscream;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context popcontext;
    List<Item> items;

    public MyAdapter(Context popcontext, List<Item> items) {
        this.popcontext = popcontext;
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(popcontext).inflate(R.layout.item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Picasso
                .get()
                .load(items.get(position).getImage())
                .into(holder.imgPoster);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}

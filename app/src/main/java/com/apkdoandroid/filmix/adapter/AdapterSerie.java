package com.apkdoandroid.filmix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.listeners.ListnerSerie;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterSerie extends RecyclerView.Adapter<AdapterSerie.MyviewHolder>{

    private ListnerSerie listnerSerie;
    private List<Serie> series;
    private Context context;

    public AdapterSerie(ListnerSerie listnerSerie, List<Serie> series, Context context) {
        this.listnerSerie = listnerSerie;
        this.series = series;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filme,parent,false);
        return new MyviewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Serie serie = series.get(position);

//        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        Glide.with(context).load(serie.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listnerSerie.onclick(serie);
            }
        });


    }

    @Override
    public int getItemCount() {
//        return 10;
        return series.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        private ImageView imagem;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imageViewCapa);
        }
    }
}

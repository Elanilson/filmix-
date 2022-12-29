package com.apkdoandroid.filmix.adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.service.DataService;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskSeries;
import com.bumptech.glide.Glide;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AdapterFillme extends RecyclerView.Adapter<AdapterFillme.MyviewHolder>{

    private ListnerFilme listnerFilme;
    private List<Filme> filmes;
    private Context context;
    private  MyviewHolder myviewHolder;
    private Filme filmex = new Filme();

    public AdapterFillme(ListnerFilme listnerFilme, List<Filme> filmes, Context context) {
        this.listnerFilme = listnerFilme;
        this.filmes = filmes;
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
        Filme filme = filmes.get(position);

//        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        for (int i = 0;i <= 10 ;i++){

        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        }
        holder.imagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listnerFilme.onclick(filme);
            }
        });





    }

    @Override
    public int getItemCount() {
//        return 10;
        return filmes.size();
    }

    class MyviewHolder extends RecyclerView.ViewHolder{
        private ImageView imagem;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imageViewCapa);
        }
    }



}

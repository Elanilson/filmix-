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
import com.apkdoandroid.filmix.model.Filme;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterLancamento extends RecyclerView.Adapter<AdapterLancamento.MyviewHolder>{

    private ListnerFilme listnerFilme;
    private List<Filme> filmes;
    private Context context;


    public AdapterLancamento(ListnerFilme listnerFilme, List<Filme> filmes, Context context) {
        this.listnerFilme = listnerFilme;
        this.filmes = filmes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_lancamento,parent,false);
        return new MyviewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Filme filme = filmes.get(position);



//        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        Glide.with(context).load("https://br.web.img3.acsta.net/pictures/20/12/09/14/50/5539035.jpg").placeholder(R.drawable.padrao).into(holder.imagem);
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
        private ImageView imagem, top10;

        public MyviewHolder(@NonNull View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imageViewCapa);

        }
    }
}

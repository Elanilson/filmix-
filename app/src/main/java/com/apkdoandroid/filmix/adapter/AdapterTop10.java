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

public class AdapterTop10 extends RecyclerView.Adapter<AdapterTop10.MyviewHolder>{

    private ListnerFilme listnerFilme;
    private List<Filme> filmes;
    private Context context;
    private int posicaoTop10 = 0;

    public AdapterTop10(ListnerFilme listnerFilme, List<Filme> filmes, Context context) {
        this.listnerFilme = listnerFilme;
        this.filmes = filmes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_filmetop,parent,false);
        return new MyviewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        Filme filme = filmes.get(position);
        System.out.println(filme.toString());

        holder.top10.setVisibility(View.VISIBLE);

        posicaoTop10 ++;
         if (0 == position){
            holder.top10.setImageResource(R.drawable.n1);

        }else if (1 == position){
            holder.top10.setImageResource(R.drawable.n2);

        }else if (2 == position){
            holder.top10.setImageResource(R.drawable.n3);

        }else if (3 == position){
            holder.top10.setImageResource(R.drawable.n4);

        }else if (4 == position){
            holder.top10.setImageResource(R.drawable.n5);

        }else if (5 == position){
            holder.top10.setImageResource(R.drawable.n6);
        }else if (6 == position){
            holder.top10.setImageResource(R.drawable.n7);
        }else if (7 == position){
            holder.top10.setImageResource(R.drawable.n8);
        }else if (8 == position){
            holder.top10.setImageResource(R.drawable.n9);
        }else if (9 == position){
            holder.top10.setImageResource(R.drawable.n10);
            posicaoTop10 = 0;
        }


         holder.imagem.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 listnerFilme.onclick(filme);
             }
         });


//        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);
        Glide.with(context).load(filme.getCapa()).placeholder(R.drawable.padrao).into(holder.imagem);


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
            top10 = itemView.findViewById(R.id.imageViewTop10);
        }
    }
}

package com.apkdoandroid.filmix.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.listeners.ListerneCategoria;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Filme;

import java.util.List;

public class AdapterEpisodio extends RecyclerView.Adapter<AdapterEpisodio.MyViewHolder> {
    private ListnerFilme listnerFilme;
    private List<Filme> filmes;
    private Context context;

    public AdapterEpisodio(ListnerFilme listnerFilme, List<Filme> filmes, Context context) {
        this.listnerFilme = listnerFilme;
        this.filmes = filmes;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_episodio,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Filme filme = filmes.get(position);
        holder.nome.setText(position+1+" Episódio");
        holder.layoutAssistirEpisodio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listnerFilme.Episodio(filme);

            }
        });

    }

    @Override
    public int getItemCount() {
        return filmes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;
        private CardView cardView;
        private LinearLayout layoutAssistirEpisodio;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewNomeEpisodio);
            cardView = itemView.findViewById(R.id.cardEpisodio);
            layoutAssistirEpisodio = itemView.findViewById(R.id.layoutAssistirEpisodio);
        }
    }
}

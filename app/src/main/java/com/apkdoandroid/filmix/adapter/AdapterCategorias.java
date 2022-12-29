package com.apkdoandroid.filmix.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.listeners.ListerneCategoria;
import com.apkdoandroid.filmix.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class AdapterCategorias extends RecyclerView.Adapter<AdapterCategorias.MyViewHolder> {
    private List<Categoria> generos;
    private ListerneCategoria listerneCategoria;

    public AdapterCategorias(List<Categoria> generos, ListerneCategoria listerneCategoria) {
        this.generos = generos;
        this.listerneCategoria = listerneCategoria;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_genero,parent,false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categoria genero = generos.get(position);
        holder.nome.setText(genero.getNome());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listerneCategoria.onClick(holder.nome.getText().toString());

            }
        });

    }

    @Override
    public int getItemCount() {
        return generos.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView nome;
        private CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.textViewNomegenero);
            cardView = itemView.findViewById(R.id.CardCategoria);
        }
    }
}

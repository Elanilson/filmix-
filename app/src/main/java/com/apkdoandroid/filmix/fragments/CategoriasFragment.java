package com.apkdoandroid.filmix.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.activity.CategoriaActivity;
import com.apkdoandroid.filmix.activity.VisualizarActivity;
import com.apkdoandroid.filmix.adapter.AdapterCategorias;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListerneCategoria;
import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Genero;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoriasFragment extends Fragment implements ListerneCategoria {
    private View view;
    private AdapterCategorias adapterCategorias;
    private RecyclerView recyclerView;
    private List<Categoria> categorias = new ArrayList<>();
    private DaoBanco dao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_categorias, container, false);
       // carregar();
        dao = new DaoBanco(getActivity());

        recyclerView = view.findViewById(R.id.recyclerViewGenero);

        adapterCategorias = new AdapterCategorias(dao.listarCategorias(),this);
//        adapterCategorias = new AdapterCategorias(dao.listarCategorias());

//        GridLayoutManager manager = new GridLayoutManager(getContext(),3);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterCategorias);

        return  view;
    }
    private void carregar(){
        categorias.add(new Categoria("Ação"));
        categorias.add(new Categoria("Comedia"));
        categorias.add(new Categoria("Romance"));
        categorias.add(new Categoria("Terror"));
        categorias.add(new Categoria("Besterol"));
        categorias.add(new Categoria("Criminal"));
        categorias.add(new Categoria("Assasinatos"));
        categorias.add(new Categoria("Reality"));
        categorias.add(new Categoria("Ducomentarios"));
        categorias.add(new Categoria("Musicas"));
        categorias.add(new Categoria("Comedia romantica"));
        categorias.add(new Categoria("Pegadinhas"));
        categorias.add(new Categoria("Contos"));
        categorias.add(new Categoria("Desenhos"));
        categorias.add(new Categoria("Anime"));

    }

    @Override
    public void onClick(String s) {
        Intent intent = new Intent(getActivity(), CategoriaActivity.class);
        intent.putExtra("categoria",s);
        startActivity(intent);

    }
}
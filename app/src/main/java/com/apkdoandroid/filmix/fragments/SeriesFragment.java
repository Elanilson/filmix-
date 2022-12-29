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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.activity.PlayerActivity;
import com.apkdoandroid.filmix.activity.VisualizarActivity;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.adapter.AdapterSerie;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.listeners.ListnerSerie;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SeriesFragment extends Fragment implements ListnerSerie {
    private View view;

    private RecyclerView recyclerView;

    private DaoBanco dao ;

    private Serie serie = new Serie();
    private List<Serie> series = new ArrayList<>();
    private AdapterSerie adapterSerie;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_series, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewAbaSerie);

        dao = new DaoBanco(getActivity());

        recyclerView();



        return view;
    }
    private void recyclerView() {


        adapterSerie = new AdapterSerie(this,  dao.listarSerie(),getActivity());

        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterSerie);
        adapterSerie.notifyDataSetChanged();

    }


public void carregarPreviaSerie(){

    BottomSheetDialog sheetDialog = new BottomSheetDialog(getContext());

    View layout = LayoutInflater.from(getContext())
            .inflate(R.layout.layout_previa,view.findViewById(R.id.layout_prev));
    sheetDialog.setContentView(layout);
    LinearLayout maisInfo = layout.findViewById(R.id.layout_MaisInfo);
    Button assistir = layout.findViewById(R.id.buttonPrevAssistir);
    Button download = layout.findViewById(R.id.buttonPrevDown);
    ImageButton close  = layout.findViewById(R.id.imageButtonPrevClose);
    ImageView capa = layout.findViewById(R.id.imageViewPrevCapa);
    TextView titulo = layout.findViewById(R.id.textViewPrevTitulo);
    TextView ano = layout.findViewById(R.id.textViewPrevAno);
    TextView retricao = layout.findViewById(R.id.textViewPrevRestri);
    TextView duracao = layout.findViewById(R.id.textViewPrevDuracao);
    TextView descricao = layout.findViewById(R.id.textViewPrevDescricao);
    TextView textView12MaisInformacoes = layout.findViewById(R.id.textView12MaisInformacoes);

    Glide.with(getActivity()).load(serie.getCapa()).placeholder(R.drawable.padrao).into(capa);
    titulo.setText(serie.getTitulo());
    ano.setText(serie.getAnoDeLancamento());
    retricao.setText(serie.getRestricao());
    duracao.setText(serie.getTemporada());
    descricao.setText(serie.getDescricao());
    download.setVisibility(View.GONE);

    textView12MaisInformacoes.setText("Episódios e informações");
    maisInfo.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//                startActivity(new Intent(getActivity(), VisualizarActivity.class));
            Intent intent = new Intent(getActivity(), VisualizarActivity.class);
            intent.putExtra("serie",(Serializable) serie);
            startActivity(intent);
            sheetDialog.dismiss();

        }
    });
    assistir.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(getActivity(), VisualizarActivity.class);
            intent.putExtra("serie",(Serializable) serie);
            startActivity(intent);
            sheetDialog.dismiss();
        }
    });
    download.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(getActivity(),"Em breve",Toast.LENGTH_SHORT).show();
            sheetDialog.dismiss();

        }
    });
    close.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            sheetDialog.dismiss();

        }
    });
    sheetDialog.show();

}



    @Override
    public void onclick(Serie serie) {
        this.serie = serie;
        carregarPreviaSerie();
    }
}
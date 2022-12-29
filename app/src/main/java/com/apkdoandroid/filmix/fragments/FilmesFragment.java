package com.apkdoandroid.filmix.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.activity.CategoriaActivity;
import com.apkdoandroid.filmix.activity.DownloadActivity;
import com.apkdoandroid.filmix.activity.PlayerActivity;
import com.apkdoandroid.filmix.activity.PlayerVideoNativoActivity;
import com.apkdoandroid.filmix.activity.VisualizarActivity;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.model.Filme;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FilmesFragment extends Fragment implements ListnerFilme {
    private View view;
    private List<Filme> filmes = new ArrayList<>();
    private Filme filme = new Filme();
    private RecyclerView recyclerView;
    private AdapterFillme adapter;
    private DaoBanco dao ;

    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAd2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_filmes, container, false);

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });


        adapter = new AdapterFillme(this,filmes,getActivity());
        recyclerView = view.findViewById(R.id.recyclerViewFilmes);
        dao = new DaoBanco(getActivity());
//        carregar();
        recyclerView();


        return view;

    }
    public void carregarAnunciointersticiais(){
        AdRequest adRequest = new AdRequest.Builder().build();
        anuncio(adRequest);
        anuncio2(adRequest);
    }
    private void anuncio( AdRequest adRequest){
        InterstitialAd.load(getActivity(),"ca-app-pub-6422137823083835/5423527546", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Chamado quando o conteúdo em tela cheia é dispensado.
                                if(filme.getFirebase() != null ){
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(getActivity(), PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(getActivity(), PlayerActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd = null;
                    }
                });
    }
    private void anuncio2( AdRequest adRequest){
        InterstitialAd.load(getActivity(),"ca-app-pub-6422137823083835/2724108361", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd2 = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd2.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Chamado quando o conteúdo em tela cheia é dispensado.
                                if(filme.getFirebase() != null ){
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(getActivity(), PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(getActivity(), PlayerActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);
                                }
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(AdError adError) {
                                // Called when fullscreen content failed to show.
                                Log.d("TAG", "The ad failed to show.");
                            }

                            @Override
                            public void onAdShowedFullScreenContent() {
                                // Called when fullscreen content is shown.
                                // Make sure to set your reference to null so you don't
                                // show it a second time.
                                mInterstitialAd2 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd2 = null;
                    }
                });
    }
    private void recyclerView() {



        adapter = new AdapterFillme(this, dao.listarFilme(), getActivity());
//        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager manager = new GridLayoutManager(getActivity(),3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void carregar() {

        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
    }
    public void carregarPrevia(){

        BottomSheetDialog sheetDialog = new BottomSheetDialog(getContext());

        View layout = LayoutInflater.from(getContext())
                .inflate(R.layout.layout_previa,view.findViewById(R.id.layout_prev));
        sheetDialog.setContentView(layout);
        LinearLayout maisInfo = layout.findViewById(R.id.layout_MaisInfo);
        Button assistir = layout.findViewById(R.id.buttonPrevAssistir);
        ImageButton close  = layout.findViewById(R.id.imageButtonPrevClose);
        ImageView capa = layout.findViewById(R.id.imageViewPrevCapa);
        TextView titulo = layout.findViewById(R.id.textViewPrevTitulo);
        TextView ano = layout.findViewById(R.id.textViewPrevAno);
        TextView retricao = layout.findViewById(R.id.textViewPrevRestri);
        TextView duracao = layout.findViewById(R.id.textViewPrevDuracao);
        TextView descricao = layout.findViewById(R.id.textViewPrevDescricao);
        Button download = layout.findViewById(R.id.buttonPrevDown);
        Glide.with(getActivity()).load(filme.getCapa()).placeholder(R.drawable.padrao).into(capa);
        titulo.setText(filme.getTitulo());
        ano.setText(filme.getAnoDeLancamento());
        retricao.setText(filme.getRestricao());
        duracao.setText(filme.getDuracao());
        descricao.setText(filme.getDescricao());
        maisInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(getActivity());
                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    Intent intent = new Intent(getActivity(), VisualizarActivity.class);
                    intent.putExtra("filme",(Serializable) filme);
                    startActivity(intent);

                }
                sheetDialog.dismiss();

            }
        });
        assistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd2 != null) {
                    mInterstitialAd2.show(getActivity());
                } else {
                    if(filme.getFirebase() != null ){
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(getActivity(), PlayerVideoNativoActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);

                    }else{
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(getActivity(), PlayerActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);
                    }
                }
                sheetDialog.dismiss();
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DownloadActivity.class);
                intent.putExtra("filme", (Serializable) filme);
                startActivity(intent);
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
    public void onclick(Filme filme) {
        this.filme = filme;
        carregarPrevia();
    }

    @Override
    public void Episodio(Filme filme) {

    }
}
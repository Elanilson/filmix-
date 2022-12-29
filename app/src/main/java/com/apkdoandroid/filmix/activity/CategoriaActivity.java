package com.apkdoandroid.filmix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.model.Filme;
import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
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

public class CategoriaActivity extends AppCompatActivity implements ListnerFilme {
    private List<Filme> filmes = new ArrayList<>();
    private Filme filme = new Filme();
    private RecyclerView recyclerView;
    private AdapterFillme adapter;
    private DaoBanco dao ;
    private String categoria;

    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAd2;
    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });

        mAdView = findViewById(R.id.adViewCategoria);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        recyclerView = findViewById(R.id.recyclerviewCategoria);
        dao = new DaoBanco(this);
        recuperandoFilme();
        recyclerView();


    }
    public void carregarAnunciointersticiais(){
        AdRequest adRequest = new AdRequest.Builder().build();
        anuncio(adRequest);
        anuncio2(adRequest);
    }
    private void anuncio( AdRequest adRequest){
        InterstitialAd.load(CategoriaActivity.this,"ca-app-pub-6422137823083835/5423527546", adRequest,
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
                                if(filme.getFirebase() != null){
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(CategoriaActivity.this, PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(CategoriaActivity.this, PlayerActivity.class);
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
        InterstitialAd.load(CategoriaActivity.this,"ca-app-pub-6422137823083835/2724108361", adRequest,
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
                                    Intent intent = new Intent(CategoriaActivity.this, PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(CategoriaActivity.this, PlayerActivity.class);
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
       // filmes.remove()

        adapter = new AdapterFillme(this, filmes, CategoriaActivity.this);
        GridLayoutManager manager = new GridLayoutManager(CategoriaActivity.this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void recuperandoFilme(){
        categoria = getIntent().getStringExtra("categoria");
        if(categoria.equalsIgnoreCase("recentemente")){
            filmes = dao.listarAdiconadoRecentimente();
        }else{
           filmes= dao.listarFilmePorCategoria(categoria);
        }

    }
    public void carregarPrevia(){

        BottomSheetDialog sheetDialog = new BottomSheetDialog(CategoriaActivity.this);

        View layout = LayoutInflater.from(CategoriaActivity.this)
                .inflate(R.layout.layout_previa,findViewById(R.id.layout_prev));
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

        Glide.with(CategoriaActivity.this).load(filme.getCapa()).placeholder(R.drawable.padrao).into(capa);
        titulo.setText(filme.getTitulo());
        ano.setText(filme.getAnoDeLancamento());
        retricao.setText(filme.getRestricao());
        duracao.setText(filme.getDuracao());
        descricao.setText(filme.getDescricao());
        maisInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(CategoriaActivity.this);
                } else {

                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(CategoriaActivity.this, VisualizarActivity.class);
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
                    mInterstitialAd2.show(CategoriaActivity.this);
                } else {
                    if(filme.getFirebase() != null ){
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(CategoriaActivity.this, PlayerVideoNativoActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);

                    }else{
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(CategoriaActivity.this, PlayerActivity.class);
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
                Intent intent = new Intent(CategoriaActivity.this, DownloadActivity.class);
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
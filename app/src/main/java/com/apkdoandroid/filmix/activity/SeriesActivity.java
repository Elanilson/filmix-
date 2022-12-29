package com.apkdoandroid.filmix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.adapter.AdapterSerie;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerSerie;
import com.apkdoandroid.filmix.model.Serie;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SeriesActivity extends AppCompatActivity implements ListnerSerie {
    private RecyclerView recyclerView;
    private AdapterSerie adapter;
    private DaoBanco dao;
    private Serie serie = new Serie();
    private List<Serie> series = new ArrayList<>();
    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAd2;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_series);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });

        mAdView = findViewById(R.id.adViewvisualizarSerrre);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        dao = new DaoBanco(SeriesActivity.this);

        recyclerView = findViewById(R.id.recyclerViewSeriesActive);
        recyclerView();
    }
    public void carregarAnunciointersticiais(){
        AdRequest adRequest = new AdRequest.Builder().build();
        anuncio(adRequest);
        anuncio2(adRequest);
    }
    private void anuncio( AdRequest adRequest){
        InterstitialAd.load(SeriesActivity.this,"ca-app-pub-6422137823083835/3216186086", adRequest,
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
                                Intent intent = new Intent(SeriesActivity.this, VisualizarActivity.class);
                                intent.putExtra("serie",(Serializable) serie);
                                startActivity(intent);
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
        InterstitialAd.load(SeriesActivity.this,"ca-app-pub-6422137823083835/6113828079", adRequest,
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
                                Intent intent = new Intent(SeriesActivity.this, VisualizarActivity.class);
                                intent.putExtra("serie",(Serializable) serie);
                                startActivity(intent);
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
        series = dao.listarSerie();
        adapter = new AdapterSerie(this, series, SeriesActivity.this);

//        RecyclerView.LayoutManager manager = new LinearLayoutManager(VisualizarActivity.this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager manager = new GridLayoutManager(SeriesActivity.this,3);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onclick(Serie serie) {
        this.serie = serie;
        if(serie != null){
            if (mInterstitialAd != null) {
                mInterstitialAd.show(SeriesActivity.this);
            } else {
                if (mInterstitialAd2 != null) {
                    mInterstitialAd2.show(SeriesActivity.this);
                } else {
                    Intent intent = new Intent(SeriesActivity.this, VisualizarActivity.class);
                    intent.putExtra("serie",(Serializable) serie);
                    startActivity(intent);

                }

            }

        }


    }
}
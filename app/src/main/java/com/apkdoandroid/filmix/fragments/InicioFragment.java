package com.apkdoandroid.filmix.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import android.widget.Toast;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.activity.CategoriaActivity;
import com.apkdoandroid.filmix.activity.DownloadActivity;
import com.apkdoandroid.filmix.activity.PlayerActivity;
import com.apkdoandroid.filmix.activity.PlayerVideoNativoActivity;
import com.apkdoandroid.filmix.activity.SeriesActivity;
import com.apkdoandroid.filmix.activity.VisualizarActivity;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.adapter.AdapterLancamento;
import com.apkdoandroid.filmix.adapter.AdapterSerie;
import com.apkdoandroid.filmix.adapter.AdapterTop10;
import com.apkdoandroid.filmix.adapter.SliderAdapter;
import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.listeners.ListnerSerie;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Item;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.service.DataService;
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
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InicioFragment extends Fragment implements ListnerFilme, ListnerSerie {

    private View view;
    private AdapterFillme adapter;
    private RecyclerView recyclerView, recyclerView2, recyclerView3, recyclerView4, recyclerView1,recyclerViewDestaque;
    private List<Filme> filmes = new ArrayList<>();
    private Filme filme = new Filme();
    private Serie serie = new Serie();
    private List<Serie> series = new ArrayList<>();
    private SliderView sliderView;
    private SliderAdapter adapterSlider;
    private AdapterTop10 adapterTop10;
    private AdapterSerie adapterSerie;
    private AdapterLancamento adapterLancamento;
    private DaoBanco dao;
    private LinearLayout layout1,layout2,layout3,layout4,layout5;
    private Retrofit retrofit;
private InterstitialAd mInterstitialAd;
private InterstitialAd mInterstitialAd2;
    private AdView mAdView,mAdView2,mAdView3,mAdView4,mAdView5;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_inicio, container, false);
        dao = new DaoBanco(getActivity());
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();



        carregarJson();

        MobileAds.initialize(getActivity(), new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });

      /*  mAdView = view.findViewById(R.id.adView111);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView2 = view.findViewById(R.id.adView112);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView2.loadAd(adRequest2);
        mAdView3 = view.findViewById(R.id.adView113);
        AdRequest adRequest3 = new AdRequest.Builder().build();
        mAdView3.loadAd(adRequest3);*/






        adapterLancamento = new AdapterLancamento(this,filmes,getActivity());
//        adapterSerie = new AdapterLancamento(this,s,getActivity());
        adapterTop10 = new AdapterTop10(this,dao.listarFilmeTop10(),getActivity());
        System.out.println("create fragmento inicio");


        sliderView = view.findViewById(R.id.imageSlider);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerViewDestaque = view.findViewById(R.id.recyclerViewDesqtaque);
        recyclerView1 = view.findViewById(R.id.recyclerview1);
        recyclerView2 = view.findViewById(R.id.recyclerview2);
        recyclerView3 = view.findViewById(R.id.recyclerview3);
        recyclerView4 = view.findViewById(R.id.recyclerview4);
        layout1 = view.findViewById(R.id.Linearlayout1);
        layout2 = view.findViewById(R.id.Linearlayout2);
        layout3 = view.findViewById(R.id.Linearlayout3);
        layout4 = view.findViewById(R.id.Linearlayout4);
        layout5 = view.findViewById(R.id.Linearlayout5);

        adapterSlider = new SliderAdapter(getActivity());
        sliderView.setSliderAdapter(adapterSlider);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); // define a animação do indicador usando IndicatorAnimationType. : WORM ou THIN_WORM ou COLOR ou DROP ou FILL ou NONE ou SCALE ou SCALE_DOWN ou SLIDE e SWAP !!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3);// define o atraso de rolagem em segundos:
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


//        carregar();
        recyclerView(filmes);
        recyclerViewDestaque(filmes);
        recyclerView1(filmes);
        recyclerView2(filmes);
        recyclerView3(filmes);
        recyclerView4(filmes);
        carregarSlide();

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriaActivity.class);
                intent.putExtra("categoria","recentemente");
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriaActivity.class);
//                Intent intent = new Intent(getActivity(), SeriesActivity.class);
                intent.putExtra("categoria","Comédia");
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriaActivity.class);
                intent.putExtra("categoria","Drama");
                startActivity(intent);
            }
        });
        layout4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriaActivity.class);
                intent.putExtra("categoria","Suspense");
                startActivity(intent);
            }
        });
        layout5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CategoriaActivity.class);
                intent.putExtra("categoria","Ação");
                startActivity(intent);
            }
        });





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

    private void carregarJson(){

        filmes = dao.listarFilme();
//        DataService service = retrofit.create(DataService.class);
//        Call<List<Filme>> call = service.filmes();
//        call.enqueue(new Callback<List<Filme>>() {
//            @Override
//            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
//                if(response.isSuccessful()){
//                    System.out.println("Sucesso: "+response.body().get(0).toString());
//                    filmes.addAll(response.body())  ;
//                    System.out.println("Total: "+response.body().size());
//
//                }else {
//                    System.out.println("Falha: "+response.message()+ "\n Cod: "+response.code());
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Filme>> call, Throwable t) {
//                System.out.println("Error: "+t.getMessage());
//                System.out.println("Error: "+t.getLocalizedMessage());
//                System.out.println("Error: "+t.getCause());
//
//            }
//        });
    }
    private void carregarSlide(){
        DataService service = retrofit.create(DataService.class);
        Call<List<Filme>> call = service.slider();
        call.enqueue(new Callback<List<Filme>>() {
            @Override
            public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                if(response.isSuccessful()){
                    renewItems(response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Filme>> call, Throwable t) {

            }
        });
    }

    public void renewItems(List<Filme> filmes) {
        List<Item> sliderItemList = new ArrayList<>();
        //dummy data
        for (int i = 0; i <= 5; i++) {
            Item sliderItem = new Item();
            sliderItem.setDescricao("Slider Item " + i);


            if (i == 0) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            } else if (i == 1) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            } else if (i == 2) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            } else if (i == 3) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            } else if (i == 4) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            } else if (i == 5) {
                sliderItem.setUrl_imagem(filmes.get(i).getCapa());
            }
            sliderItemList.add(sliderItem);
            //  sliderItemList = dao.listarSlide("Turma da Mônica");
        }
        adapterSlider.renewItems(sliderItemList);
    }
    public void carregarPrevia(){

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
        Glide.with(getActivity()).load(filme.getCapa()).placeholder(R.drawable.padrao).into(capa);
        titulo.setText(filme.getTitulo());
        ano.setText(filme.getAnoDeLancamento());
        retricao.setText(filme.getRestricao());
        duracao.setText(filme.getDuracao());
        descricao.setText(filme.getDescricao());
        maisInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getActivity(), VisualizarActivity.class));

//                if (mInterstitialAd != null) {
//                    mInterstitialAd.show(getActivity());
//                } else {
                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                    Intent intent = new Intent(getActivity(), VisualizarActivity.class);
                    intent.putExtra("filme",(Serializable) filme);
                    startActivity(intent);

//                }
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
        textView12MaisInformacoes.setText("Episódios e informações");
        download.setVisibility(View.GONE);
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


    private void recyclerView(List<Filme> filmes) {


        adapter = new AdapterFillme(this, dao.listarAdiconadoRecentimente(), getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
    private void recyclerViewDestaque(List<Filme> filmes) {


        //adapter = new AdapterFillme(this, filmes, getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewDestaque.setLayoutManager(manager);
        recyclerViewDestaque.setAdapter(adapterTop10);
        adapterTop10.notifyDataSetChanged();

    }

    private void recyclerView1(List<Filme> filmes) {


        adapter = new AdapterFillme(this,  dao.listarFilmePorCategoria("Comédia"), getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView1.setLayoutManager(manager);
        recyclerView1.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void recyclerView2(List<Filme> filmes) {


        adapter = new AdapterFillme(this,  dao.listarFilmePorCategoria("Drama"), getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void recyclerView3(List<Filme> filmes) {


        adapter = new AdapterFillme(this, dao.listarFilmePorCategoria("Suspense"), getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView3.setLayoutManager(manager);
        recyclerView3.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void recyclerView4(List<Filme> filmes) {


        adapter = new AdapterFillme(this,  dao.listarFilmePorCategoria("Ação"), getActivity());
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(manager);
        recyclerView4.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }

    private void carregar() {

        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
    }



    @Override
    public void onclick(Filme filme) {
        this.filme = filme;
        carregarPrevia();
//        startActivity(new Intent(getActivity(), VisualizarActivity.class));
//        startActivity(new Intent(getActivity(), PlayerActivity.class));

//         startActivity(new Intent(getActivity(), PlayerActivity.class));

    }

    @Override
    public void Episodio(Filme filme) {

    }


    @Override
    public void onclick(Serie serie) {
        this.serie = serie;
        carregarPreviaSerie();
    }

    @Override
    public void onStart() {
        super.onStart();
        System.out.println("onStart");


    }

    @Override
    public void onPause() {
        super.onPause();

        System.out.println("onPause");
    }


    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
        adapterLancamento.notifyDataSetChanged();
        adapterTop10.notifyDataSetChanged();

        System.out.println("onResume");
    }
}
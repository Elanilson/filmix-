package com.apkdoandroid.filmix.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.adapter.AdapterEpisodio;
import com.apkdoandroid.filmix.adapter.AdapterFillme;
import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.listeners.ListnerFilme;
import com.apkdoandroid.filmix.listeners.ListnerSerie;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.model.Usuario;
import com.apkdoandroid.filmix.service.DataService;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskViews;
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
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VisualizarActivity extends AppCompatActivity implements ListnerFilme,
        AdapterView.OnItemSelectedListener, ListnerSerie {
    private RecyclerView recyclerView;
    private AdapterFillme adapter;
    private AdapterEpisodio adapterEpisodio;
    private List<Filme> filmes = new ArrayList<>();
    private Filme filme = new Filme();
    private Serie serie = new Serie();
    private VideoView videoView;
    private TextView titulo, descricao, anoLancamento, restricao, duracao, elenco, direcao, views, semelhantes, textVie;
    private Button btnassistir,buttonVisuAssistir2, btndownload, btnSmart;
    private ImageView imageViewFavoritoAdd, imagemCapaFilme;
    private LinearLayout linarLayoutLista;
    private DaoBanco dao;
    private Spinner spinnerTemporada;
    int testeTem = 0;
    Dialog dialogAva;
    private LinearLayout problemalayout;
    private Retrofit retrofit;

    private AdView mAdView;
    private InterstitialAd mInterstitialAd;
    private InterstitialAd mInterstitialAd2;
    private InterstitialAd mInterstitialAd3;
    private InterstitialAd mInterstitialAd4;
    private InterstitialAd mInterstitialAd5;
    private InterstitialAd mInterstitialAd6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });

        mAdView = findViewById(R.id.adViewvisualizar);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        //startActivity(new Intent(this,SplashActivity.class));
        dao = new DaoBanco(this);
        carregar();
        recyclerView = findViewById(R.id.recyclerviewSemelhantes);
        problemalayout = findViewById(R.id.problema);
        linarLayoutLista = findViewById(R.id.linarLayoutLista);
        imagemCapaFilme = findViewById(R.id.imageView12CapaFilme);
        imageViewFavoritoAdd = findViewById(R.id.imageViewAddListaFavorito);
        titulo = findViewById(R.id.textViewVisuTitulo);
        descricao = findViewById(R.id.textViewVisuDescricao);
        anoLancamento = findViewById(R.id.textViewVisuAnoLancamento);
        restricao = findViewById(R.id.textViewVisuRestri);
        duracao = findViewById(R.id.textViewVisuDuracao);
        elenco = findViewById(R.id.textViewVisuElenco);
        direcao = findViewById(R.id.textViewVisuDirecao);
        btnassistir = findViewById(R.id.buttonVisuAssistir);
        buttonVisuAssistir2 = findViewById(R.id.buttonVisuAssistir2);
        btndownload = findViewById(R.id.buttonVisuDownload);
        views = findViewById(R.id.textView16Views);
        spinnerTemporada = findViewById(R.id.spinnerTemporada);
        semelhantes = findViewById(R.id.textViewOpcoesSemelhantes);
        btnSmart = findViewById(R.id.buttonAssistirAgora);
        textVie = findViewById(R.id.textVie);
        dialogAva = new Dialog(this);

//        videoView = findViewById(R.id.videoViewPrevia);
        recuperandoDados(); // carrega filme e series


        // recyclerView(filmes);
        verificarSeEstaNaListaDeFavoritos(); // carregar depois do filme ou serie


//        videoView.setMediaController(new MediaController(this));
//        Uri url = Uri.parse("https://apkdoandroidonline.com/video.mp4");
//        videoView.setVideoURI(url);
//        videoView.start();

        btnSmart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (mInterstitialAd3 != null) {
                    mInterstitialAd3.show(VisualizarActivity.this);
                } else {
                    if (mInterstitialAd4 != null) {
                        mInterstitialAd4.show(VisualizarActivity.this);
                    } else {
                        Intent intent = new Intent(VisualizarActivity.this, TVSmartActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);

                    }

                }

            }
        });

        linarLayoutLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                System.out.println("click em favorito " + filme.toString());
                String tipo = "com.apkdoandroid.filmix.model.Filme";
                if (tipo.equalsIgnoreCase(filme.getClass().getName())) { // verifica o nome do objeto
                    if (dao.verificarFavorito(filme.getId(), filme.getClass().getName())) {//  retorna true se existir no favorito

                        if (filme != null) { // verificar se é diferente de nullo
                            if (dao.deletarFavorito(filme, new Usuario())) { // deleta dos favoritos
                                verificarSeEstaNaListaDeFavoritos(); // ele verificar se o objeto cotem nos favoritso e muda o icone
                            }

                        }

                    } else { //se não existir
                        if (filme != null) { // verifica se é diferente de nullo
                            if (dao.salvarFavorito(filme, new Usuario())) { // salvar nos favoritos
                                verificarSeEstaNaListaDeFavoritos();// ele verificar se o objeto cotem nos favoritso e muda o icone
                            }
                        }


                    }
                } else {
                    if (dao.verificarFavorito(serie.getId(), serie.getClass().getName())) {
                        if (serie != null) { // verificar se é diferente de nullo
                            if (dao.deletarFavorito(serie, new Usuario())) { // deleta dos favoritos
                                verificarSeEstaNaListaDeFavoritos(); // ele verificar se o objeto cotem nos favoritso e muda o icone
                            }

                        }

                    } else { //se não existir
                        if (filme != null) { // verifica se é diferente de nullo
                            if (dao.salvarFavorito(serie, new Usuario())) { // salvar nos favoritos
                                verificarSeEstaNaListaDeFavoritos();// ele verificar se o objeto cotem nos favoritso e muda o icone
                            }
                        }


                    }
                }


//                System.out.println("Nome: "+filme.getClass().getName());
//                System.out.println("Nome: "+serie.getClass().getName());
            }
        });
        problemalayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertProblema();

            }
        });

        buttonVisuAssistir2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd2 != null) {
                    mInterstitialAd2.show(VisualizarActivity.this);
                } else {
                    if(filme.getFirebase() != null ){
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(VisualizarActivity.this, PlayerVideoNativoActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);

                    }else{
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);
                    }
                }

                finish();
            }
        });

        btnassistir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mInterstitialAd != null) {
                    mInterstitialAd.show(VisualizarActivity.this);
                } else {
                    if(filme.getFirebase() != null ){
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(VisualizarActivity.this, PlayerVideoNativoActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);

                    }else{
                        Log.d("TAG", "The interstitial ad wasn't ready yet.");
                        Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
                        intent.putExtra("filme", (Serializable) filme);
                        startActivity(intent);
                    }
                }

                finish();
            }
        });
        btndownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VisualizarActivity.this, DownloadActivity.class);
                intent.putExtra("filme", (Serializable) filme);
                startActivity(intent);
            }
        });

        // listar temporadas
        if (serie != null) {
            //  System.out.println("Total de temporadas: "+dao.totalDeTemporada(serie.getId()));
            List<String> temporadas = new ArrayList<>();
            int total = dao.totalDeTemporada(serie.getId());
            // total  = serie.getTemporada();
            for (int i = 1; i <= total; i++) {
                temporadas.add(i + " Temporada");
            }
            spinnerTemporada.setOnItemSelectedListener(this);
            spinnerTemporada.setAdapter(new ArrayAdapter<String>(VisualizarActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, temporadas));


        }
        if (!dao.verificarAvaliacao()) {
            avaliacao();

        } else {
            System.out.println("já foi avaliado");
        }

        //telacheia();


    }

    private void telacheia(){
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Defina o conteúdo para aparecer nas barras do sistema para que o
                        // o conteúdo não é redimensionado quando as barras do sistema são ocultadas e exibidas.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Ocultar a barra de navegação e a barra de status
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    public void carregarAnunciointersticiais() {
        AdRequest adRequest = new AdRequest.Builder().build();
        anuncio(adRequest);
        anuncio2(adRequest);
        anuncio3(adRequest);
        anuncio4(adRequest);
        anuncio5(adRequest);
        anuncio6(adRequest);
    }

    private void anuncio(AdRequest adRequest) {
        InterstitialAd.load(VisualizarActivity.this, "ca-app-pub-6422137823083835/5423527546", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                // Chamado quando o conteúdo em tela cheia é dispensado.
                                if(filme.getFirebase() != null ){
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(VisualizarActivity.this, PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
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
        InterstitialAd.load(VisualizarActivity.this,"ca-app-pub-6422137823083835/2724108361", adRequest,
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
                                    Intent intent = new Intent(VisualizarActivity.this, PlayerVideoNativoActivity.class);
                                    intent.putExtra("filme", (Serializable) filme);
                                    startActivity(intent);

                                }else{
                                    Log.d("TAG", "The interstitial ad wasn't ready yet.");
                                    Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
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
    private void anuncio3( AdRequest adRequest){
        InterstitialAd.load(VisualizarActivity.this,"ca-app-pub-6422137823083835/4257224450", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd3 = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd3.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent intent = new Intent(VisualizarActivity.this, TVSmartActivity.class);
                                intent.putExtra("filme", (Serializable) filme);
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
                                mInterstitialAd3 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd3 = null;
                    }
                });
    }
    private void anuncio4( AdRequest adRequest){
        InterstitialAd.load(VisualizarActivity.this,"ca-app-pub-6422137823083835/4804019363", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd4 = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd4.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent intent = new Intent(VisualizarActivity.this, TVSmartActivity.class);
                                intent.putExtra("filme", (Serializable) filme);
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
                                mInterstitialAd4 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd4 = null;
                    }
                });
    }
    private void anuncio5( AdRequest adRequest){
        InterstitialAd.load(VisualizarActivity.this,"ca-app-pub-6422137823083835/8807440021", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd5 = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd5.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
                                intent.putExtra("filme", (Serializable) filme);
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
                                mInterstitialAd5 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd5 = null;
                    }
                });
    }
    private void anuncio6( AdRequest adRequest){
        InterstitialAd.load(VisualizarActivity.this,"ca-app-pub-6422137823083835/5086002659", adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd6 = interstitialAd;
                        //  Log.i(TAG, "onAdLoaded");

                        mInterstitialAd6.setFullScreenContentCallback(new FullScreenContentCallback(){
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
                                intent.putExtra("filme", (Serializable) filme);
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
                                mInterstitialAd6 = null;
                                Log.d("TAG", "The ad was shown.");
                            }
                        });
                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error
                        ///   Log.i(TAG, loadAdError.getMessage());
                        mInterstitialAd6 = null;
                    }
                });
    }

    private void avaliacao() {
        dialogAva.setContentView(R.layout.layout_avaliacao);

        dialogAva.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout layout = dialogAva.findViewById(R.id.linearLayoutavaliar);
        ImageView imageView = dialogAva.findViewById(R.id.imageView8Closse);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogAva.dismiss();
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.avaliacao(); // salva como true avaliado
                dialogAva.dismiss();
                String url = "https://play.google.com/store/apps/details?id=com.apkdoandroid.filmix";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        dialogAva.setCancelable(false);
        dialogAva.show();
    }

    private void atribuicao(Boolean opcao) {
        if (opcao) { // true é filme
            titulo.setText(filme.getTitulo());
            descricao.setText(filme.getDescricao());
            anoLancamento.setText(filme.getAnoDeLancamento());
            restricao.setText(filme.getRestricao());
            duracao.setText(filme.getDuracao());
            elenco.setText(filme.getElenco());
            direcao.setText(filme.getDirecao());
            String  vizu =filme.getViews() == null ? "0" :filme.getViews();
            views.setText("Views: " +vizu);
            Glide.with(this).load(filme.getCapa()).into(imagemCapaFilme);

        } else { // serie
            btnSmart.setVisibility(View.GONE);
            textVie.setVisibility(View.GONE);
            titulo.setText(serie.getTitulo());
            descricao.setText(serie.getDescricao());
            anoLancamento.setText(serie.getAnoDeLancamento());
            restricao.setText(serie.getRestricao());
            duracao.setText(""+dao.totalDeTemporada(serie.getId())+" Temporada");
            linarLayoutLista.setVisibility(View.GONE);
            problemalayout.setVisibility(View.GONE);
            elenco.setText(serie.getElenco());
            direcao.setText(serie.getDirecao());
            String  vizu =serie.getViews() == null ? "0" :serie.getViews();
            views.setText("Views: " +vizu);
            Glide.with(this).load(serie.getCapa()).into(imagemCapaFilme);

        }
    }

    private void verificarSeEstaNaListaDeFavoritos() {
        String tipo = "com.apkdoandroid.filmix.model.Filme";
        atribuicao(filme != null);
        if (filme != null) {
            if (dao.verificarFavorito(filme.getId(), filme.getClass().getName())) {//  retorna true
                imageViewFavoritoAdd.setImageResource(R.drawable.ic_adicionado_24);
            } else {
                imageViewFavoritoAdd.setImageResource(R.drawable.ic_adicionar_24);
            }

        } else {
            if (dao.verificarFavorito(serie.getId(), serie.getClass().getName())) {
                imageViewFavoritoAdd.setImageResource(R.drawable.ic_adicionado_24);
            } else {
                imageViewFavoritoAdd.setImageResource(R.drawable.ic_adicionar_24);
            }

        }
//        if(tipo.equalsIgnoreCase(filme.getClass().getName())){
//
//
//        }else{
//
//        }

    }

    private void recuperandoDados() {
        filme = (Filme) getIntent().getSerializableExtra("filme");
        serie = (Serie) getIntent().getSerializableExtra("serie");
        if (filme != null) {
            btnassistir.setVisibility(View.VISIBLE);
            buttonVisuAssistir2.setVisibility(View.VISIBLE);
            btndownload.setVisibility(View.VISIBLE);
            semelhantes.setVisibility(View.VISIBLE);
            recyclerViewFilme();
        } else {
            spinnerTemporada.setVisibility(View.VISIBLE);

        }
    }

    private void recyclerViewFilme() {
        adapter = new AdapterFillme(this, dao.listarFilmePorCategoria(filme.getCategoria()), VisualizarActivity.this);

//        RecyclerView.LayoutManager manager = new LinearLayoutManager(VisualizarActivity.this, LinearLayoutManager.HORIZONTAL, false);
        GridLayoutManager manager = new GridLayoutManager(VisualizarActivity.this,3);

        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void recyclerViewSerie(Long id, int temporada) {
        adapterEpisodio = new AdapterEpisodio(this, dao.listarEpisodios(id, temporada), VisualizarActivity.this);
//        adapterEpisodio = new AdapterEpisodio(this, dao.listarFilmePorCategoria(serie.getCategoria()), VisualizarActivity.this);

        LinearLayoutManager manager = new LinearLayoutManager(VisualizarActivity.this, LinearLayoutManager.VERTICAL, false);
//        GridLayoutManager manager = new GridLayoutManager(VisualizarActivity.this,3);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapterEpisodio);
        adapterEpisodio.notifyDataSetChanged();
        System.out.println("Carregando a "+temporada);
    }
    public void alertProblema(){

        BottomSheetDialog sheetDialog = new BottomSheetDialog(VisualizarActivity.this);

        View layout = LayoutInflater.from(VisualizarActivity.this)
                .inflate(R.layout.layout_problema,findViewById(R.id.layout_prev));
        sheetDialog.setContentView(layout);
        Button problema = layout.findViewById(R.id.buttonProblema);
        ImageButton close  = layout.findViewById(R.id.imageButtonPrevClose);
        ImageView capa = layout.findViewById(R.id.imageViewPrevCapa);
        TextView titulo = layout.findViewById(R.id.textViewPrevTitulo);
        EditText mensagem = layout.findViewById(R.id.editRelato);


        Glide.with(VisualizarActivity.this).load(filme.getCapa()).placeholder(R.drawable.padrao).into(capa);
        titulo.setText(filme.getTitulo());



        problema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!mensagem.getText().toString().equalsIgnoreCase("")){
                    DataService service = retrofit.create(DataService.class);
                    Call<List<Filme>> call = service.enviarRelato(filme.getId(), mensagem.getText().toString());
                    call.enqueue(new Callback<List<Filme>>() {
                        @Override
                        public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(VisualizarActivity.this, "Enviado som sucesso!", Toast.LENGTH_SHORT).show();
                                sheetDialog.dismiss();

                            }
                        }

                        @Override
                        public void onFailure(Call<List<Filme>> call, Throwable t) {
                            Toast.makeText(VisualizarActivity.this, "Falha ao enviar, tente novamente mais tarde!", Toast.LENGTH_SHORT).show();
                            sheetDialog.dismiss();

                        }
                    });
                }else{
                    Toast.makeText(VisualizarActivity.this, "Informe o problema!", Toast.LENGTH_SHORT).show();
                }

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

    private void carregar() {

        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
        filmes.add(new Filme());
    }

    @Override
    public void onclick(Filme filme) {
        Intent intent = new Intent(VisualizarActivity.this, VisualizarActivity.class);
        intent.putExtra("filme", (Serializable) filme);
        startActivity(intent);
        finish();
    }

    @Override
    public void Episodio(Filme filme) {
            this.filme =  filme;

        if (mInterstitialAd5 != null) {
            mInterstitialAd5.show(VisualizarActivity.this);
        } else {
            if (mInterstitialAd6 != null) {
                mInterstitialAd6.show(VisualizarActivity.this);
            } else {
                Intent intent = new Intent(VisualizarActivity.this, PlayerActivity.class);
                intent.putExtra("filme", (Serializable) filme);
                startActivity(intent);

            }

        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int posicao, long l) {
        //Toast.makeText(VisualizarActivity.this, posicao, Toast.LENGTH_SHORT).show();
        System.out.println("Posicao: " + posicao);
        recyclerViewSerie(serie.getId(), posicao + 1 );
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    @Override
    public void onclick(Serie serie) {
        this.serie = serie;


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    @Override
    protected void onResume() {
        super.onResume();


    }
}
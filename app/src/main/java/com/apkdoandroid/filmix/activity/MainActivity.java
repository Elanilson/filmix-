package com.apkdoandroid.filmix.activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.fragments.FilmesFragment;
import com.apkdoandroid.filmix.fragments.CategoriasFragment;
import com.apkdoandroid.filmix.fragments.InicioFragment;
import com.apkdoandroid.filmix.fragments.MinhaListaFragment;
import com.apkdoandroid.filmix.fragments.SeriesFragment;
import com.apkdoandroid.filmix.fragments.PesquisaFragment;
import com.apkdoandroid.filmix.model.App;
import com.apkdoandroid.filmix.service.DataService;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskDispositovo;
import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class MainActivity extends AppCompatActivity {
//    private BottomNavigationViewEx bottomNavigationViewEx;
    Dialog dialog;
    private DaoBanco dao;
    private Retrofit retrofit;

    private InterstitialAd mInterstitialAd;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        pedirPermisos();





        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                carregarAnunciointersticiais();
            }
        });

        mAdView = findViewById(R.id.adViewinicio);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        if (mInterstitialAd != null) {
            mInterstitialAd.show(this);
        } else {
            Log.d("TAG", "The interstitial ad wasn't ready yet.");
        }



        new CarregarJsonTaskDispositovo(this);
        dialog = new Dialog(this);
        dao = new DaoBanco(this);
//        bottomNavigationViewEx = findViewById(R.id.bottomNavigation);
       // bottomNavigationViewEx.setTextVisibility(true);

        //Configurar bottom navigation view
        configuraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit();

        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        DataService service = retrofit.create(DataService.class);
        Call<List<App>> call = service.versaoApp();
        call.enqueue(new Callback<List<App>>() {
            @Override
            public void onResponse(Call<List<App>> call, Response<List<App>> response) {
                if(response.isSuccessful()){
                    System.out.println("Sucesso ao conectar com a vesao");

                    if(response.body() != null){
                        System.out.println("Versao atual app: "+response.body().get(0).getVersao());
                        if(Constantes.VERSIONAtualApp != response.body().get(0).getVersao()){
                            atualizacao();
                            dao.avaliacaoUpdate();
                        }
                    }

                }
            }

            @Override
            public void onFailure(Call<List<App>> call, Throwable t) {
                    System.out.println("Falha ao conectar com a vesao");

            }
        });





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

    public void carregarAnunciointersticiais(){
        AdRequest adRequest = new AdRequest.Builder().build();
        anuncio(adRequest);
    }
    private void anuncio( AdRequest adRequest){
        InterstitialAd.load(this,"ca-app-pub-6422137823083835/4257224450", adRequest,
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
                                // Called when fullscreen content is dismissed.
                                Log.d("TAG", "The ad was dismissed.");
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

    private void atualizacao(){
        dialog.setContentView(R.layout.layout_atualizacao);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.BLACK));
        dialog.setCancelable(false);
        Button btnAtualizar = dialog.findViewById(R.id.buttonAtulizar);
        Button btnCancelar = dialog.findViewById(R.id.buttonCancelar);
        btnAtualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                String url = "https://play.google.com/store/apps/details?id=com.apkdoandroid.filmix";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        dialog.show();
    }
    private void configuraBottomNavigationView(){

        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation);

        //faz configurações iniciais do Bottom Navigation
//        bottomNavigationViewEx.enableAnimation(true);
//        bottomNavigationViewEx.enableItemShiftingMode(false);
//        bottomNavigationViewEx.enableShiftingMode(false);
//        bottomNavigationViewEx.setTextVisibility(true);

        //Habilitar navegação
        habilitarNavegacao( bottomNavigationViewEx );

        //configura item selecionado inicialmente
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(0);
        menuItem.setChecked(true);

    }

    /**
     * Método responsável por tratar eventos de click na BottomNavigation
     * @param viewEx
     */
    private void habilitarNavegacao(BottomNavigationViewEx viewEx){

        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                switch (item.getItemId()){
                    case R.id.menu_inicio :
                        fragmentTransaction.replace(R.id.viewPager, new InicioFragment()).commit();
                        return true;
                    case R.id.menu_lancamentos :
                        fragmentTransaction.replace(R.id.viewPager, new MinhaListaFragment()).commit();
                        return true;
                    case R.id.menu_filmes :
                        fragmentTransaction.replace(R.id.viewPager, new FilmesFragment()).commit();
                        return true;
                    case R.id.menu_generos :
                        fragmentTransaction.replace(R.id.viewPager, new CategoriasFragment()).commit();
                        return true;
                    case R.id.menu_pesquisar:
                        fragmentTransaction.replace(R.id.viewPager, new PesquisaFragment()).commit();
                        return true;

                }

                return false;
            }
        });

    }
//    public void pedirPermisos() {
//        // PERMISOS PARA ANDROID 6 O SUPERIOR
//        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(
//                    MainActivity.this,
//                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                    0
//            );
//
//        }
//    }

}
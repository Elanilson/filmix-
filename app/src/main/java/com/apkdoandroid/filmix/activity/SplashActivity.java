package com.apkdoandroid.filmix.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.adapter.AdapterSplash;
import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.model.App;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.service.DataService;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskCategorias;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskFilme;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskSeries;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private int liberado = 0 ; // 0 é nao  e 1 é sim
    private  int versaoApp = 17;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        retrofit = new Retrofit.Builder()
                .baseUrl(Constantes.SITE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        publicado();
        new ProcessamentoTask().execute();
        new CarregarJsonTaskFilme(SplashActivity.this);
        new CarregarJsonTaskCategorias(SplashActivity.this);
        new CarregarJsonTaskSeries(SplashActivity.this);

      telacheia();




        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                abrirTelaPrincipal();
                finish();

            }
        },5000);
    }
    private void publicado(){
        DataService service = retrofit.create(DataService.class);
        Call<List<App>> call = service.publicacao();
                    call.enqueue(new Callback<List<App>>() {
                        @Override
                        public void onResponse(Call<List<App>> call, Response<List<App>> response) {

                            if(response.isSuccessful()){
                              App item = new  App();
                                for(App app: response.body()){
                                    item = app;
                                }
                                System.out.println("Versao: "+item.getVersao());
                                System.out.println("publicado: "+item.getPublicado());

                                if(versaoApp == item.getVersao()){
                                    if(item.getPublicado() == 1){
                                        liberado = 1;
                                    }else{
                                        liberado = 0;
                                    }

                                }else{
                                    liberado = 1;
                                }


                            }else{
                                liberado = 1;
                            }

                        }

                        @Override
                        public void onFailure(Call<List<App>> call, Throwable t) {
                            liberado = 1;

                        }
                    });

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
    private void abrirTelaPrincipal(){
        if(liberado == 1){
            startActivity(new Intent(this, MainActivity.class));
        }else{
            startActivity(new Intent(this, LiberarAppActivity.class));
        }
    }

    private  class ProcessamentoTask extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ViewPager2 locationviewPage = findViewById(R.id.locationviewpage);
            locationviewPage.setAdapter(new AdapterSplash());
            locationviewPage.setClipToPadding(false);
            locationviewPage.setClipChildren(false);
            locationviewPage.setOffscreenPageLimit(1);
            locationviewPage.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_ALWAYS);
        }


        @Override
        protected Void doInBackground(Void... voids) {
            CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
            compositePageTransformer.addTransformer(new MarginPageTransformer(10));

            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }
}
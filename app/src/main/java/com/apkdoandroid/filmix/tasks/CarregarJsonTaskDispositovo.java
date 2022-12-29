package com.apkdoandroid.filmix.tasks;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;

import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarregarJsonTaskDispositovo {
    private static final String TAG = "Carregando Task";
    private List<Categoria> Categoriass = new ArrayList<>();
    private Retrofit retrofit;
    private DaoBanco dao ;
    private Context context;
    private Filme filme = new Filme();

    public CarregarJsonTaskDispositovo(Context context){
        this.context = context;
        dao = new DaoBanco(context);



        new ProcessamentoTask().execute();


    }

    private  class ProcessamentoTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constantes.SITE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            String product = Build.MODEL;
            String model = String.valueOf(Build.VERSION.SDK_INT);
            DataService service = retrofit.create(DataService.class);
            Call<List<Filme>> call = service.enviarDados(product, model);
            call.enqueue(new Callback<List<Filme>>() {
                @Override
                public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                    if(response.isSuccessful()){
                        System.out.println("dispositivo enviados com sucesso: ");
                    }else{
                        System.out.println("falha");
                    }
                }

                @Override
                public void onFailure(Call<List<Filme>> call, Throwable t) {
                    System.out.println("falha ao tentar conctar dispositivo nao enviado");

                }
            });



            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
    }
}

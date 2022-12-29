package com.apkdoandroid.filmix.tasks;

import android.content.Context;
import android.os.AsyncTask;

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

public class CarregarJsonTaskViews {
    private static final String TAG = "Carregando Task";
    private List<Categoria> Categoriass = new ArrayList<>();
    private Retrofit retrofit;
    private DaoBanco dao ;
    private Context context;
    private Filme filme = new Filme();

    public CarregarJsonTaskViews(Context context, Filme filme){
        this.context = context;
        dao = new DaoBanco(context);
        this.filme = filme;


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
            DataService service = retrofit.create(DataService.class);
            int views =0;
            if(filme.getViews() != null && filme.getViews() != "" && !filme.getViews().equalsIgnoreCase("")){
              views = Integer.parseInt(filme.getViews());
          }
            Call<List<Filme>> call = service.enviarViews(filme.getId(), views+1);
            call.enqueue(new Callback<List<Filme>>() {
                @Override
                public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                    if(response.isSuccessful()){
                        System.out.println("Views enviados com sucesso: id-"+filme.getId()+" - views-"+filme.getViews());
                    }else{
                        System.out.println("falha");
                    }
                }

                @Override
                public void onFailure(Call<List<Filme>> call, Throwable t) {
                    System.out.println("falha ao tentar conctar views nao enviado");
                    System.out.println("view cod: "+t.getMessage()+" "+t.getCause());
                }
            });

            Call<List<Filme>> call2 = service.receberViewsAtualizadas(filme.getId());
            call2.enqueue(new Callback<List<Filme>>() {
                @Override
                public void onResponse(Call<List<Filme>> call, Response<List<Filme>> response) {
                    if(response.isSuccessful()){
                        System.out.println("views id enviado com sucesso: id-"+filme.getId());
                        dao.atualizarViewsFilme(response.body().get(0));
                        System.out.println(response.body().get(0).toString());
                    }else{
                        System.out.println("falha");
                    }
                }

                @Override
                public void onFailure(Call<List<Filme>> call, Throwable t) {
                    System.out.println("falha ao tentar conctar views nao recebidos");
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

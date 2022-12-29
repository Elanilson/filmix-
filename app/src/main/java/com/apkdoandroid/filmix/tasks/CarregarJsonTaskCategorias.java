package com.apkdoandroid.filmix.tasks;

import android.content.Context;
import android.os.AsyncTask;

import com.apkdoandroid.filmix.banco.Constantes;
import com.apkdoandroid.filmix.banco.DaoBanco;
import com.apkdoandroid.filmix.model.Categoria;

import com.apkdoandroid.filmix.service.DataService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CarregarJsonTaskCategorias {
    private static final String TAG = "Carregando Task";
    private List<Categoria> Categoriass = new ArrayList<>();
    private Retrofit retrofit;
    private DaoBanco dao ;
    private Context context;


    public CarregarJsonTaskCategorias(Context context){
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
            DataService service = retrofit.create(DataService.class);
            Call<List<Categoria>> call = service.categorias();
            call.enqueue(new Callback<List<Categoria>>() {
                @Override
                public void onResponse(Call<List<Categoria>> call, Response<List<Categoria>> response) {
                    if(response.isSuccessful()){
//                        System.out.println("Sucesso: "+response.body().get(0).toString());
//                        Categoriass.addAll(response.body())  ;
//                        System.out.println("Total: "+response.body().size());
                        int ultimoId =dao.getUltimICategoria();
                        for(Categoria categoria : response.body()){
                            if(categoria !=  null){
                                if(categoria.getId() > ultimoId  || ultimoId  == 0){ // id  da categoria tem que ser maior que o id do ultimo categoria salvo
                                    dao.salvarCategoria(categoria);
                                }
                            }
                        }

                        System.out.println("Categorias Carregadas");

                    }else {
                        System.out.println("Falha: "+response.message()+ "\n Cod: "+response.code());
                    }
                }

                @Override
                public void onFailure(Call<List<Categoria>> call, Throwable t) {
                    System.out.println("Error: "+t.getMessage());
                    System.out.println("Error: "+t.getLocalizedMessage());
                    System.out.println("Error: "+t.getCause());

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

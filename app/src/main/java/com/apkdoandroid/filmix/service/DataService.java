package com.apkdoandroid.filmix.service;

import com.apkdoandroid.filmix.model.App;
import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface DataService {
    @GET("/filmix/api/getSlide.php")
    Call<List<Filme>> slider ();

    @GET("/filmix/api/getFilmes.php")
    Call<List<Filme>> filmes ();

    @GET("/filmix/api/getSeries.php")
    Call<List<Serie>> series ();

    @GET("/filmix/api/getCategorias.php")
    Call<List<Categoria>> categorias ();

    @GET("/filmix/api/getAtualizacao.php")
    Call<List<App>> versaoApp ();

    @GET("/filmix/api/publicado.php")
    Call<List<App>> publicacao ();

    @FormUrlEncoded
    @POST("/filmix/api/salvarDispositivo.php")
    Call<List<Filme>> enviarDados( @Field("product") String product,@Field("model") String model);

    @FormUrlEncoded
    @POST("/filmix/api/relato.php")
    Call<List<Filme>> enviarRelato( @Field("id") Long id,@Field("mensagem") String mensagem);


    @FormUrlEncoded
    @POST("/filmix/api/views.php")
    Call<List<Filme>> receberViewsAtualizadas ( @Field("id") Long id);

    @FormUrlEncoded
    @POST("filmix/api/atualizarFilme.php")
    Call<List<Filme>> enviarViews (
            @Field("id") Long id,
            @Field("views") int views
    );
}

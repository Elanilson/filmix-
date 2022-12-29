package com.apkdoandroid.filmix.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Item implements Serializable {
    @SerializedName("id")
    private String id;
    @SerializedName("titulo")
    private String titulo;
    @SerializedName("categoria")
    private String categoria;
    @SerializedName("descricao")
    private String descricao;
    @SerializedName("link_imagem")
    private String url_imagem;
    @SerializedName("link_pdf")
    private String link_pdf;
   // @SerializedName("data_lancamento")
    private String data_lancamento;
    private String temaPesquisa;


    public Item() {
    }

    public Item(String titulo, String url_imagem) {
        this.titulo = titulo;
        this.url_imagem = url_imagem;
    }

    public Item(String id, String titulo, String categoria, String descricao, String url_imagem, String link_pdf, String data_lancamento) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.url_imagem = url_imagem;
        this.link_pdf = link_pdf;
        this.data_lancamento = data_lancamento;
    }

    public Item(String id, String titulo, String categoria, String descricao, String url_imagem, String link_pdf) {
        this.id = id;
        this.titulo = titulo;
        this.categoria = categoria;
        this.descricao = descricao;
        this.url_imagem = url_imagem;
        this.link_pdf = link_pdf;
    }
    public Item(String titulo, String descricao, String url_imagem, String link_pdf) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.url_imagem = url_imagem;
        this.link_pdf = link_pdf;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", categoria='" + categoria + '\'' +
                ", descricao='" + descricao + '\'' +
                ", url_imagem='" + url_imagem + '\'' +
                ", link_pdf='" + link_pdf + '\'' +
                ", data_lancamento='" + data_lancamento + '\'' +
                ", temaPesquisa='" + temaPesquisa + '\'' +
                '}';
    }

    public String getTemaPesquisa() {
        return temaPesquisa;
    }

    public void setTemaPesquisa(String temaPesquisa) {
        this.temaPesquisa = temaPesquisa;
    }

    public String getData_lancamento() {
        return data_lancamento;
    }

    public void setData_lancamento(String data_lancamento) {
        this.data_lancamento = data_lancamento;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategria() {
        return categoria;
    }

    public void setCategria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getUrl_imagem() {
        return url_imagem;
    }

    public void setUrl_imagem(String url_imagem) {
        this.url_imagem = url_imagem;
    }

    public String getLink_pdf() {
        return link_pdf;
    }

    public void setLink_pdf(String link_pdf) {
        this.link_pdf = link_pdf;
    }
}

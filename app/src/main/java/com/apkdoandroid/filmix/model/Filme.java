package com.apkdoandroid.filmix.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Filme implements Serializable {
    //@SerializedName("id")
    private Long id;
    private String titulo;
    private String capa;
    private String descricao;
    private String categoria;
//    private List<String> categoria;
    private String avaliacao;
    private String restricao;
   // @SerializedName("views")
    private String views;
    private String anoDeLancamento;
    private String duracao;
    private String elenco;
//    private List<String> elenco;
    private String direcao;
    private String urlVideo;
    private String urlVideo2;
    private String urlVideo3;
    private String urlVideo4;
    private String urlVideo5;
    private String urlVideo6;
    private String urlVideo7;
    private String urlVideo8;
    private String firebase;
    private String dataUpload;
    private int idSerie;
    private String temporada;


    public Filme() {
    }

    public Filme(Long id) {
        this.id = id;
    }

    public Filme(Long id, String titulo, String capa, String descricao, String categoria, String avaliacao, String restricao, String views, String anoDeLancamento, String duracao, String elenco, String direcao, String urlVideo, String dataUpload, int idSerie, String temporada) {
        this.id = id;
        this.titulo = titulo;
        this.capa = capa;
        this.descricao = descricao;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
        this.restricao = restricao;
        this.views = views;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.elenco = elenco;
        this.direcao = direcao;
        this.urlVideo = urlVideo;
        this.dataUpload = dataUpload;
        this.idSerie = idSerie;
        this.temporada = temporada;
    }

    public Filme(Long id, String titulo, String capa, String descricao, String categoria, String avaliacao, String restricao, String views, String anoDeLancamento, String duracao, String elenco, String direcao, String urlVideo, String dataUpload, String temporada) {
        this.id = id;
        this.titulo = titulo;
        this.capa = capa;
        this.descricao = descricao;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
        this.restricao = restricao;
        this.views = views;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.elenco = elenco;
        this.direcao = direcao;
        this.urlVideo = urlVideo;
        this.dataUpload = dataUpload;
        this.temporada = temporada;
    }

    public Filme(Long id, String titulo, String capa, String descricao, String categoria, String avaliacao, String restricao, String views, String anoDeLancamento, String duracao, String elenco, String direcao, String urlVideo, String urlVideo2, String urlVideo3, String urlVideo4, String urlVideo5, String urlVideo6, String urlVideo7, String urlVideo8, String dataUpload, int idSerie, String temporada) {
        this.id = id;
        this.titulo = titulo;
        this.capa = capa;
        this.descricao = descricao;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
        this.restricao = restricao;
        this.views = views;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.elenco = elenco;
        this.direcao = direcao;
        this.urlVideo = urlVideo;
        this.urlVideo2 = urlVideo2;
        this.urlVideo3 = urlVideo3;
        this.urlVideo4 = urlVideo4;
        this.urlVideo5 = urlVideo5;
        this.urlVideo6 = urlVideo6;
        this.urlVideo7 = urlVideo7;
        this.urlVideo8 = urlVideo8;
        this.dataUpload = dataUpload;
        this.idSerie = idSerie;
        this.temporada = temporada;
    }


    public Filme(Long id, String titulo, String capa, String descricao, String categoria, String avaliacao, String restricao, String views, String anoDeLancamento, String duracao, String elenco, String direcao, String urlVideo, String urlVideo2, String urlVideo3, String urlVideo4, String urlVideo5, String urlVideo6, String urlVideo7, String urlVideo8, String firebase, String dataUpload, int idSerie, String temporada) {
        this.id = id;
        this.titulo = titulo;
        this.capa = capa;
        this.descricao = descricao;
        this.categoria = categoria;
        this.avaliacao = avaliacao;
        this.restricao = restricao;
        this.views = views;
        this.anoDeLancamento = anoDeLancamento;
        this.duracao = duracao;
        this.elenco = elenco;
        this.direcao = direcao;
        this.urlVideo = urlVideo;
        this.urlVideo2 = urlVideo2;
        this.urlVideo3 = urlVideo3;
        this.urlVideo4 = urlVideo4;
        this.urlVideo5 = urlVideo5;
        this.urlVideo6 = urlVideo6;
        this.urlVideo7 = urlVideo7;
        this.urlVideo8 = urlVideo8;
        this.firebase = firebase;
        this.dataUpload = dataUpload;
        this.idSerie = idSerie;
        this.temporada = temporada;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", capa='" + capa + '\'' +
                ", descricao='" + descricao + '\'' +
                ", categoria='" + categoria + '\'' +
                ", avaliacao='" + avaliacao + '\'' +
                ", restricao='" + restricao + '\'' +
                ", views='" + views + '\'' +
                ", anoDeLancamento='" + anoDeLancamento + '\'' +
                ", duracao='" + duracao + '\'' +
                ", elenco='" + elenco + '\'' +
                ", direcao='" + direcao + '\'' +
                ", urlVideo='" + urlVideo + '\'' +
                ", urlVideo2='" + urlVideo2 + '\'' +
                ", urlVideo3='" + urlVideo3 + '\'' +
                ", urlVideo4='" + urlVideo4 + '\'' +
                ", urlVideo5='" + urlVideo5 + '\'' +
                ", urlVideo6='" + urlVideo6 + '\'' +
                ", urlVideo7='" + urlVideo7 + '\'' +
                ", urlVideo8='" + urlVideo8 + '\'' +
                ", firebase='" + firebase + '\'' +
                ", dataUpload='" + dataUpload + '\'' +
                ", idSerie=" + idSerie +
                ", temporada='" + temporada + '\'' +
                '}';
    }

    public String getFirebase() {
        return firebase;
    }

    public void setFirebase(String firebase) {
        this.firebase = firebase;
    }

    public String getUrlVideo2() {
        return urlVideo2;
    }

    public void setUrlVideo2(String urlVideo2) {
        this.urlVideo2 = urlVideo2;
    }

    public String getUrlVideo3() {
        return urlVideo3;
    }

    public void setUrlVideo3(String urlVideo3) {
        this.urlVideo3 = urlVideo3;
    }

    public String getUrlVideo4() {
        return urlVideo4;
    }

    public void setUrlVideo4(String urlVideo4) {
        this.urlVideo4 = urlVideo4;
    }

    public String getUrlVideo5() {
        return urlVideo5;
    }

    public void setUrlVideo5(String urlVideo5) {
        this.urlVideo5 = urlVideo5;
    }

    public String getUrlVideo6() {
        return urlVideo6;
    }

    public void setUrlVideo6(String urlVideo6) {
        this.urlVideo6 = urlVideo6;
    }

    public String getUrlVideo7() {
        return urlVideo7;
    }

    public void setUrlVideo7(String urlVideo7) {
        this.urlVideo7 = urlVideo7;
    }

    public String getUrlVideo8() {
        return urlVideo8;
    }

    public void setUrlVideo8(String urlVideo8) {
        this.urlVideo8 = urlVideo8;
    }

    public int getIdSerie() {
        return idSerie;
    }

    public void setIdSerie(int idSerie) {
        this.idSerie = idSerie;
    }

    public String getTemporada() {
        return temporada;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCapa() {
        return capa;
    }

    public void setCapa(String capa) {
        this.capa = capa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getRestricao() {
        return restricao;
    }

    public void setRestricao(String restricao) {
        this.restricao = restricao;
    }

    public String getViews() {
        return views;
    }

    public void setViews(String views) {
        this.views = views;
    }

    public String getAnoDeLancamento() {
        return anoDeLancamento;
    }

    public void setAnoDeLancamento(String anoDeLancamento) {
        this.anoDeLancamento = anoDeLancamento;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public String getElenco() {
        return elenco;
    }

    public void setElenco(String elenco) {
        this.elenco = elenco;
    }

    public String getDirecao() {
        return direcao;
    }

    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }

    public String getUrlVideo() {
        return urlVideo;
    }

    public void setUrlVideo(String urlVideo) {
        this.urlVideo = urlVideo;
    }

    public String getDataUpload() {
        return dataUpload;
    }

    public void setDataUpload(String dataUpload) {
        this.dataUpload = dataUpload;
    }
}

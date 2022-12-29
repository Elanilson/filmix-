package com.apkdoandroid.filmix.model;

import java.util.List;

public class Usuario {
    private String id = "1";
    private String nome;
    private List<Integer> favoritos;
//    private String id;
//    private String id;
//    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Integer> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Integer> favoritos) {
        this.favoritos = favoritos;
    }
}

package com.apkdoandroid.filmix.banco;

import com.apkdoandroid.filmix.model.Usuario;

import java.util.List;

public interface UsuarioDao {
    public Boolean salvar(Usuario usuario);
    public Boolean atualizar(Usuario usuario);
    public Boolean deletar(Usuario usuario);
    public Usuario listar();

}

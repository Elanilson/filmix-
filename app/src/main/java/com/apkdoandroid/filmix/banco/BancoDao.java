package com.apkdoandroid.filmix.banco;

import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.model.Usuario;

import java.util.List;

public interface BancoDao {
    public Boolean salvar(Filme filme);
    public Boolean salvarSerie(Serie serie);
    public Boolean salvarCategoria(Categoria categoria);
    public Boolean salvarFavorito(Serie serie, Usuario usuario);
    public Boolean deletarFavorito(Serie serie, Usuario usuario);
    public Boolean salvarFavorito(Filme filme,Usuario usuario);
    public Boolean deletarFavorito(Filme filme,Usuario usuario);
    public Boolean atualizarSerie(Serie serie);
    public Boolean atualizarViewsFilme(Filme filme);
    public Boolean atualizar(Filme filme);
    public Boolean avaliacao();
    public Boolean avaliacaoUpdate();
    public Boolean salvarVersaoApp(int versao);
    public int getVersaoApp();
    public Boolean verificarAvaliacao();
    public Boolean deletar(Filme filme);
    public Boolean verificarFilme(Filme filme);
    public int totalDeTemporada(Long idSerieTemp);
    public List<Filme> listarFilmePorCategoria(String categoria);
    public List<Filme> listarEpisodios(Long id, int temporada);
    public List<Serie> listarSeriePorCategoria(String categoria);
    public List<Categoria> listarCategorias( );
    public Boolean verificarSerie(Serie serie);
    public Boolean verificarFavorito(Long id, String tipo);
    public List<Filme> listarFilme();
    public int getUltimIDFilme();
    public int getUltimIDSerie();
    public int getUltimICategoria();
    public List<Filme> listarFilmeTop10();
    public List<Filme> listarLancamentos();
    public List<Filme> pesquisa(String pesquisa);
    public List<Serie> pesquisaSerie(String pesquisa);
    public List<Filme> listarFilme(Long id);
    public List<Serie> listarSerie(int id);
    public List<Serie> listarSerie();
    public List<Object> listarFavoritos();
    public List<Filme> listarFavoritosFilme();
    public List<Filme> listarAdiconadoRecentimente();

}

package com.apkdoandroid.filmix.model;

import java.util.List;

public class Serie extends Filme {


    public Serie() {
    }



    public Serie(Long id, String titulo, String capa, String descricao, String categoria, String avaliacao, String restricao, String views, String anoDeLancamento, String duracao, String elenco, String direcao, String urlVideo, String dataUpload, String temporada) {
        super(id, titulo, capa, descricao, categoria, avaliacao, restricao, views, anoDeLancamento, duracao, elenco, direcao, urlVideo, dataUpload, temporada);
    }

    public Serie(Long id) {
        super(id);
    }
}

package com.apkdoandroid.filmix.helpers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by jamiltondamasceno
 */

public class VideosPreferencias {

    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private VideosPreferencias preferencias;

    private final String NOME_ARQUIVO = "videos.preferencias";
    private  String CHAVE_NOME = "vazio";

    public VideosPreferencias(Context c, String chave) {
        this.context = c;
        this.CHAVE_NOME = chave;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferences.edit();
    }

    public void salvarProgresso(int anotacao){
     //   editor.putString(CHAVE_NOME, anotacao );
        editor.putInt(CHAVE_NOME,anotacao);
        editor.commit();
    }

    public int recuperarProgresso(){
        return preferences.getInt(CHAVE_NOME, 0);
    }

}

package com.apkdoandroid.filmix.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoUsuario implements UsuarioDao {

    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private Context context;

    public DaoUsuario (Context context){
        Banco db =  new Banco(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
        this.context = context;
    }


    @Override
    public Boolean salvar(Usuario usuario) {
        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME,usuario.getNome());

            escrever.insert(Constantes.TABELAUSUARIO,null,cv);


           return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean atualizar(Usuario usuario) {

        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME,usuario.getNome());

            String [] args = {usuario.getId()};

            escrever.update(Constantes.TABELAUSUARIO,cv,"id = ?",args);


            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean deletar(Usuario usuario) {
        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.NOME,usuario.getNome());

            String [] args = {usuario.getId()};

            escrever.update(Constantes.TABELAUSUARIO,cv,"id = ?",args);


            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Usuario listar( ) {
        Usuario usuario = new Usuario();
        try{
            String sql = "SELECT * FROM "+Constantes.TABELAUSUARIO+";";
            Cursor cursor = ler.rawQuery(sql,null);
            while (cursor.moveToFirst()){
                usuario.setId(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ID)));
                usuario.setNome(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.NOME)));
            }


            return usuario;
        }catch (Exception e){
            return usuario;
        }
    }


}

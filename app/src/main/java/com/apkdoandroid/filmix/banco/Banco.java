package com.apkdoandroid.filmix.banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

;

public class Banco extends SQLiteOpenHelper {

    private Context context;
//    public static String

    public Banco(Context context) {
        super(context,Constantes.BANCO,null,Constantes.VERSION);
         this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String sqlFilme = "CREATE TABLE "+Constantes.TABELAFILME+" (" +
                    Constantes.ID+" int, "+
                    Constantes.TITULO+" VARCHAR(20), "+
                    Constantes.CATEGORIA+" VARCHAR(50), "+
                    Constantes.URLVIDEO+" TEXT, "+
                    Constantes.URLVIDEO2+" TEXT, "+
                    Constantes.URLVIDEO3+" TEXT, "+
                    Constantes.URLVIDEO4+" TEXT, "+
                    Constantes.URLVIDEO5+" TEXT, "+
                    Constantes.URLVIDEO6+" TEXT, "+
                    Constantes.URLVIDEO7+" TEXT, "+
                    Constantes.URLVIDEO8+" TEXT, "+
                    Constantes.FIREBASE+" varcahr(10), "+
                    Constantes.DESCRICAO+" TEXT, "+
                    Constantes.CAPA +" VARCHAR(60), "+
                    Constantes.AVALIACAO +" VARCHAR(5),"+
                    Constantes.VIEWS +" VARCHAR(15)," +
                    Constantes.ELENCO +" TEXT," +
                    Constantes.IDSERIE +" INTEGER," +
                    Constantes.TEMPORADA +" VARCHAR(20)," +
                    Constantes.DIRECAO +" VARCHAR(25)," +
                    Constantes.RESTRICAO+ " VARCHAR(2), "+
                    Constantes.ANOlANCAMENTO +" VARCHAR(4), "+
                    Constantes.DATAUPLOAD +" VARCHAR(10), "+
                    Constantes.DURACAO +" VARCHAR(7));";

            String sqlSerie = "CREATE TABLE "+Constantes.TABELASERIE+" (" +
                    Constantes.ID+" int, "+
                    Constantes.TITULO+" VARCHAR(20), "+
                    Constantes.CATEGORIA+" VARCHAR(50), "+
                    Constantes.URLVIDEO+" TEXT, "+
                    Constantes.DESCRICAO+" TEXT, "+
                    Constantes.CAPA +" VARCHAR(60), "+
                    Constantes.AVALIACAO +" VARCHAR(5),"+
                    Constantes.VIEWS +" VARCHAR(15)," +
                    Constantes.ELENCO +" TEXT," +
                    Constantes.TEMPORADA +" VARCHAR(20)," +
                    Constantes.DIRECAO +" VARCHAR(25)," +
                    Constantes.RESTRICAO+ " VARCHAR(2), "+
                    Constantes.ANOlANCAMENTO +" VARCHAR(4), "+
                    Constantes.DATAUPLOAD +" VARCHAR(10), "+
                    Constantes.DURACAO +" VARCHAR(7));";

            String sqlUsuario = "CREATE TABLE "+Constantes.TABELAUSUARIO+" (" +
                    Constantes.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Constantes.FAVORITOS+" VARCHAR(6), "+
                    Constantes.NOME +" VARCHAR(25));";

            String sqlFavorito = "CREATE TABLE "+Constantes.TABELAFAVORITOS+" (" +
                    Constantes.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    Constantes.IDUSUARIO+" INTEGER, "+
                    Constantes.IDFILME+" INTEGER, "+
                    Constantes.IDSERIE+" INTEGER);";
            String sqlCategoria = "CREATE TABLE "+Constantes.TABELACATEGORIAS+" (" +
                    Constantes.ID+" int, "+
                    Constantes.NOME+" VARCHAR(15));";
            String sqlAvaliacao = "CREATE TABLE "+Constantes.TABELAAVALIACAO+" (" +
                    Constantes.ID+" int default 1, "+
                    Constantes.AVALIADO+" int default 0);";
            String sqlAtualizacao = "CREATE TABLE "+Constantes.TABELAATUALIZACAO+" (" +
                    Constantes.ID+" int, "+
                    Constantes.VERSAOAPP+" int);";


            db.execSQL(sqlFilme);
            db.execSQL(sqlSerie);
            db.execSQL(sqlUsuario);
            db.execSQL(sqlFavorito);
            db.execSQL(sqlCategoria);
            db.execSQL(sqlAvaliacao);
            db.execSQL(sqlAtualizacao);
           // db.execSQL("insert into tbAvaliacao (avaliado) values (0)");
            System.out.println("Banco de dados criado");

        }catch (Exception e){
            System.out.println("Error ao criar banco de dados");

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}

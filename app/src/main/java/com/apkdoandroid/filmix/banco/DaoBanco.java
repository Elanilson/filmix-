package com.apkdoandroid.filmix.banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.apkdoandroid.filmix.model.Categoria;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;
import com.apkdoandroid.filmix.model.Usuario;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DaoBanco implements BancoDao {
    private SQLiteDatabase escrever;
    private SQLiteDatabase ler;
    private Context context;

    public DaoBanco (Context context){
        Banco db =  new Banco(context);
        escrever = db.getWritableDatabase();
        ler = db.getReadableDatabase();
        this.context = context;
    }

    @Override
    public Boolean salvar(Filme filme) {
        int idtester = 0;

        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,filme.getId());
            cv.put(Constantes.TITULO,filme.getTitulo());
            cv.put(Constantes.DESCRICAO,filme.getDescricao());
            cv.put(Constantes.CATEGORIA,filme.getCategoria());
            cv.put(Constantes.CAPA,filme.getCapa());
            cv.put(Constantes.ANOlANCAMENTO,filme.getAnoDeLancamento());
            cv.put(Constantes.AVALIACAO,filme.getAvaliacao());
            cv.put(Constantes.DATAUPLOAD,filme.getDataUpload());
            cv.put(Constantes.DIRECAO,filme.getDirecao());
            cv.put(Constantes.DURACAO,filme.getDuracao());
            cv.put(Constantes.ELENCO,filme.getElenco());
            cv.put(Constantes.IDSERIE,filme.getIdSerie());
            cv.put(Constantes.RESTRICAO,filme.getRestricao());
            cv.put(Constantes.TEMPORADA,filme.getTemporada());
            cv.put(Constantes.URLVIDEO,filme.getUrlVideo());
            cv.put(Constantes.URLVIDEO2,filme.getUrlVideo2());
            cv.put(Constantes.URLVIDEO3,filme.getUrlVideo3());
            cv.put(Constantes.URLVIDEO4,filme.getUrlVideo4());
            cv.put(Constantes.URLVIDEO5,filme.getUrlVideo5());
            cv.put(Constantes.URLVIDEO6,filme.getUrlVideo6());
            cv.put(Constantes.URLVIDEO7,filme.getUrlVideo7());
            cv.put(Constantes.URLVIDEO8,filme.getUrlVideo8());
            cv.put(Constantes.FIREBASE,filme.getFirebase());
            cv.put(Constantes.VIEWS,filme.getViews());


            escrever.insert(Constantes.TABELAFILME,null,cv);
            System.out.println("Filme salvo");
            idtester++;


          return   true;
        }catch (Exception e){
           return false;
        }

    }

    @Override
    public Boolean salvarSerie(Serie serie) {
        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,serie.getId());
            cv.put(Constantes.TITULO,serie.getTitulo());
            cv.put(Constantes.DESCRICAO,serie.getDescricao());
            cv.put(Constantes.CATEGORIA,serie.getCategoria());
            cv.put(Constantes.CAPA,serie.getCapa());
            cv.put(Constantes.ANOlANCAMENTO,serie.getAnoDeLancamento());
            cv.put(Constantes.AVALIACAO,serie.getAvaliacao());
            cv.put(Constantes.DATAUPLOAD,serie.getDataUpload());
            cv.put(Constantes.DIRECAO,serie.getDirecao());
            cv.put(Constantes.DURACAO,serie.getDuracao());
            cv.put(Constantes.ELENCO,serie.getElenco());
           // cv.put(Constantes.IDSERIE,serie.getIdSerie());
            cv.put(Constantes.RESTRICAO,serie.getRestricao());
            cv.put(Constantes.TEMPORADA,serie.getTemporada());
            cv.put(Constantes.URLVIDEO,serie.getUrlVideo());
            cv.put(Constantes.VIEWS,serie.getViews());

            escrever.insert(Constantes.TABELASERIE,null,cv);
            System.out.println("Serie salva");


            return   true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean salvarCategoria(Categoria categoria) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,categoria.getId());
            cv.put(Constantes.NOME,categoria.getNome());
            escrever.insert(Constantes.TABELACATEGORIAS,null,cv);
            System.out.println("Categoria salva com sucesso");
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public Boolean salvarFavorito(Serie serie,Usuario usuario) {

        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDUSUARIO,usuario.getId());
            cv.put(Constantes.IDSERIE,serie.getId());
            escrever.insert(Constantes.TABELAFAVORITOS,null,cv);
            System.out.println("Serie salva como favoritada");
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public Boolean deletarFavorito(Serie serie, Usuario usuario) {
        try{
            String [] args = {serie.getId().toString()};
            escrever.delete(Constantes.TABELAFAVORITOS,"idSerie=?",args);
            System.out.println("Serie deletada dos favoritos");

            return true;
        }catch (Exception e){
            e.printStackTrace();

            return  false;

        }
    }

    @Override
    public Boolean salvarFavorito(Filme filme,Usuario usuario) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.IDUSUARIO,usuario.getId());
            cv.put(Constantes.IDFILME,filme.getId());
            escrever.insert(Constantes.TABELAFAVORITOS,null,cv);
            System.out.println("Filme salvo como favoritado");
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public Boolean deletarFavorito(Filme filme, Usuario usuario) {
        try{
            String [] args = {filme.getId().toString()};
            escrever.delete(Constantes.TABELAFAVORITOS,"idFilme=?",args);
            System.out.println("Filme deletado dos favoritos");

            return true;
        }catch (Exception e){
            e.printStackTrace();

            return  false;

        }
    }

    @Override
    public Boolean atualizarSerie(Serie serie) {
        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,serie.getId());
            cv.put(Constantes.TITULO,serie.getTitulo());
            cv.put(Constantes.DESCRICAO,serie.getDescricao());
            cv.put(Constantes.CATEGORIA,serie.getCategoria());
            cv.put(Constantes.CAPA,serie.getCapa());
            cv.put(Constantes.ANOlANCAMENTO,serie.getAnoDeLancamento());
            cv.put(Constantes.AVALIACAO,serie.getAvaliacao());
            cv.put(Constantes.DATAUPLOAD,serie.getDataUpload());
            cv.put(Constantes.DIRECAO,serie.getDirecao());
            cv.put(Constantes.DURACAO,serie.getDuracao());
            cv.put(Constantes.ELENCO,serie.getElenco());
            cv.put(Constantes.IDSERIE,serie.getIdSerie());
            cv.put(Constantes.RESTRICAO,serie.getRestricao());
            cv.put(Constantes.TEMPORADA,serie.getTemporada());
            cv.put(Constantes.URLVIDEO,serie.getUrlVideo());
            cv.put(Constantes.VIEWS,serie.getViews());



            String [] args = {serie.getId().toString()};

            escrever.update(Constantes.TABELASERIE,cv,"idSerie = ?",args);

            System.out.println("Serie Atualizada");


            return   true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean atualizarViewsFilme(Filme filme) {
        int idtester = 0;

        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,filme.getId());
            cv.put(Constantes.VIEWS,filme.getViews());

            String [] args = {filme.getId().toString()};
            escrever.update(Constantes.TABELAFILME,cv,"id = ?",args);
            System.out.println("Filme VIEWS ATUALIZADOS");
            idtester++;


            return   true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean atualizar(Filme filme) {
        try{
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,filme.getId());
            cv.put(Constantes.TITULO,filme.getTitulo());
            cv.put(Constantes.DESCRICAO,filme.getDescricao());
            cv.put(Constantes.CATEGORIA,filme.getCategoria());
            cv.put(Constantes.CAPA,filme.getCapa());
            cv.put(Constantes.ANOlANCAMENTO,filme.getAnoDeLancamento());
            cv.put(Constantes.AVALIACAO,filme.getAvaliacao());
            cv.put(Constantes.DATAUPLOAD,filme.getDataUpload());
            cv.put(Constantes.DIRECAO,filme.getDirecao());
            cv.put(Constantes.DURACAO,filme.getDuracao());
            cv.put(Constantes.ELENCO,filme.getElenco());
            cv.put(Constantes.IDSERIE,filme.getIdSerie());
            cv.put(Constantes.RESTRICAO,filme.getRestricao());
            cv.put(Constantes.TEMPORADA,filme.getTemporada());
            cv.put(Constantes.URLVIDEO,filme.getUrlVideo());
            cv.put(Constantes.VIEWS,filme.getViews());

            String [] args = {filme.getId().toString()};

            escrever.update(Constantes.TABELAFILME,cv,"idFilme = ?",args);

            System.out.println("Filme atualizado");


            return   true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean avaliacao() {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.ID,1);
            cv.put(Constantes.AVALIADO,1);
            escrever.insert(Constantes.TABELAAVALIACAO,null,cv);
            System.out.println("Avaliaxao salvo com sucesso");
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public Boolean avaliacaoUpdate( ) {
        try{

            String [] args = {"1"};

            escrever.delete(Constantes.TABELAAVALIACAO,"id = ?",args);

            System.out.println("Avaliacao atualizado");


            return   true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public Boolean salvarVersaoApp(int versao) {
        try {
            ContentValues cv = new ContentValues();
            cv.put(Constantes.VERSAOAPP,versao);
            escrever.insert(Constantes.TABELAATUALIZACAO,null,cv);
            System.out.println("Atualizacao salvo com sucesso");
            return  true;
        }catch (Exception e){
            return  false;
        }
    }

    @Override
    public int getVersaoApp() {
        int versao = 0;
        String sql = "SELECT * FROM "+Constantes.TABELAATUALIZACAO+"  order by versaoApp Desc ;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
          while (cursor.moveToFirst()){
              versao = cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.VERSAOAPP));
          }
        }
        return versao;
    }

    @Override
    public Boolean verificarAvaliacao() {
        String sql = "SELECT * FROM "+Constantes.TABELAAVALIACAO+" where AVALIADO = 1 limit 1;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean deletar(Filme filme) {
        try{
            String [] args = {filme.getId().toString()};
            escrever.delete(Constantes.TABELAFILME,"id=?",args);
            System.out.println("filme deletado");

            return true;
        }catch (Exception e){
            e.printStackTrace();

            return  false;

        }

    }

    @Override
    public Boolean verificarFilme(Filme filme) {
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE id = "+filme.getId()+" OR titulo = "+filme.getTitulo()+";";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            return  true;
        }
        return false;
    }

    @Override
    public int totalDeTemporada(Long idSerieTemp) {
        int total = 0;
        String sql = "SELECT count(temporada) as temporada from  tbFilmes WHERE idSerie = "+idSerieTemp+" ;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.moveToNext()){
          //  String temporada = "";
            int temporada = 0;
            temporada = cursor.getInt(cursor.getColumnIndexOrThrow("temporada"));
//            if(temporada != null && temporada != " " && temporada!= ""){
//
//         //  total = Integer.parseInt(temporada);
//            }
           total = temporada;
        }
        return total;
    }

    @Override
    public List<Filme> listarFilmePorCategoria(String categoriaFilme) {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE categoria like '%"+categoriaFilme+"%' and idSerie =0 OR idSerie = null order by id desc ;";
        Cursor cursor = ler.rawQuery(sql,null);

        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));

        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public List<Filme> listarEpisodios(Long idTempo, int tempo) {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE idSerie = "+idTempo+" and temporada = '"+tempo+"';";
        Cursor cursor = ler.rawQuery(sql,null);

        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public List<Serie> listarSeriePorCategoria(String categoria) {
        return null;
    }

    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "select  nome  from tbcategorias group by nome";
        try{
            Cursor cursor = ler.rawQuery(sql,null);

            while (cursor.moveToNext()){
                //String nome = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
                categorias.add(new Categoria((cursor.getString(cursor.getColumnIndexOrThrow(Constantes.NOME)))));
            }

            return categorias;
        }catch (Exception e){
            return categorias;
        }
    }

    @Override
    public Boolean verificarSerie(Serie serie) {
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE id = "+serie.getId()+" OR titulo = "+serie.getTitulo()+";";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            return  true;
        }
        return false;
    }

    @Override
    public Boolean verificarFavorito(Long id, String tipo) {
        String tipoObjeto = "com.apkdoandroid.filmix.model.Filme";
        String sql = "";

        if(tipo.equalsIgnoreCase(tipoObjeto)){

         sql = "SELECT * FROM "+Constantes.TABELAFAVORITOS+" WHERE idFilme  = "+id+";";
        }else{
         sql = "SELECT * FROM "+Constantes.TABELAFAVORITOS+" WHERE idSerie  = "+id+";";

        }

        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            return  true;
        }
        return false;
    }

    @Override
    public List<Filme> listarFilme() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" where idSerie =0 OR idSerie = null order by id desc; ";
        Cursor cursor = ler.rawQuery(sql,null);
        Filme filme  = new Filme();
        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public int getUltimIDFilme() {
        int ultimoId = 0;
        String sql = "select  * from tbfilmes ORDER BY id DESC limit 1 ;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ultimoId = cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.ID));

            }
        }
        return ultimoId;
    }
    @Override
    public int getUltimIDSerie() {
        int ultimoId = 0;
        String sql = "select  * from tbSeries ORDER BY id DESC limit 1 ;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                ultimoId = cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.ID));

            }
        }
        return ultimoId;
    }

    @Override
    public int getUltimICategoria() {
        int ultimoId = 0;
        String sql = "select  * from tbCategorias ORDER BY id DESC limit 1 ;";
        Cursor cursor = ler.rawQuery(sql,null);
        if(cursor.getCount() > 0){
            while (cursor.moveToNext()){
                ultimoId = cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.ID));

            }
        }
        return ultimoId;
    }

    @Override
    public List<Filme> listarFilmeTop10() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" ORDER BY views  DESC LIMIT 10;";
        Cursor cursor = ler.rawQuery(sql,null);
        Filme filme  = new Filme();
        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public List<Filme> listarLancamentos() {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" where idSerie =0 OR idSerie = null order by id desc;";
        Cursor cursor = ler.rawQuery(sql,null);
        Filme filme  = new Filme();
        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public List<Filme> pesquisa(String pesquisa) {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE titulo LIKE '%"+pesquisa+"%'  and  idSerie =0 OR idSerie = null limit 20;";
        Cursor cursor = ler.rawQuery(sql,null);
        Filme filme  = new Filme();
        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


        }
        System.out.println("Listano filmes");
        return filmes;
    }

    @Override
    public List<Serie> pesquisaSerie(String pesquisa) {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELASERIE+" WHERE titulo LIKE '%"+pesquisa+"%' limit 20;";
        Cursor cursor = ler.rawQuery(sql,null);
        Serie serie  = new Serie();
        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
//            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            series.add(new Serie(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,dataUpload,temporada));



        }
        System.out.println("Listando serie por id");
        return series;
    }

    @Override
    public List<Filme> listarFilme(Long idd) {
        List<Filme> filmes = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELAFILME+" WHERE ID = "+idd+" and idSerie =0 OR idSerie = null;";
        Cursor cursor = ler.rawQuery(sql,null);
        Filme filme  = new Filme();
        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
            String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
            String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
            String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
            String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
            String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
            String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
            String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
            filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));



        }
        System.out.println("Listando filme por id");
        return filmes;
    }

    @Override
    public List<Serie> listarSerie(int idd) {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELASERIE+" WHERE ID = "+idd+";";
        Cursor cursor = ler.rawQuery(sql,null);
        Serie serie  = new Serie();
        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
//            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            series.add(new Serie(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,dataUpload,temporada));



        }
        System.out.println("Listando serie por id");
        return series;
    }

    @Override
    public List<Serie> listarSerie() {
        List<Serie> series = new ArrayList<>();
        String sql = "SELECT * FROM "+Constantes.TABELASERIE+";";
        Cursor cursor = ler.rawQuery(sql,null);
        Serie serie  = new Serie();
        while (cursor.moveToNext()){
            Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
            String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
            String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
            String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
            String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
            String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
            String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
            String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
            String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
            String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
            String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
//            int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
            String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
            String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
            String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
            String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
            series.add(new Serie(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,dataUpload,temporada));



        }
        System.out.println("Listando serie");
        return series;
    }

    @Override
    public List<Object> listarFavoritos() {
        List<Filme> filmes = new ArrayList<>();
        List<Serie> series = new ArrayList<>();


        try{
            String sql = "SELECT * FROM "+Constantes.TABELAFAVORITOS+";";
            Cursor cursor = ler.rawQuery(sql,null);
            while (cursor.moveToNext()){
                Long idfilme =(cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.IDFILME)));
                Long idSerie =(cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
                filmes.add(new Filme(idfilme));
                series.add(new Serie(idSerie));
            }

            System.out.println("Listando favoritos");
            return Arrays.asList(filmes,series);



        }catch (Exception e){

        }

        return null;
    }

    @Override
    public List<Filme> listarFavoritosFilme() {
        List<Filme> filmes = new ArrayList<>();

        try{
            String sql = "select * from tbFilmes where id  in (select idFilme  from tbFavoritos);";
            Cursor cursor = ler.rawQuery(sql,null);
            while (cursor.moveToNext()){
                Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
                String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
                String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
                String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
                String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
                String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
                String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
                String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
                String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
                String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
                String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
                int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
                String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
                String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
                String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
                String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
                String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
                String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
                String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
                String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
                String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
                String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
                String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
                String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
                filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


            }

            System.out.println("Listando id favoritos");


        }catch (Exception e){

        }

        return filmes;
    }

    @Override
    public List<Filme> listarAdiconadoRecentimente() {
        List<Filme> filmes = new ArrayList<>();

        try{
            String sql = "select * from tbFilmes where idSerie =0 OR idSerie = null ORDER by dataupload DESC;";
            Cursor cursor = ler.rawQuery(sql,null);
            while (cursor.moveToNext()){
                Long id = cursor.getLong(cursor.getColumnIndexOrThrow(Constantes.ID));
                String tutulo = (cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TITULO)));
                String descricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DESCRICAO)));
                String duracao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DURACAO)));
                String direcao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DIRECAO)));
                String dataUpload =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.DATAUPLOAD)));
                String anoLancamento =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ANOlANCAMENTO)));
                String avaliacao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.AVALIACAO)));
                String capa =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CAPA)));
                String categoria =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.CATEGORIA)));
                String elenco =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.ELENCO)));
                int idSerie =(cursor.getInt(cursor.getColumnIndexOrThrow(Constantes.IDSERIE)));
                String restricao =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.RESTRICAO)));
                String temporada =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.TEMPORADA)));
                String views =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.VIEWS)));
                String urlVideo =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO)));
                String urlVideo2 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO2)));
                String urlVideo3 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO3)));
                String urlVideo4 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO4)));
                String urlVideo5 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO5)));
                String urlVideo6 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO6)));
                String urlVideo7 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO7)));
                String urlVideo8 =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.URLVIDEO8)));
                String firebase =(cursor.getString(cursor.getColumnIndexOrThrow(Constantes.FIREBASE)));
                filmes.add(new Filme(id,tutulo,capa,descricao,categoria,avaliacao,restricao,views,anoLancamento,duracao,elenco,direcao,urlVideo,urlVideo2,urlVideo3,urlVideo4,urlVideo5,urlVideo6,urlVideo7,urlVideo8,firebase,dataUpload,idSerie,temporada));


            }

            System.out.println("Listando adicionado recentimente");


        }catch (Exception e){

        }

        return filmes;
    }

}

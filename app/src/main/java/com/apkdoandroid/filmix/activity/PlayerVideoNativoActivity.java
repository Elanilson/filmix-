package com.apkdoandroid.filmix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.helpers.VideosPreferencias;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskViews;


public class PlayerVideoNativoActivity extends AppCompatActivity {
    private VideoView videoView;
    private Filme filme = new Filme();
    Dialog dialogAva;
    private ImageView play;
    String urlx = "";
    private VideosPreferencias preferencias;
    int progresso = 0;
    int contador=0;
    Boolean completo = false;
    Boolean trocado = false;
    Boolean video2 = false,video3 = false,video4 = false,video5 = false,video6 = false,video7 = false,video8 = false;
    Boolean video2Valido = true,video3Valido = true,video4Valido = true,video5Valido = true,video6Valido = true,video7Valido = true,video8Valido = true;
    private ProgressBar progressBar;
    private TextView textoCarregando;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_video_nativo);
        recuperandoFilme();

        dialogAva = new Dialog(this);
        //esconder actionbar
        // getSupportActionBar().hide();
        //esconder statusBar e barra de navegacao
        View decorView = getWindow().getDecorView();
        int uipcoes = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
        decorView.setSystemUiVisibility(uipcoes);

        urlx = "https://firebasestorage.googleapis.com/v0/b/bloco-de-notas-black.appspot.com/o/Tratamento%20de%20Realeza%202022%20WEB-DL%201080p%20DUAL%205.1.mp4?alt=media&token=86a2742e-9912-446d-bb29-b8b9d6f97d5c";

        videoView = findViewById(R.id.videoView2);
        progressBar = findViewById(R.id.progressBar2CarregandoVideo);
        textoCarregando = findViewById(R.id.textView12CarregandoVideo);
        play = findViewById(R.id.imageView15Play);
        videoView.setMediaController(new MediaController(this));
        //instanciando e enviando no id do filme como chave para guardar o progresso
        preferencias = new VideosPreferencias( getApplicationContext(),filme.getId().toString() );
        getProgresso();
        Uri url = Uri.parse(filme.getUrlVideo());
//        Uri url = Uri.parse(urlx);
       // Uri url = Uri.parse("https://firebasestorage.googleapis.com/v0/b/appclone-4bddf.appspot.com/o/velozes_e_furiosos_9_2021_720p_web_dl_dual_5_1.mp4?alt=media&token=e2c3a8fe-7bb2-4032-9c2f-c3c9b1264f13");

        videoView.setVideoURI(url);
        videoView.setMediaController(new MediaController(this));
        videoView.setDrawingCacheEnabled(true);
        videoView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_AUTO);
        videoView.getDrawingCache();
        videoView.getDrawingCacheQuality();
        videoView.getDrawingCache(true);
        videoView.seekTo(progresso);
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                System.out.println("Completo");
                completo= true;
                progressBar.setVisibility(View.GONE);
                textoCarregando.setVisibility(View.GONE);
                play.setVisibility(View.VISIBLE);

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                videoView.start();
                play.setVisibility(View.GONE);
            }
        });

        MyRunnable runnable = new MyRunnable();
        new Thread( runnable ).start();

        Contador contador1 = new Contador();
        new Thread( contador1 ).start();
    }
    private void getProgresso(){
        //Recuperar progresso
        int valor = preferencias.recuperarProgresso();
        if( valor!= 0 ){
            progresso = valor;
            System.out.println("não está vazio");
        }
        System.out.println("vazioooooo");
    }
    private void aviso() {
        dialogAva.setContentView(R.layout.play);

        dialogAva.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        LinearLayout layout = dialogAva.findViewById(R.id.linearLayoutavaliar);
        ImageView imageView = dialogAva.findViewById(R.id.imageView8Closse);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogAva.dismiss();
            }
        });
        dialogAva.setCancelable(false);
        dialogAva.show();
    }



    class MyRunnable implements Runnable {

        @Override
        public void run() {
            for (int i=0; i <= 999999999; i++ ){
                contador = i;
                //   System.out.println( "contador: " + contador );
                //   System.out.println("Completo: "+completo);


                //  System.out.println("Reproduzindo? "+ videoView.isPlaying());
                //   System.out.println("Posicao do video: "+videoView.getCurrentPosition());
                //   System.out.println("Duração: "+videoView.getDuration());

                if(i==1 && video2 == false && video2Valido== true && !completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            //getProgresso();
                            System.out.println("Entrou");
                         //   Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/appclone-4bddf.appspot.com/o/velozes_e_furiosos_9_2021_720p_web_dl_dual_5_1.mp4?alt=media&token=e2c3a8fe-7bb2-4032-9c2f-c3c9b1264f13");
                            Uri uri = Uri.parse(filme.getUrlVideo2());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 2  ");
                            System.out.println( "contador: " + contador );
                        }
                    });
                    if(completo){
                        video2 = true;
                        // video1Valido =true;
                    }else{
                        video2Valido =false;
                    }
                    System.out.println("completo: "+completo);


                }else if(i==2 && video3 == false && video3Valido== true && video2Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            Uri uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/ifood-d3075.appspot.com/o/video.mp4?alt=media&token=5ef70cb9-e0af-4720-975d-5562c6ac14f7");
                            Uri uri = Uri.parse(filme.getUrlVideo3());
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.setVideoURI(uri);
                            videoView.start();
                            System.out.println("Video 3  ");

                        }
                    });
                    if(completo){
                        video3 = true;
                    }else{
                        video3Valido =false;
                    }

                }else if(i==3 &&video4 == false  && video4Valido == true && video3Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Uri uri = Uri.parse(filme.getUrlVideo4());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 4");
                            trocado = true;

                        }
                    });

                    if(completo){
                        video4 = true;
                    }else{
                        video4Valido =false;
                    }

                }else if(i==4 &&video5 == false  && video5Valido == true && video4Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Uri uri = Uri.parse(filme.getUrlVideo5());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 4");
                            trocado = true;

                        }
                    });

                    if(completo){
                        video5 = true;
                    }else{
                        video5Valido =false;
                    }

                }else if(i==5 &&video6 == false  && video6Valido == true && video5Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Uri uri = Uri.parse(filme.getUrlVideo6());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 4");
                            trocado = true;

                        }
                    });

                    if(completo){
                        video6 = true;
                    }else{
                        video6Valido =false;
                    }

                }else if(i==6 &&video7 == false  && video7Valido == true && video6Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Uri uri = Uri.parse(filme.getUrlVideo7());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 4");
                            trocado = true;

                        }
                    });

                    if(completo){
                        video7 = true;
                    }else{
                        video7Valido =false;
                    }

                }else if(i==7 &&video8 == false  && video8Valido == true && video7Valido== false &&!completo){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Uri uri = Uri.parse(filme.getUrlVideo8());
                            videoView.setVideoURI(uri);
                            getProgresso();
                            videoView.seekTo(progresso);
                            videoView.start();
                            System.out.println("Video 4");
                            trocado = true;

                        }
                    });

                    if(completo){
                        video8 = true;
                    }else{
                        video8Valido =false;
                    }

                }



                if(i ==999999999){
                    i=0;

                }

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private void recuperandoFilme(){
        filme = (Filme) getIntent().getSerializableExtra("filme");
        new CarregarJsonTaskViews(this,filme);
    }
    class Contador implements Runnable {

        @Override
        public void run() {
            for (int i=0; i <= 999999999; i++ ){

                System.out.println("posicao: "+videoView.getCurrentPosition());
                int position = videoView.getCurrentPosition();
                int duracao = videoView.getDuration();
                if(position != 0 && position >= progresso && position <= duracao){
                    preferencias.salvarProgresso( videoView.getCurrentPosition() );
                    System.out.println("Salvando tempo de reprodução");

                }

                if(i ==999999999){
                    i=0;

                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
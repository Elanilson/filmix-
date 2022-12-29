package com.apkdoandroid.filmix.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.HttpAuthHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.tasks.CarregarJsonTaskViews;

public class PlayerActivity extends AppCompatActivity {
    private VideoView videoView;
    private WebView webView;
    private ProgressBar progressBarVideo;
    private Filme filme = new Filme();
    Dialog dialogAva;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        progressBarVideo = findViewById(R.id.progressBarVideo);
        dialogAva = new Dialog(this);
        recuperandoFilme();
        //esconder actionbar
        // getSupportActionBar().hide();
        //esconder statusBar e barra de navegacao
//        View decorView = getWindow().getDecorView();
//        int uipcoes = View.SYSTEM_UI_FLAG_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
//        decorView.setSystemUiVisibility(uipcoes);

        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Defina o conteúdo para aparecer nas barras do sistema para que o
                        // o conteúdo não é redimensionado quando as barras do sistema são ocultadas e exibidas.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Ocultar a barra de navegação e a barra de status
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);


        webView = findViewById(R.id.webViewPlayer);

        Uri url = Uri.parse(filme.getUrlVideo());

        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setDomStorageEnabled(true);
        // webView.getSettings().setBuiltInZoomControls(true);
        webView.setWebViewClient(new MyWebViewClient());
       // webView.setWebChromeClient(new ChromeClient());




        String userAgent = webView.getSettings().getUserAgentString();

        try {
            String androidString = webView.getSettings().getUserAgentString().
                    substring(userAgent.indexOf("("),userAgent.indexOf(")")+ 1);

            userAgent = webView.getSettings().getUserAgentString().replace(androidString,"X11; Linux x86_64");

        }catch (Exception e){
            e.printStackTrace();
        }

        webView.getSettings().setUserAgentString(userAgent);
        webView.reload();


        webView.loadUrl(filme.getUrlVideo());
        //aviso();
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

    private void recuperandoFilme(){
        filme = (Filme) getIntent().getSerializableExtra("filme");
        new CarregarJsonTaskViews(this,filme);
    }
    //add this class in main activity
    class MyWebViewClient extends WebViewClient {
        @Override
        public void onReceivedHttpAuthRequest(WebView view,
                                              HttpAuthHandler handler, String host, String realm) {

            handler.proceed("Elanilson@t.5tb.in", "753159654");

        }
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            Toast.makeText(PlayerActivity.this, "Carregando...", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //Page load finished
            super.onPageFinished(view, url);
            progressBarVideo.setVisibility(View.GONE);
            Toast.makeText(PlayerActivity.this, "Aguarde estamos preparando o video..", Toast.LENGTH_LONG).show();
        }

    }



    private String site(){
        String  html = "<!DOCTYPE html>\n" +
                "<html lang=\"pt-br\">\n" +
                "\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
                "    <title>JLPlayer - Reprodutor de vídeos</title>\n" +
                "\n" +
                "    <!-- Link style jlplayer.css -->\n" +
                "    <link rel=\"stylesheet\" href=\"https://apkdoandroidonline.com/jlplayer.css\">\n" +
                "\n" +
                "    <style>\n" +
                "        *, *::before, *::after{margin: 0; padding: 0;}\n" +
                "        .video-container{width: 100%;}\n" +
                "    </style>\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body>\n" +
                "\n" +
                "    <form class=\"video-container\" action=\"\" method=\"GET\">\n" +
                "\n" +
                "        <!-- Chamar elemento vídeo com class jlplayer-video -->\n" +
                "        <video preload=\"none\" class=\"jlplayer-video\">\n" +
                "            <source src=\""+filme.getUrlVideo()+"\" type=\"video/mp4\">\n" +
                "            <!-- <track kind=\"captions\" src=\"https://apkdoandroidonline.com/legenda.vtt\" default> -->\n" +
                "        </video>\n" +
                "\n" +
                "    </form>\n" +
                "\n" +
                "    <!-- Inclusão jlplayer -->\n" +
                "    <script src=\"https://apkdoandroidonline.com/jlplayer.js\" async></script>\n" +
                "\n" +
                "</body>\n" +
                "\n" +
                "</html>";

        return  html;
    }

//    private String site(){
//        String  html = "<!DOCTYPE html>\n" +
//                "<html lang=\"pt-br\">\n" +
//                "\n" +
//                "<head>\n" +
//                "    <meta charset=\"UTF-8\">\n" +
//                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
//                "    <meta http-equiv=\"X-UA-Compatible\" content=\"ie=edge\">\n" +
//                "    <title>JLPlayer - Reprodutor de vídeos</title>\n" +
//                "\n" +
//                "    <!-- Link style jlplayer.css -->\n" +
//                "    <link rel=\"stylesheet\" href=\"https://apkdoandroidonline.com/jlplayer.css\">\n" +
//                "\n" +
//                "    <style>\n" +
//                "        *, *::before, *::after{margin: 0; padding: 0;}\n" +
//                "        .video-container{width: 100%;}\n" +
//                "    </style>\n" +
//                "\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "\n" +
//                "    <form class=\"video-container\" action=\"\" method=\"GET\">\n" +
//                "\n" +
//                "        <!-- Chamar elemento vídeo com class jlplayer-video -->\n" +
//                "        <video preload=\"none\" class=\"jlplayer-video\">\n" +
//                "            <source src=\"https://apkdoandroidonline.com/home.mp4\" type=\"video/mp4\">\n" +
//                "            <!-- <track kind=\"captions\" src=\"https://apkdoandroidonline.com/legenda.vtt\" default> -->\n" +
//                "        </video>\n" +
//                "\n" +
//                "    </form>\n" +
//                "\n" +
//                "    <!-- Inclusão jlplayer -->\n" +
//                "    <script src=\"https://apkdoandroidonline.com/jlplayer.js\" async></script>\n" +
//                "\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";
//
//        return  html;
//    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        webView.saveState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        webView.restoreState(savedInstanceState);
    }


}


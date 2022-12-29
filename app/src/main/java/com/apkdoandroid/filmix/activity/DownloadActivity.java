package com.apkdoandroid.filmix.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DownloadManager;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.apkdoandroid.filmix.R;

import com.apkdoandroid.filmix.model.Filme;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class DownloadActivity extends AppCompatActivity {
    private AdView mAdView;
    private TextView textoDownload;
    private ProgressBar progressBar;
    private Button btnDownload;
    private Filme filme = new Filme();
    private LinearLayout layout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        recuperandoDAdos();
        pedirPermisos();




        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        textoDownload = findViewById(R.id.textView11GerandoLink);
        progressBar = findViewById(R.id.progressBar2Download);
        btnDownload = findViewById(R.id.buttonLinkDownload);
        layout = findViewById(R.id.layoutDownloadLinear);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textoDownload.setText("Link liberado!!");
                textoDownload.setTextColor(getResources().getColor(R.color.verdeClaro));
                progressBar.setVisibility(View.GONE);
                btnDownload.setVisibility(View.VISIBLE);

            }
        }, 10000);


        mAdView = findViewById(R.id.adViewDownload1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        mAdView = findViewById(R.id.adViewDownload12);
        AdRequest adRequest2 = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest2);

        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //DownloadManager.Request created with url.
                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(filme.getUrlVideo()));
                //cookie
                String cookie=CookieManager.getInstance().getCookie(filme.getUrlVideo());
                //Add cookie and User-Agent to request
                request.addRequestHeader("Cookie",cookie);
                request.addRequestHeader("User-Agent",null);
                //file scanned by MediaScannar
                request.allowScanningByMediaScanner();
                //Download is visible and its progress, after completion too.
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                //DownloadManager created
                DownloadManager downloadManager=(DownloadManager)getSystemService(DOWNLOAD_SERVICE);
                //Saving files in Download folder
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, filme.getTitulo());
                //download enqued
                downloadManager.enqueue(request);

//                Snackbar.make(layout, "Baixando....", Snackbar.LENGTH_LONG)
//                        .setAction("CLOSE", new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//
//                            }
//                        })
//                        .setBackgroundTint(getResources().getColor(R.color.vermelho))
//                        .setActionTextColor(getResources().getColor(android.R.color.white ))
//                        .show();
                Toast.makeText(DownloadActivity.this, "Iniciando download!", Toast.LENGTH_SHORT).show();
                finish();


            }
        });


    }
    private void recuperandoDAdos(){
        filme = (Filme) getIntent().getSerializableExtra("filme");
    }

    public void pedirPermisos() {
        // PERMISOS PARA ANDROID 6 O SUPERIOR
        if (ContextCompat.checkSelfPermission(DownloadActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    DownloadActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    0
            );

        }
    }
}




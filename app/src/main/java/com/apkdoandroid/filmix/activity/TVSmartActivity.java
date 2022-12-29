package com.apkdoandroid.filmix.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apkdoandroid.filmix.R;
import com.apkdoandroid.filmix.model.Filme;
import com.apkdoandroid.filmix.model.Serie;

public class TVSmartActivity extends AppCompatActivity {
    private Filme filme = new Filme();
    private String link = "" ;
    private TextView codigo, linkTEsxt;
    private String paraSerCopiadoCodigo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvsmart);
        recuperandoDados();
        codigo = findViewById(R.id.textView8Codigo);
        linkTEsxt = findViewById(R.id.textView9Link);
        paraSerCopiadoCodigo = "#Filmix"+filme.getId();
        codigo.setText(paraSerCopiadoCodigo);

        link = "https://apkdoandroidonline.com/play/";
        linkTEsxt.setText(link);



        codigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copiar(paraSerCopiadoCodigo);
            }
        });
        linkTEsxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                copiar(link);
            }
        });
    }

    private void recuperandoDados() {
        filme = (Filme) getIntent().getSerializableExtra("filme");

    }
    public String copiar(String texto){
        //ClipData copia = ClipData.newPlainText(texto, autor);
        //para cópia:
        ClipboardManager clipboar = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("",texto);
        clipboar.setPrimaryClip(clip);

        //E cole:
        String dados = "";
        // Se contiver dados, decida se você pode manipular os dados.
        if(!(clipboar.hasPrimaryClip())){

        }else if(!(clipboar.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN))){
            // visto que a área de transferência contém dados, mas não é um texto simples
        }else{

            // já que a área de transferência contém texto simples.
            ClipData.Item item=  clipboar.getPrimaryClip().getItemAt(0);


            // Obtém a área de transferência como texto.
            dados = item.getText().toString();
            Toast.makeText(TVSmartActivity.this, "Copiado", Toast.LENGTH_SHORT).show();

        }
        return dados;
    }
}
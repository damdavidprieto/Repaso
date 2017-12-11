package com.example.usuario.entrega;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuPrincipal extends AppCompatActivity {

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

    }

    public void coloresEvento(View view){
        mp=MediaPlayer.create(this, R.raw.scifidoor);
        mp.start();
        Intent mainIntent = new Intent().setClass(MenuPrincipal.this, ColoresAct.class);
        startActivity(mainIntent);
        overridePendingTransition(R.anim.zoom_back_in, R.anim.zoom_back_out);
    }

    public void notasEvento(View view){
        mp=MediaPlayer.create(this, R.raw.punch);
        mp.start();
        Intent mainIntent = new Intent().setClass(MenuPrincipal.this, ListViewActivity.class);
        startActivity(mainIntent);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}

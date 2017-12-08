package com.example.usuario.entrega;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class PantallaCarga extends AppCompatActivity {

    private int tiempo,progreso;
    private ProgressBar barraProgreso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tiempo=3000;
        progreso=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_carga);

        barraProgreso=(ProgressBar)findViewById(R.id.progressBar2);
        new Thread(carga).start();
    }

    private Runnable carga = new Runnable() {
        @Override
        public void run() {
            while (progreso < 100) {
                try {

                    miHandle.sendMessage(miHandle.obtainMessage());
                    Thread.sleep(50);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }

            Intent mainIntent = new Intent().setClass(PantallaCarga.this, MenuPrincipal.class);
            startActivity(mainIntent);
            finish();
        }

        Handler miHandle = new Handler(){

            public void handleMessage(Message msg) {
                progreso++;
                barraProgreso.setProgress(progreso);
                TextView tv=findViewById(R.id.textProgreso);
                String pro=Integer.toString(progreso);
                tv.setText(pro+"%");
            }
        };
    };

}

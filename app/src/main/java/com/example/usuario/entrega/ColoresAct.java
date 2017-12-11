package com.example.usuario.entrega;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class ColoresAct extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, View.OnClickListener{

    private SeekBar sRojo,sVerde,sAzul,sAlfa;
    private TextView tvRojo, tvVerde, tvAzul, tvAlfa;
    private Button bGuardar;
    private SurfaceView sfGuardado,sfActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colores);

        sRojo=(SeekBar)findViewById(R.id.sbRojo);
        sVerde=(SeekBar)findViewById(R.id.sbVerde);
        sAzul=(SeekBar)findViewById(R.id.sbAzul);
        sAlfa=(SeekBar)findViewById(R.id.sbAlfa);

        tvRojo=(TextView)findViewById(R.id.tvRojo);
        tvVerde=(TextView)findViewById(R.id.tvVerde);
        tvAzul=(TextView)findViewById(R.id.tvAzul);
        tvAlfa=(TextView)findViewById(R.id.tvAlfa);

        bGuardar=(Button)findViewById(R.id.bGuardar);

        sfGuardado=(SurfaceView) findViewById(R.id.sfvGuardado);
        sfActual=(SurfaceView) findViewById(R.id.sfvActual);

        sRojo.setOnSeekBarChangeListener(this);
        sVerde.setOnSeekBarChangeListener(this);
        sAzul.setOnSeekBarChangeListener(this);
        sAlfa.setOnSeekBarChangeListener(this);
        bGuardar.setOnClickListener(this);

        SharedPreferences prefe=getSharedPreferences("datos", Context.MODE_PRIVATE);
        tvRojo.setText(prefe.getString("rojo", "0"));
        tvVerde.setText(prefe.getString("verde", "0"));
        tvAzul.setText(prefe.getString("azul", "0"));
        tvAlfa.setText(prefe.getString("opaco", "0"));

        sRojo.setProgress(Integer.parseInt(tvRojo.getText().toString()));
        sVerde.setProgress(Integer.parseInt(tvVerde.getText().toString()));
        sAzul.setProgress(Integer.parseInt(tvAzul.getText().toString()));
        sAlfa.setProgress(Integer.parseInt(tvAlfa.getText().toString()));
        int opacidad=Integer.parseInt(tvAlfa.getText().toString());
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        sfGuardado.setBackgroundColor(Color.argb(opacidad,rojo, verde, azul));
    }

    @Override
    public void onClick(View view) {
        SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("rojo", tvRojo.getText().toString());
        editor.putString("verde", tvVerde.getText().toString());
        editor.putString("azul", tvAzul.getText().toString());
        editor.commit();
        Toast.makeText(this, "Se ha guardado el color elegido", Toast.LENGTH_LONG).show();
        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        sfGuardado.setBackgroundColor(Color.rgb(rojo, verde, azul));
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        int id=seekBar.getId();
        if(id==sRojo.getId())
            tvRojo.setText(new Integer(i).toString());
        if(id==sVerde.getId())
            tvVerde.setText(new Integer(i).toString());
        if(id==sAzul.getId())
            tvAzul.setText(new Integer(i).toString());
        if(id==sAlfa.getId())
            tvAlfa.setText(new Integer(i).toString());

        int rojo=Integer.parseInt(tvRojo.getText().toString());
        int verde=Integer.parseInt(tvVerde.getText().toString());
        int azul=Integer.parseInt(tvAzul.getText().toString());
        int opacidad=Integer.parseInt(tvAlfa.getText().toString());

        sfActual.setBackgroundColor(Color.argb(opacidad,rojo,verde,azul));
    }


    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}

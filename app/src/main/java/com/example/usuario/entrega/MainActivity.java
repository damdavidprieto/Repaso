package com.example.usuario.entrega;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private EditText et1,et2,et3;
    private Nota nota;
    private Spinner spinner;
    private MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
        et2=(EditText)findViewById(R.id.editTextAsunto);
        et3=(EditText)findViewById(R.id.editTextCuerpo);
        spinner = (Spinner) findViewById(R.id.spinner);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.combo, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinner.setSelection(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(getIntent().getExtras().getSerializable("objeto")!=null) {
            nota = (Nota) getIntent().getExtras().getSerializable("objeto");

            et1.setText(nota.getTitulo());
            et2.setText(nota.getAsunto());
            et3.setText(nota.getCuerpo());
        }else{
            nota=null;
        }
    }

    public void guardar(View v){
        String nombreArchivo=et1.getText().toString();
        nombreArchivo=nombreArchivo.replace('/','-');
        try{
            OutputStreamWriter archivo=new OutputStreamWriter(openFileOutput(nombreArchivo, Activity.MODE_PRIVATE));
            int spin=spinner.getSelectedItemPosition();
            String asuntoCuerpo=et2.getText().toString()+"\n"+spin+"\n"+et3.getText().toString();
            archivo.write(asuntoCuerpo);
            archivo.flush();
            archivo.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Imposible abrir el archivo",Toast.LENGTH_LONG).show();
        }
        mp=MediaPlayer.create(this, R.raw.scifidoor);
        mp.start();
        Intent edicion = new Intent(getApplicationContext(), ListViewActivity.class);
        finish();
        startActivity(edicion);
    }

    @Override
    public void onBackPressed() {
        mp=MediaPlayer.create(this, R.raw.scifidoor);
        mp.start();
        Intent edicion = new Intent(getApplicationContext(), ListViewActivity.class);
        finish();
        startActivity(edicion);
    }
}

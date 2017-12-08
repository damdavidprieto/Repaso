package com.example.usuario.entrega;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private ListView list;
    private String [] archivos;
    private List<Nota> listaNotas;
    private AdaptadorNotas adaptador;
    private Nota notaSeleccionada;
    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);


        archivos=fileList();
        list= (ListView) findViewById(R.id.lista1);
        listaNotas=new ArrayList<Nota>();

        registerForContextMenu(list);

        /*
        ArrayAdapter<String> adaptador;
        adaptador=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,archivos);
        list.setAdapter(adaptador);
        */
        cargarDatos();

    }

    private void cargarDatos(){
        for(String s :archivos){
            String tit=s;
            String asunto="";
            String todo="";
            String prioridad="";
            Context c=getBaseContext();
            InputStreamReader cuerpo;
            try {
                cuerpo = new InputStreamReader(c.openFileInput(s));
                BufferedReader br = new BufferedReader(cuerpo);
                asunto = br.readLine();
                prioridad=br.readLine();
                String linea=br.readLine();
                todo = "";

                while (linea != null) {
                    todo += linea + "\n";
                    linea = br.readLine();
                }

                br.close();
                cuerpo.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(s+" / "+asunto+" / "+todo);
            System.out.println("Título: "+s);
            System.out.println("Prioridad: "+prioridad);
            System.out.println("Asunto: "+asunto);
            System.out.println("Cuerpo: "+todo);

            int nu=R.drawable.altapri;
            if(prioridad.contains("0")){
                nu=R.drawable.altapri;
            }else if(prioridad.contains("1")){
                nu=R.drawable.mediapri;
            }else if(prioridad.contains("2")){
                nu=R.drawable.bajapri;
            }

            Nota n=new Nota(s,asunto,todo,nu);
            listaNotas.add(n);
        }

        adaptador =new AdaptadorNotas(getApplicationContext(),listaNotas);
        list.setAdapter(adaptador);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.setSelected(true);
                notaSeleccionada =(Nota)adapterView.getItemAtPosition(i);
                Toast.makeText(getBaseContext(),notaSeleccionada.getTitulo(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void abrir(View view){
        if(notaSeleccionada!=null){
            mp=MediaPlayer.create(this, R.raw.punch);
            mp.start();
            Intent edicion = new Intent(getApplicationContext(), MainActivity.class);
            edicion.putExtra("objeto", notaSeleccionada);
            finish();
            startActivity(edicion);
            overridePendingTransition(R.anim.right_in, R.anim.right_out);
        }
    }

    public void borrar(View view){
        if(notaSeleccionada!=null){
            mp=MediaPlayer.create(this, R.raw.scifidoor);
            mp.start();
            for(File f:getFilesDir().listFiles()){
                if(f.getName().contains(notaSeleccionada.getTitulo())){
                    f.delete();
                    adaptador.listaNotas.remove(notaSeleccionada);
                    adaptador.notifyDataSetChanged();
                }
            }
            notaSeleccionada=null;
        }
    }

    public void nuevo(View view){
        notaSeleccionada=null;
        mp=MediaPlayer.create(this, R.raw.punch);
        mp.start();
        Intent edicion = new Intent(getApplicationContext(), MainActivity.class);
        edicion.putExtra("objeto", notaSeleccionada);
        finish();
        startActivity(edicion);
        overridePendingTransition(R.anim.oblicuo_in, R.anim.oblicuo_out);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(this);
        inflater.inflate(R.menu.menucontextual, menu);
        v.setSelected(true);
        AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
        notaSeleccionada = (Nota) list.getItemAtPosition(acmi.position);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuabrir:
                abrir(null);
                return true;
            case R.id.mnuborrar:
                borrar(null);
                return true;
        }
        return false;
    }
}

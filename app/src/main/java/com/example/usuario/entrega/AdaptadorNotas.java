package com.example.usuario.entrega;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Usuario on 07/12/2017.
 */

public class AdaptadorNotas extends BaseAdapter {

    Context context;
    List<Nota> listaNotas;

    public AdaptadorNotas(Context context, List<Nota> listaNotas) {
        this.context = context;
        this.listaNotas = listaNotas;
    }

    @Override
    public int getCount() {
        return listaNotas.size();
    }

    @Override
    public Object getItem(int i) {
        return listaNotas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View vista=view;
        LayoutInflater lf = LayoutInflater.from(context);
        vista=lf.inflate(R.layout.list_notas,null);

        TextView tvTit = vista.findViewById(R.id.tvTitulo);
        TextView tvAsunto=vista.findViewById(R.id.tvAsunto);
        ImageView ivPri=vista.findViewById(R.id.ivImagen);

        String t=context.getResources().getString(R.string.titulo);
        String a=context.getResources().getString(R.string.asunto);

        tvTit.setText(t+": "+listaNotas.get(i).getTitulo().toString());
        tvAsunto.setText(a+": "+listaNotas.get(i).getAsunto().toString());
        ivPri.setImageResource(listaNotas.get(i).getImagen());

        return vista;
    }
}

package com.example.usuario.entrega;

import java.io.Serializable;

/**
 * Created by Usuario on 07/12/2017.
 */

public class Nota implements Serializable {

    private String titulo, asunto,cuerpo;
    private int imagen;

    public Nota(String titulo, String asunto, String cuerpo, int imagen) {
        this.titulo = titulo;
        if(asunto==null){
            this.asunto="";
        }else {
            this.asunto = asunto;
        }
        this.cuerpo = cuerpo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}

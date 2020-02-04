package com.example.inicioreto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Comercial{
    int cod;
    String nom;
    String ape;
    Comercial(int cod, String nom, String ape){
        this.nom=nom;
        this.ape=ape;
        this.cod=cod;
    }

    public int getCodigo(){
        return cod;
    }
    public void setCodigo(int c){
        cod=c;
    }

    public String getNombre(){
        return nom;
    }
    public void setNombre(String n){
        nom=n;
    }
    public String getApellido(){
        return ape;
    }
    public void setApellido(String a){
        ape=a;
    }


}

package com.example.inicioreto;

public class Linea {
    private int Cantidad;
    private float Precio;
    private float Descuento;
    private float PrecioTotal;
    private int CodArticulo;
    Linea(int cant, float pre, float desc, float precitot,int art){
        Cantidad=cant;
        Precio=pre;
        Descuento=desc;
        PrecioTotal=precitot;
        CodArticulo=art;
    }
    public int getCantidad(){
        return Cantidad;
    }
    public float getPrecio(){
        return Precio;
    }
    public float getDescuento(){
        return Descuento;
    }
    public float getPrecioTotal(){
        return PrecioTotal;
    }
    public int getCodArticulo(){
        return CodArticulo;
    }
}

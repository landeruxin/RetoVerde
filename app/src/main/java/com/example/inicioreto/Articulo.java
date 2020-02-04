package com.example.inicioreto;

public class Articulo {
    private String CodArticulo;
    private String Descripcion;
    private String PrecioVenta;
    private String CodCategoria;
    Articulo (String cod,String desc,String pv,String CodCat,String Cant){
        CodArticulo=cod;
        Descripcion=desc;
        PrecioVenta=pv;
        CodCategoria=CodCat;
    }
    public String getDescripcion(){
        return Descripcion;
    }
    public String getCodArticulo(){
        return CodArticulo;

    }

    public String getPrecioVenta(){
        return PrecioVenta;
    }
    public String getCodCategoria(){
        return CodCategoria;
    }

}

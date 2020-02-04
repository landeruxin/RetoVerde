package com.example.inicioreto;

public class Albaran {
    private String NombreAlbaran;
    private String FechaAlbaran;
    private String FechaEnvio;
    private String FechaEntrega;
    private int CodTransportista;
    private int CodCliente;
    private int CodComercial;
    private Linea lineas[];
    Albaran(String nomalb,String fecalb,String fecenv,String fecent,int codt,int codc,int codcom, Linea lin[]){
        NombreAlbaran=nomalb;
        FechaAlbaran=fecalb;
        FechaEnvio=fecenv;
        FechaEntrega=fecent;
        CodTransportista=codt;
        CodCliente=codc;
        CodComercial=codcom;
        lineas=lin;
    }
}

package com.example.inicioreto;

public class Partner {
    private int cod;
    private String nombre;
    private String apellido;
    private String empresa;
    private String direccionE;
    private String direccionF;
    private String poblacion;
    private int telefono;
    private int codComer;


    Partner(String nombre, String apellido, String empresa, String direccionE, String direccionF, String poblacion, int telefono, int codComer){


        this.nombre=nombre;
        this.apellido=apellido;
        this.empresa=empresa;
        this.direccionE=direccionE;
        this.direccionF=direccionF;
        this.poblacion=poblacion;
        this.telefono=telefono;
        this.codComer=codComer;

    }
    Partner(){
    }

    public int getCodigo(){
        return cod;
    }
    public void setCodigo(int c){
        cod=c;
    }
    public int getCodComer(){
        return cod;
    }
    public void setCodComer(int c){
        cod=c;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String n){
        nombre=n;
    }
    public String getApellido(){
        return apellido;
    }
    public void setApellido(String ape){
        apellido=ape;
    }
    public String getEmpresa(){
        return empresa;
    }
    public void setEmpresa(String em){
        empresa=em;
    }
    public String getDireccionE(){
        return direccionE;
    }
    public void setDireccionE(String dirE){
        direccionE=dirE;
    }

    public String getDireccionF(){
        return direccionF;
    }
    public void setDireccionF(String dirF){
        direccionF=dirF;
    }

    public String getPoblacion(){
        return poblacion;
    }
    public void setPoblacion(String pob){
        poblacion=pob;
    }

    public int getTelefono(){
        return telefono;
    }
    public void setTelefono(int tel){
        telefono=tel;
    }


}

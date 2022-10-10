package com.example.trackingforgym.data;

import java.io.Serializable;
import java.util.ArrayList;

public class Rutine implements Serializable {

    private int id;
    private int idPropietario;
    private String color;
    private String nombre;
    public ArrayList<Ejercicio> ejercicios;
    public int numero_usos;

    public Rutine(int id, int idpropietario, String color, String nombre, int num){
        this.id=id;
        this.idPropietario=idpropietario;
        this.color=color;
        this.nombre=nombre;
        this.numero_usos=num;
        this.ejercicios=new ArrayList<Ejercicio>();
    }

    public Rutine(int id, int idpropietario, String color, String nombre){
        this.id=id;
        this.idPropietario=idpropietario;
        this.color=color;
        this.nombre=nombre;
        this.numero_usos=0;
    }
    public Rutine(int i,String color, String nombre ){
        this(i,0, color,nombre,0);
    }
    public Rutine(String color, String nombre ){
        this(0,Session.getUser().getId(), color,nombre,0);
    }
    public Rutine(String color, String nombre, int num){
        this(0,0,color,nombre,num);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean mayorQue(Rutine rutina){
        return this.numero_usos > rutina.numero_usos;
    }

    public String upload(){
        return DataBase.addRutine(this);
    }

    public void setIdDataBase(){
        this.id= DataBase.getRutina(this).id;
    }
}
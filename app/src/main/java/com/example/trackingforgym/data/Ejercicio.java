package com.example.trackingforgym.data;

import java.io.Serializable;

public class Ejercicio implements Serializable {
    int id;
    String nombre;
    String color;
    String parteCuerpo;

    public Ejercicio(String nom, String col, String pc){
        this(-1, nom,col,pc);
    }
    public Ejercicio(int i, String nom, String col, String pc){
        this.id=i;
        this.nombre=nom;
        this.color=col;
        this.parteCuerpo=pc;
    }
    public int getId() {
        return id;
    }

    public void setIdDataBase(){
        this.id= DataBase.getEjercicio(this).id;
    }

    public String upload(){
        return DataBase.addEjercicio(this);
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParteCuerpo() {
        return parteCuerpo;
    }

    public void setParteCuerpo(String parteCuerpo) {
        this.parteCuerpo = parteCuerpo;
    }
}

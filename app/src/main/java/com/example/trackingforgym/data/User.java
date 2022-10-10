package com.example.trackingforgym.data;

import java.util.ArrayList;

public class User {
    int id;
    String correo;
    public ArrayList<Rutine> rutinas;
    public ArrayList<RutineHistoric> entrenamientos;
    public ArrayList<Ejercicio> ejercicios;
    public RutineHistoric nuevoEntrenamiento;
    public ArrayList<LastStat> stats;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public User(){
        this(-1,null);
    }

    public User(int ide){
        id=ide;
        correo=null;
        rutinas=new ArrayList<Rutine>();
        entrenamientos=new ArrayList<RutineHistoric>();
        nuevoEntrenamiento= new RutineHistoric(0,"","",0,0);
        stats = new ArrayList<LastStat>();
    }
    public User(int ide, String email){
        id=ide;
        correo= email;
        rutinas=new ArrayList<Rutine>();
        entrenamientos=new ArrayList<RutineHistoric>();
        stats = new ArrayList<LastStat>();
    }
}

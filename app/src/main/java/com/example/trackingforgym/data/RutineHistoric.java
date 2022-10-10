package com.example.trackingforgym.data;

import java.util.ArrayList;

public class RutineHistoric extends Rutine{
    private Fecha fecha;
    int esfuerzo;
    public int idPadre;
    public ArrayList<Serie> series;

    public RutineHistoric(String color, String nombre, int fecha){
        super(color, nombre);
        this.fecha = new Fecha(fecha);
    }
    public RutineHistoric(int i){
        this(i,"","",0,0);
    }
    public RutineHistoric(int id, String color, String nombre, int fecha, int esfu){
        super(id, color, nombre);
        this.fecha = new Fecha(fecha);
        this.esfuerzo=esfu;
        series = new ArrayList<Serie>();
    }
    public RutineHistoric(String color, String nombre){
        super(color, nombre);
        fecha=new Fecha();
    }

    public Fecha getFecha() {
        return fecha;
    }

    public void setFecha(int fecha) {
        this.fecha = new Fecha(fecha);
    }

    public void crearFecha(){
        fecha=new Fecha();
    }
}

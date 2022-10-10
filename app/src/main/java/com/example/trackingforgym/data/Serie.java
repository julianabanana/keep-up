package com.example.trackingforgym.data;

public class Serie extends Ejercicio{
    public int repeticiones;
    public int peso;
    public int ejercicio;
    public int esfuerzo;

    public Serie(int i, String nom, String col, String pc, int r, int eje){
        super(i,nom,col,pc);
        repeticiones=r;
        ejercicio=eje;
    }
}

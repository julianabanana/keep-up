package com.example.trackingforgym;

import java.io.Serializable;

public class Lista_Rutinas implements Serializable {
    public String colo;
    public String nombre;
    public String citys;
    public String status;

    public Lista_Rutinas(String colo, String nombre, String city, String status) {
        this.colo = colo;
        this.nombre = nombre;
        this.citys = city;
        this.status = status;
    }

    public String getColo() {
        return colo;
    }

    public void setColo(String colo) {
        this.colo = colo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCitys() {
        return citys;
    }

    public void setCity(String city) {
        this.citys = city;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

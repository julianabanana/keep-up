package com.example.trackingforgym.data;
import java.util.Calendar;

public class Fecha {
    public long fecha;
    Calendar cal;

    public Fecha(){
        cal = Calendar.getInstance();
        fecha = cal.getTimeInMillis();
        cal.setTimeInMillis(fecha);
        fecha=fecha/86400000;
    }
    public Fecha(int f){
        cal = Calendar.getInstance();
        fecha = f;
        cal.setTimeInMillis(fecha*86400000);
    }
    public Fecha(long f){
        cal = Calendar.getInstance();
        fecha = f;
        cal.setTimeInMillis(fecha*86400000);
    }

    public int getYear(){
        return cal.get(Calendar.YEAR);
    }

    public int getMonth(){
        return cal.get(Calendar.MONTH);
    }

    public int getDay(){
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    @Override
    public String toString() {
        return getYear()+"/"+getMonth()+"/"+getDay();
    }
}

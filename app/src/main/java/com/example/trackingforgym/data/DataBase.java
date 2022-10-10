package com.example.trackingforgym.data;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class DataBase {
    static String resultado;
    static Gson gson = new Gson();
    public static final String BaseURL="https://tackingforgym.000webhostapp.com";
    static String URL;
    static InputStream response;
    static URLConnection connection;
    static String responseBody;

    public static String makeRequest(String url) {
        response=null;
        connection=null;
        responseBody="funcionando";
        String charset = "UTF-8";
        try {
            connection = new URL(url).openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        connection.setRequestProperty("Accept-Charset", charset);
        try {
            response = connection.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(response)) {
            responseBody = scanner.useDelimiter("\\A").next();
        }
        return responseBody;
    }

    public static String addUser(String nombre, String correo, String clave){
        URL=BaseURL+"/addUser.php?nombre="+nombre+"&clave="+clave+"&correo="+correo;
        return makeRequest(URL);
    }

    public static User getUser(String correo,String clave){
        URL=BaseURL+"/getUser.php?correo="+correo+"&clave="+clave;
        resultado = makeRequest(URL);
        User[] users=gson.fromJson(resultado , User[].class);
        return users[0];
    }

    public static String addEjercicio(Ejercicio j) {
        URL=BaseURL+"/addEjercicio.php?nombre="+j.nombre+"&color="+j.color+"&parteCuerpo="+j.parteCuerpo+"&usuario="+Session.getUser().id;
        return makeRequest(URL);
    }

    public static String addEntrenamiento(RutineHistoric j) {
        URL=BaseURL+"/addEntrenamiento.php?usuario="+Session.getUser().getId()+"&rutina="+j.idPadre+"&fecha="+ j.getFecha().fecha+"&esfuerzo="+0;
        return makeRequest(URL);
    }
    public static String addSerie(int id, Serie j) {
        URL=BaseURL+"/addSerie.php?entrenamiento="+id+"&ejercicio="+j.ejercicio+"&repeticiones="+j.repeticiones;
        return makeRequest(URL);
    }

    public static RutineHistoric getEntrenamiento(RutineHistoric j) {
        URL=BaseURL+"/getEntrenamiento.php?usuario="+Session.getUser().getId()+"&rutina="+j.idPadre+"&fecha="+j.getFecha().fecha;
        String res= makeRequest(URL);
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(res).getAsJsonArray();
        ArrayList<RutineHistoric> d=new ArrayList<RutineHistoric>();
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();

            RutineHistoric a= new RutineHistoric(gsonObj.get("id_entrenamiento").getAsInt());
            d.add(a);
            System.out.println();
        }
        System.out.println(res);
        return d.get(0);
    }


    /*public static String addSerie(int id, Serie j) {
        URL=BaseURL+"/addEntrenamiento.php?usuario="+Session.getUser().getId()+"&rutina="+j.idPadre+"&fecha="+ j.getFecha().fecha+"&esfuerzo="+0;
        return makeRequest(URL);
    }*/


    public static Ejercicio getEjercicio(Ejercicio j){
        URL=BaseURL+"/getEjercicio.php?nombre="+j.nombre+"&color="+j.color+"&parteCuerpo="+j.parteCuerpo;
        String res =makeRequest(URL);
        Ejercicio[] ej=gson.fromJson(res , Ejercicio[].class);
        return ej[0];
    }
    public static Rutine getRutina(Rutine j){
        URL=BaseURL+"/getRutina.php?nombre="+j.getNombre()+"&color="+j.getColor();
        String res =makeRequest(URL);
        Rutine[] ej=gson.fromJson(res , Rutine[].class);
        return ej[0];
    }

    public static String addRutine(Rutine r) {
        String ejs ="";
        for(Ejercicio e : r.ejercicios){
            ejs=ejs+e.id+",";
        }
        ejs=ejs.substring(0,ejs.length()-1);
        URL=BaseURL+"/addRutina.php?nombre="+r.getNombre()+"&color="+r.getColor()+"&ejercicios="+ejs+"&usuario="+Session.getUser().id;
        return makeRequest(URL);
    }

    public static ArrayList<Rutine> getRutinasUser(int id){

        URL=BaseURL+"/getRutinas.php?usuario="+id;
        String res= makeRequest(URL);
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(res).getAsJsonArray();
        ArrayList<Rutine> d=new ArrayList<Rutine>();
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();

            Rutine a= new Rutine(gsonObj.get("id_rutina").getAsInt(),id,gsonObj.get("color_rutina").getAsString(),gsonObj.get("nombre_rutina").getAsString(),gsonObj.get("usos").getAsInt());
            URL=BaseURL+"/getEjerciciosRutina.php?rutina="+a.getId();
            String res1=makeRequest(URL);
            System.out.println(res1);
            JsonArray gsonArr1 = parser.parse(res1).getAsJsonArray();
            for(JsonElement p: gsonArr1){
                JsonObject objeto =p.getAsJsonObject();
                Ejercicio eje=new Ejercicio(objeto.get("id_ejercicio").getAsInt(),objeto.get("nombre").getAsString(),objeto.get("color").getAsString(),objeto.get("parteCuerpo").getAsString());
                a.ejercicios.add(eje);
            }
            d.add(a);
            System.out.println();
        }
        System.out.println(res);
        return d;
    }

    public static ArrayList<RutineHistoric> getEntrenamientos(int id){
        URL=BaseURL+"/getEntrenamientos.php?usuario="+id;
        String res= makeRequest(URL);
        System.out.println(res);
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(res).getAsJsonArray();
        ArrayList<RutineHistoric> d=new ArrayList<RutineHistoric>();
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();

            RutineHistoric a= new RutineHistoric(gsonObj.get("id_entrenamiento").getAsInt(),gsonObj.get("color_rutina").getAsString(),gsonObj.get("nombre_rutina").getAsString(),gsonObj.get("fecha").getAsInt(),gsonObj.get("esfuerzo").getAsInt());
            URL=BaseURL+"/getSeries.php?entrenamiento="+a.getId();
            String res1=makeRequest(URL);
            System.out.println(res1);
            JsonArray gsonArr1 = parser.parse(res1).getAsJsonArray();
            for(JsonElement p: gsonArr1){
                JsonObject objeto =p.getAsJsonObject();
                Serie eje=new Serie(objeto.get("id_serie").getAsInt(),objeto.get("nombre").getAsString(),objeto.get("color").getAsString(),objeto.get("parteCuerpo").getAsString(),objeto.get("repeticiones").getAsInt(),0);
                a.series.add(eje);
            }
            d.add(a);
            System.out.println();
        }



        return d;
    }

    public static ArrayList<Ejercicio> getEjerciciosUsuario(int id){
        URL=BaseURL+"/getEjerciciosUsuario.php?usuario="+id;
        String res= makeRequest(URL);
        System.out.println(res);
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(res).getAsJsonArray();
        ArrayList<Ejercicio> d = new ArrayList<Ejercicio>();
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();

            Ejercicio a= new Ejercicio(gsonObj.get("id_ejercicio").getAsInt(),gsonObj.get("nombre").getAsString(),gsonObj.get("color").getAsString(),gsonObj.get("parteCuerpo").getAsString());
            d.add(a);
            System.out.println();
        }
        return d;
    }

    public static ArrayList<LastStat> getLastStat(int id){
        URL=BaseURL+"/getLastStat.php?usuario="+id;
        String res= makeRequest(URL);
        System.out.println(res);
        JsonParser parser = new JsonParser();
        JsonArray gsonArr = parser.parse(res).getAsJsonArray();
        ArrayList<LastStat> d = new ArrayList<LastStat>();
        for (JsonElement obj : gsonArr) {
            JsonObject gsonObj = obj.getAsJsonObject();

            LastStat a= new LastStat(gsonObj.get("id_serie").getAsInt(),gsonObj.get("nombre").getAsString(),gsonObj.get("usos").getAsInt());
            d.add(a);
            System.out.println();
        }
        return d;
    }
}

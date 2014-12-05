package com.example.jhordan.Ambar.models;

public class CollectionObjects_Object {
    private String nombre;
    private int humedad;
    private int temperatura;
    private String thumbnail;


    public CollectionObjects_Object(String nombre, int humedad, int temperatura, String thumbnail) {
        this.nombre = nombre;
        this.humedad = humedad;
        this.temperatura = temperatura;
        this.thumbnail = thumbnail;
    }

    public String getNombre(){
        return nombre;
    }

    public int getHumedad(){
        return humedad;
    }

    public int getTemperatura(){
        return temperatura;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setHumedad(int humedad) {
        this.humedad = humedad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTemperatura(int temperatura) {
        this.temperatura = temperatura;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}


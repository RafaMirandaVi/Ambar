package com.example.chicharo.clean_ambar.models;

/**
 * Created by chicharo on 7/12/14.
 */
public class Collection_Object {
    private String nombre;
    private int humedad;
    private int temperatura;
    private String thumbnail;

    public Collection_Object(){}

    public Collection_Object(String nombre, int humedad, int temperatura, String thumbnail) {
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
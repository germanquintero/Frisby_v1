package com.example.ga.f_restaurante;

/**
 * Created by ga on 01/06/2015.
 */
public class Read_places {
    private int numsede;
    private String nombresede;
    private double latitud;
    private double longitud;


    public Read_places(int numsede, String nombresede, double latitud, double longitud) {
        this.numsede = numsede;
        this.nombresede = nombresede;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getnumsede() {
        return numsede;
    }
    public String getnombresede() {
        return nombresede;

    }
    public double getlatitud() {
        return latitud;

    }
    public double getlongitud() {
        return longitud;

    }
}

package com.example.ga.frisby_v1;

/**
 * Created by ga on 31/05/2015.
 */
public class Read_places {
    private String numsede;
    private String nombresede;
    private String latitud;
    private String longitud;


    public Read_places(String numsede, String nombresede, String latitud, String longitud) {
        this.numsede = numsede;
        this.nombresede = nombresede;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getnumsede() {
        return numsede;

    }
    public String getnombresede() {
        return nombresede;
        
    }
    public String getlatitud() {
        return latitud;
        
    }
    public String getlongitud() {
        return longitud;
        
    }
}

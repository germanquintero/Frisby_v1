package com.example.ga.frisby_v1;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Ubication_sedes extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubication_sedes);
        setUpMapIfNeeded();


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */

    private void setUpMap() {


        //Leer datos de la base de datos
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,"admfrisbyDB", null, 1);
        admin.opendatabase();

        Read_places[] ubicacion_sede = new Read_places[5];

        ubicacion_sede[0]=admin.readUbication(1);
        ubicacion_sede[1]=admin.readUbication(2);
        ubicacion_sede[2]=admin.readUbication(3);
        ubicacion_sede[3]=admin.readUbication(4);
        ubicacion_sede[4]=admin.readUbication(5);

        LatLng position0 = new LatLng(ubicacion_sede[0].getlatitud(),ubicacion_sede[0].getlongitud());
        mMap.addMarker(new MarkerOptions()
                .title(ubicacion_sede[0].getnombresede())
                .snippet("Sede: " + Integer.toString(ubicacion_sede[0].getnumsede()))
                .position(position0));

        LatLng position1 = new LatLng(ubicacion_sede[1].getlatitud(),ubicacion_sede[1].getlongitud());
        mMap.addMarker(new MarkerOptions()
                .title(ubicacion_sede[1].getnombresede())
                .snippet("Sede: " + Integer.toString(ubicacion_sede[1].getnumsede()))
                .position(position1));

        LatLng position2 = new LatLng(ubicacion_sede[2].getlatitud(),ubicacion_sede[2].getlongitud());
        mMap.addMarker(new MarkerOptions()
                .title(ubicacion_sede[2].getnombresede())
                .snippet("Sede: " + Integer.toString(ubicacion_sede[2].getnumsede()))
                .position(position2));

        LatLng position3 = new LatLng(ubicacion_sede[3].getlatitud(),ubicacion_sede[3].getlongitud());
        mMap.addMarker(new MarkerOptions()
                .title(ubicacion_sede[3].getnombresede())
                .snippet("Sede: " + Integer.toString(ubicacion_sede[3].getnumsede()))
                .position(position3));

        LatLng position4 = new LatLng(ubicacion_sede[4].getlatitud(),ubicacion_sede[4].getlongitud());
        mMap.addMarker(new MarkerOptions()
                .title(ubicacion_sede[4].getnombresede())
                .snippet("Sede: " + Integer.toString(ubicacion_sede[4].getnumsede()))
                .position(position4));



        //Para acercar masla cámara
         mMap.setMyLocationEnabled(true);
        LatLng Medellin = new LatLng(6.2705,-75.572120);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Medellin, 12));

        

    }




}


package com.bignerdranch.android.myreceipts;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Class: MapsActivity
 *
 * Created by Samuel Allan (SBA006)
 *
 * ICT311 (Mobile App Development)
 * Task 2
 * Semester 2, 2018
 * University of the Sunshine Coast
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double latitude;
    private double longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        Intent intent = getIntent();
//        latitude = intent.getDoubleExtra("latitude", -27.5);
//        longitude = intent.getDoubleExtra("longitude", 153.0);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. By default,
     * the marker was put near Sydney, Australia; however, it's now somewhere in Brisbane (USC-SBA006).
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        updateUI();
    }

    private void updateUI() {
        LatLng myPoint = new LatLng(-27.5, 153.0);

        MarkerOptions myMarker = new MarkerOptions().position(myPoint).title("Receipt location");

        mMap.clear();
        mMap.addMarker(myMarker);

        int zoomLevel = 15;
        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(myPoint, zoomLevel);
        mMap.animateCamera(update);
    }
}

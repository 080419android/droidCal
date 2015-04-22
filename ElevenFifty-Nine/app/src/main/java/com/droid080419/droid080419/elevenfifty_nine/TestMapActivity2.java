package com.droid080419.droid080419.elevenfifty_nine;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class TestMapActivity2 extends FragmentActivity {

     private GoogleMap mMap; // Might be null if Google Play services APK is not available.

     static final LatLng UPD = new LatLng(14.6549,121.0645);
     static final LatLng BORACAY = new LatLng(11.9694, 121.9272);

     public void moveToUPD(View view){
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPD, 15));
     }

     public void moveToBoracay(View view){
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BORACAY, 15));
     }

     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_test_map);
          setUpMapIfNeeded();
     }

     @Override
     protected void onResume() {
          super.onResume();
          setUpMapIfNeeded();
     }

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

     private void setUpMap() {
          mMap.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
     }
}

package com.droid080419.droid080419.elevenfifty_nine;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

     private GoogleMap mMap; // Might be null if Google Play services APK is not available.

     static final LatLng UPD = new LatLng(14.6549,121.0645);

     LatLng coords;
     boolean def;
     @Override
     protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          if(savedInstanceState == null)
               Log.d("MAPS", "Nunully");
          if(savedInstanceState == null || savedInstanceState.isEmpty()){
               coords = CalendarGlobals.gps;
               //def = true;
               Log.d("MAPS","DEFAULT");
          }else {
               double lat = savedInstanceState.getDouble("LATITUDE", 12345.0f);
               double longitude = savedInstanceState.getDouble("LONGITUDE", 6789.0f);
               if (lat > 360.0f || longitude > 360.0f) {
                    coords = UPD;
                    def = true;
               } else {
                    coords = new LatLng(lat, longitude);
                    def = false;
               }
               Log.d("MAPS","not default" + Double.toString(lat) + "," + Double.toString(longitude) );
          }
          setContentView(R.layout.activity_maps);
          setUpMapIfNeeded();
     }

     //un-comment if to be used in debugging
     /*@Override
     protected void onResume() {
          super.onResume();
          setUpMapIfNeeded();
          mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
               @Override
               public void onMapLongClick(LatLng latLng) {
                    CalendarGlobals.gps = new LatLng(latLng.latitude,latLng.longitude);
                    mMap.clear();
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Current Position"));
                    Toast t = Toast.makeText(getApplicationContext(),"Location Changed",Toast.LENGTH_SHORT);
                    t.show();
               }
          });
     }*/

     /**
      * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
      * installed) and the map has not already been instantiated.. This will ensure that we only ever
      * call {@link #setUpMap()} once when {@link #mMap} is not null.
      * <p/>
      * If it isn't installed {@link com.google.android.gms.maps.SupportMapFragment} (and
      * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
      * install/update the Google Play services APK on their device.
      * <p/>
      * A user can return to this FragmentActivity after following the prompt and correctly
      * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
      * have been completely destroyed during this process (it is likely that it would only be
      * stopped or paused), {@link #onCreate(android.os.Bundle)} may not be called again so we should call this
      * method in {@link #onResume()} to guarantee that it will be called.
      */
     private void setUpMapIfNeeded() {
          // Do a null check to confirm that we have not already instantiated the map.

          if (mMap == null) {
               // Try to obtain the map from the SupportMapFragment.
               mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
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
          if(def) {
               mMap.addMarker(new MarkerOptions().position(UPD).title("Default: UPD"));
               CalendarGlobals.gps = new LatLng(UPD.latitude, UPD.longitude);
          }else{
               mMap.addMarker(new MarkerOptions().position(coords).title("Current Position"));
          }
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coords, 15));
          mMap.setMyLocationEnabled(true);

     }
}

package com.droid080419.droid080419.elevenfifty_nine;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Louie on 3/24/2015.
 */
public class TestMapActivity extends Activity {
     static final LatLng UPD = new LatLng(14.6549,121.0645);
     static final LatLng BORACAY = new LatLng(11.9694, 121.9272);
     private GoogleMap mMap;

     @Override
     public void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_test_map);

          mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                  .getMap();

          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPD, 15));
          mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);
     }

     public void moveToUPD(View view){
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(UPD, 15));
     }

     public void moveToBoracay(View view){
          mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(BORACAY, 15));
     }
}

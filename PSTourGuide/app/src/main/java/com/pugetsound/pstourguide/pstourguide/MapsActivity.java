package com.pugetsound.pstourguide.pstourguide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private List<Location> mLocations;
    private UiSettings mUiSettings;
    private DatabaseHelper myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        myDb = new DatabaseHelper(this); // here's the database lol


        permissions();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /** INSTANCE VARIABLES FOR PERMISSIONS**/
    private Context context;
    private Activity activity;
    private static final int PERMISSION_REQUEST_CODE = 1;

    /**
     * Permissions
     */
    private void permissions(){
        context = getApplicationContext(); // for ACCESS_FINE_LOCATION
        activity = this; // for ACCESS_FINE_LOCATION

        requestPermission();
    }

    /**
     * check permissions
     * @return
     */
    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION);
        if (result == PackageManager.PERMISSION_GRANTED) return true;
        else return false;
    }

    /**
     * request permissions
     */
    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)){
            Toast.makeText(context,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(activity, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
        }
    }

    /**
     * request stuff
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MapsActivity.this, R.string.permission_granted, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MapsActivity.this, R.string.permission_denied, Toast.LENGTH_SHORT).show();
                    // call lifecycle activity destroy here cause location is necessary # needs: team discussion
                }
                break;
        }
    }


    public void viewAllData(int classification){
        Cursor res = myDb.getClassificationData(classification);
        if (res.getCount() == 0) {
            System.out.println("No database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            buffer.append("Id: " + res.getInt(0) + "\n");
            buffer.append("Location Name: " + res.getString(1) + "\n");
            buffer.append("Latitude: " + res.getString(2) + "\n");
            buffer.append("Longitude: " + res.getDouble(3) + "\n");
            buffer.append("Classification: " + res.getInt(4) + "\n");
        }

        System.out.println(buffer);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);

        viewAllData(0);
        viewAllData(1);
        viewAllData(2);
        viewAllData(3);
        viewAllData(4);
        viewAllData(5);


//        for (int i=0; i < mLocations.size(); i++){
////            // mLocations.add(new Location(databaseTable.location_name, databaseTable.latitude, databaseTable.longitude)); // needs database logic
////            Marker info = mMap.addMarker(new MarkerOptions().position(mLocations.get(i).getLatLng()).title(mLocations.get(i).getLocationName()).snippet(mLocations.get(i).getLatLng().toString()));
////            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocations.get(i).getLatLng()));
////            info.showInfoWindow(); // this only needs to be ran once... so optimization it needs lol
//
//            boolean isInserted = myDb.insertData(mLocations.get(i).getLocationName(),
//                    mLocations.get(i).getLatitude(),
//                    mLocations.get(i).getLongitude(),
//                    mLocations.get(i).getClassification()
//            );
//
//            if (isInserted) {
//                System.out.println(mLocations.get(i).getLocationName() + " inserted.");
//            } else {
//                System.out.println(mLocations.get(i).getLocationName() + " not inserted.");
//            }
//        }


//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocations.get(0).getLatLng(), 16)); // Zoom Level 16 is optimal
    }




}

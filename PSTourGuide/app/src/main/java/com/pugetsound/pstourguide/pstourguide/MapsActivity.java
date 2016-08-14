package com.pugetsound.pstourguide.pstourguide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap; /** Creating MAP **/
    private UiSettings mUiSettings; /** UI settings for map**/
    private Location mCenter = new Location("University of Puget Sound", 47.262328, -122.481645, 5); /** Center **/
    private DatabaseHelper myDb; /** Opening Database **/
    private List<ArrayList<Location>> mLocations; /** ALL LOCATIONS **/
    private final int maxClassifications = 6;
    private boolean isPoppedUp = false; /** KEEPS TRACK OF DROPDOWN MENU STATE **/

    //expandable list variables (ArrayList in static block are these, too)
    private ExpandableListAdapter listAdapter;
    private ExpandableListView expListView;
    private List<String> listDataHeader;
    private HashMap<String, List<String>> listDataChild;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);


        myDb = new DatabaseHelper(this); /** opens database **/
        permissions(); /** permission system for location specifically // TODO: fix permissions **/

        /** Obtain the SupportMapFragment and get notified when the map is ready to be used. **/
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        /** Fill Locations**/
        mLocations = new ArrayList<>();
        for (int i=0;i<maxClassifications;i++) mLocations.add(new ArrayList<Location>());
        viewAllData();
    }

    /**
     * Prepares list of data for the expandable menu
     * Called at: startup before making menu
     */
    private void prepareListData() {

        /** Creates data header (for parent on click) **/
        // TODO: 0 1 2 3 4 5
        listDataHeader = new ArrayList<>(Arrays.asList("Residence Halls",
                                                        "Food and Beverage",
                                                        "Music and Art",
                                                        "Sports and Recreation",
                                                        "Learning",
                                                        "Services"
        ));

        /** Creates data child  (for child on click) **/
        listDataChild = new HashMap<String, List<String>>();

        // TODO: temporary
        ArrayList<List<String>> listChildren=new ArrayList<List<String>>();

        for (int i=0; i<mLocations.size();i++){

            listChildren.add( new ArrayList<String>() );

            for (int j=0;j<mLocations.get(i).size(); j++){
                listChildren.get(i).add(mLocations.get(i).get(j).getLocationName());
            }
        }

        /** For Loop: N Time :
         * Goes through all the parents in the list and puts children in same list.
         **/
        for(int i=0;i<mLocations.size(); i++) {
            listDataChild.put(listDataHeader.get(i), listChildren.get(i)); // Header, Child data
        }
    }


    //this is the on click for the expandable menu from the toolbar
    //all methods for the expandable list view are now in here
    //this is where they shall stay please
    //unless we move the onclicks out of here
    //but it should be created and infalted in HERE not in the oncreate
    public void toolbarMapMenuClick(View v) {
        if (isPoppedUp==false) {
            LinearLayout explistLayout = (LinearLayout) findViewById(R.id.exp_list);
            if (explistLayout == null) {
                RelativeLayout myLayout = (RelativeLayout) findViewById(R.id.map_activity);
                View mapMenu = getLayoutInflater().inflate(R.layout.explist, myLayout, false);
                myLayout.addView(mapMenu);

                // get the listview
                expListView = (ExpandableListView) findViewById(R.id.lvExp);
                // preparing list data
                prepareListData();
                listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);
                // setting list adapter
                expListView.setAdapter(listAdapter);
                // Listview on child click listener
                // when a location is selected, a marker is added there
                expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        //first maybe we should only have 1 marker up at a time?
                        //a counter could be added to set a 'limit' on markers up at once but.. meh
                        mMap.clear();
                        //sets markers for residence halls'
                        //THESE COULD BE UN HARD CODED BUT IM LAZY -jesse
                        Marker info = mMap.addMarker(new MarkerOptions()
                                .position(mLocations.get(groupPosition).get(childPosition).getLatLng())
                                .title(mLocations.get(groupPosition).get(childPosition).getLocationName())
                                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                        );
                        return true;
                    }
                });

                // Listview Group expanded listener
                // this closes previous group when you open other group
                expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    int lastExpandedGroupPosition = 0;

                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if (groupPosition != lastExpandedGroupPosition) {
                            expListView.collapseGroup(lastExpandedGroupPosition);
                        }
                        lastExpandedGroupPosition = groupPosition;
                    }
                });

                // Listview Group collasped listener
                // this just makes a toast for now
                //do change
                expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
                    @Override
                    public void onGroupCollapse(int groupPosition) {
                        Toast.makeText(getApplicationContext(),
                                listDataHeader.get(groupPosition) + " Collapsed",
                                Toast.LENGTH_SHORT).show();

                    }
                });
            }
            isPoppedUp=true;
        }

        else {
            View myView = findViewById(R.id.exp_list);
            ViewGroup parent = (ViewGroup) myView.getParent();
            parent.removeView(myView);
            isPoppedUp=false;
        }

    }


    // TODO: MOVE PERMISSIONS TO ITS OWN CLASS
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
        return result == PackageManager.PERMISSION_GRANTED;
    }

    /**
     * request permissions
     */
    private void requestPermission(){
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity, android.Manifest.permission.ACCESS_FINE_LOCATION)){
           // Toast.makeText(context,"GPS permission allows us to access location data. Please allow in App Settings for additional functionality.", Toast.LENGTH_LONG).show();
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


    /**
     *
     * @param classification
     */
    public void viewClassificationData(int classification){
        Cursor res = myDb.getClassificationData(classification);
        if (res.getCount() == 0) {
//            System.out.println("No database");
            return;
        }

//        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){

//            buffer.append("Id: " + res.getInt(0) + "\n");
//            buffer.append("Location Name: " + res.getString(1) + "\n");
//            buffer.append("Latitude: " + res.getDouble(2) + "\n");
//            buffer.append("Longitude: " + res.getDouble(3) + "\n");
//            buffer.append("Classification: " + res.getInt(4) + "\n");
//            mLocations.add(new Location(res.getString(1), res.getDouble(2), res.getDouble(3), res.getInt(4)));
        }
//        System.out.println(buffer);
    }

    public void viewAllData(){
        Cursor res = myDb.getAllData();
        if (res.getCount() == 0) return;

        while (res.moveToNext()){
            mLocations.get(res.getInt(4))
                    .add(new Location(res.getString(1),
                                    res.getDouble(2),
                                    res.getDouble(3),
                                    res.getInt(4))
            );
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // TODO: add to database
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mCenter.getLatLng(), 16));

       // TODO settings app
        mUiSettings = mMap.getUiSettings();
        mUiSettings.setZoomControlsEnabled(true);


//        System.out.println("WHAAHSAHSAA" + mLocations.size());


//            Marker info = mMap.addMarker(new MarkerOptions().position(mLocations.get(i).getLatLng()).title(mLocations.get(i).getLocationName()).snippet(mLocations.get(i).getLatLng().toString()));
//            mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocations.get(i).getLatLng()));
//            info.showInfoWindow(); // this only needs to be ran once... so optimization it needs lol


//            boolean isInserted = myDb.insertData("",
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


    }

}

package com.pugetsound.pstourguide.pstourguide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private static ArrayList<Location> mresidenceHalls = new ArrayList<>();
    private static ArrayList<Location> mfoodBev = new ArrayList<>();
    private static ArrayList<Location> mmusicArt = new ArrayList<>();
    private static ArrayList<Location> msportsRec = new ArrayList<>();
    private static ArrayList<Location> mclassRooms = new ArrayList<>();
    private static ArrayList<Location> mservices = new ArrayList<>();

    // im setting up the location variables into arrays sorted by building use
static {
// makin the arraylists


    mfoodBev.add(new Location("Diversions Cafe", 47.2634335,-122.4789625));
    mfoodBev.add(new Location("The Cellar", 47.2628085,-122.4790297));
    mfoodBev.add(new Location("Wheelock Student Center", 47.2631483,-122.4792851));

    mmusicArt.add(new Location("Schneebeck Concert Hall", 47.2636322,-122.4821299));
    mmusicArt.add(new Location("Ceramic Studio", 47.2642912,-122.4792128));
    mmusicArt.add(new Location("Collins Library", 47.264242,-122.481759));
    mmusicArt.add(new Location("Kittridge Hall", 47.2639602,-122.4791598));
    mmusicArt.add(new Location("Sculpture Studio", 47.2641136,-122.4785153));
    mmusicArt.add(new Location("Kilworth Chapel", 47.2653789,-122.4817543));

    mresidenceHalls.add(new Location("Oppenheimer Hall", 47.264429,-122.4809467));
    mresidenceHalls.add(new Location("Anderson/Langdon Hall",47.2648607,-122.4806463));
    mresidenceHalls.add(new Location("Harrington Hall", 47.2651619,-122.4808149));
    mresidenceHalls.add(new Location("Schiff Hall", 47.2651129,-122.480116));
    mresidenceHalls.add(new Location("Out Hause", 47.2605833,-122.4794021));
    mresidenceHalls.add(new Location("Regester Hall", 47.2619825,-122.4810598));
    mresidenceHalls.add(new Location("Seward Hall", 47.2620252,-122.4798254));
    mresidenceHalls.add(new Location("Thomas hall", 47.2617864,-122.4797618));
    mresidenceHalls.add(new Location("Todd-Phibbs Hall North Entrance", 47.2626932,-122.4810082));
    mresidenceHalls.add(new Location("Todd-Phibbs Hall South Entrance", 47.262107, -122.481038));
    mresidenceHalls.add(new Location("Trimble", 47.2629495,-122.4804017));
    mresidenceHalls.add(new Location("Theme Row", 47.2609954,-122.4794028));

    msportsRec.add(new Location("Baseball Diamond", 47.2593788,-122.4829743));
    msportsRec.add(new Location("Softball Field",47.2599231,-122.4805791));
    msportsRec.add(new Location("Alcorn Aboretum", 47.264778,-122.482382));
    msportsRec.add(new Location("Bike Shop", 47.2641155,-122.4782371));
    msportsRec.add(new Location("Field House", 47.259795, -122.481175));
    msportsRec.add(new Location("Peyton Field", 47.2601056,-122.4826096));
    msportsRec.add(new Location("Lower Baker Field", 47.2612333,-122.4826033));
    msportsRec.add(new Location("Todd Field", 47.2623233,-122.4816467));
    msportsRec.add(new Location("Expeditionary", 47.2639838,-122.4778462));
    msportsRec.add(new Location("Baker Stadium", 47.2600233,-122.4825617));
    msportsRec.add(new Location("Karlen Quad", 47.2638794,-122.481745));
    msportsRec.add(new Location("South Quad", 47.2624666,-122.4797446));
    msportsRec.add(new Location("Jones Fountain", 47.2636877,-122.4802265));

    mclassRooms.add(new Location("Smith Hall", 47.2644341,-122.4798282));
    mclassRooms.add(new Location("Weyerhauser Hall", 47.259795,-122.481175));
    mclassRooms.add(new Location("Thompson Hall", 47.263635, -122.4837498));
    mclassRooms.add(new Location("Howarth Hall", 47.263451,-122.4803902));
    mclassRooms.add(new Location("Jones Hall", 47.2636632,-122.4808371));
    mclassRooms.add(new Location("Warner Hall", 47.261701,-122.4817124));
    mclassRooms.add(new Location("Wyatt Hall", 47.2618819,-122.4823751));
    mclassRooms.add(new Location("McIntyre Hall", 47.264193,-122.4805196));

    mservices.add(new Location("Security Services", 47.2633136,-122.4778425));
    mservices.add(new Location("President's House", 47.2654854,-122.4829989));
    mservices.add(new Location("Print & Copy Services", 47.2627185,-122.4782137));
    mservices.add(new Location("Student Diversity Center", 47.2636513,-122.4785338));
    mservices.add(new Location("Residential Life", 47.2636512,-122.4782423));

    }
    private GoogleMap mMap;
    //private List<Location> mLocations;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        permissions();
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
            if (groupPosition==0) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(mresidenceHalls.get(childPosition).getLatLng())
                        .title(mresidenceHalls.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                );
            }
            if (groupPosition==1) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(mfoodBev.get(childPosition).getLatLng())
                        .title(mfoodBev.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                );
            }
            if (groupPosition==2) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(mmusicArt.get(childPosition).getLatLng())
                        .title(mmusicArt.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                );
            }
            if (groupPosition==3) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(mservices.get(childPosition).getLatLng())
                        .title(mservices.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW))
                );
            }
            if (groupPosition==4) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(mclassRooms.get(childPosition).getLatLng())
                        .title(mclassRooms.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN))
                );
            }
            if (groupPosition==5) {
                Marker info = mMap.addMarker(new MarkerOptions()
                        .position(msportsRec.get(childPosition).getLatLng())
                        .title(msportsRec.get(childPosition).getLocationName())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
                );
            }
             return true;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Expanded",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("Residence Halls");
        listDataHeader.add("Food and Beverage");
        listDataHeader.add("Music and Art");
        listDataHeader.add("Services");
        listDataHeader.add("Learning");
        listDataHeader.add("Sports and Recreation");

        // Adding child data
        //residence halls fill
        List<String> residenceHalls = new ArrayList<String>();
        for (int i = 0; i<mresidenceHalls.size(); i++) {
            residenceHalls.add(mresidenceHalls.get(i).getLocationName());
        }
        //food and beverage fill
        List<String> foodBev = new ArrayList<String>();
        for (int i = 0; i<mfoodBev.size(); i++) {
            foodBev.add(mfoodBev.get(i).getLocationName());
        }
        //music and art fill
        List<String> musicArt = new ArrayList<String>();
        for (int i = 0; i<mmusicArt.size(); i++) {
            musicArt.add(mmusicArt.get(i).getLocationName());
        }
        //sports and rec fill
        List<String> sportsRec = new ArrayList<>();
        for (int i = 0; i<msportsRec.size(); i++) {
            sportsRec.add(msportsRec.get(i).getLocationName());
        }
        //classrooms fill
        List<String> classRooms = new ArrayList<>();
        for (int i = 0; i<mclassRooms.size(); i++) {
            classRooms.add(mclassRooms.get(i).getLocationName());
        }
        //services fill
        List<String> services = new ArrayList<>();
        for (int i = 0; i<mservices.size(); i++) {
            services.add(mservices.get(i).getLocationName());
        }
        listDataChild.put(listDataHeader.get(0), residenceHalls); // Header, Child data
        listDataChild.put(listDataHeader.get(1), foodBev);
        listDataChild.put(listDataHeader.get(2), musicArt);
        listDataChild.put(listDataHeader.get(3), services);
        listDataChild.put(listDataHeader.get(4), classRooms);
        listDataChild.put(listDataHeader.get(5), sportsRec);
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

        //mLocations = new ArrayList<>();

        //mLocations.add(new Location("Regester Hall", 47.2619825,-122.4810598));
        //mLocations.add(new Location("Thompson Hall", 47.263635, -122.4837498));
        //mLocations.add(new Location("Field House", 47.259795, -122.481175));
        //mLocations.add(new Location("Weyerhauser Hall", 47.259795,-122.481175));
        //mLocations.add(new Location("Peyton Field", 47.2601056,-122.4826096));
        //mLocations.add(new Location("Lower Baker Field", 47.2612333,-122.4826033));
        //mLocations.add(new Location("Warner Hall", 47.261701,-122.4817124));
        //mLocations.add(new Location("Wyatt Hall", 47.2618819,-122.4823751));
        //mLocations.add(new Location("Todd Field", 47.2623233,-122.4816467));
        //mLocations.add(new Location("Seward Hall", 47.2620252,-122.4798254));
        //mLocations.add(new Location("Thomas hall", 47.2617864,-122.4797618));
        //mLocations.add(new Location("Baseball Diamond", 47.2593788,-122.4829743));
        //mLocations.add(new Location("Softball Field",47.2599231,-122.4805791));
        //mLocations.add(new Location("Todd-Phibbs Hall North Entrance", 47.2626932,-122.4810082));
        //mLocations.add(new Location("Todd-Phibbs Hall South Entrance", 47.262107, -122.481038));
        //mLocations.add(new Location("Trimble", 47.2629495,-122.4804017));
        //mLocations.add(new Location("Jones Fountain", 47.2636877,-122.4802265));
        //mLocations.add(new Location("Diversions Cafe", 47.2634335,-122.4789625));
        //mLocations.add(new Location("Howarth Hall", 47.263451,-122.4803902));
        //mLocations.add(new Location("Jones Hall", 47.2636632,-122.4808371));
        //mLocations.add(new Location("Wheelock Student Center", 47.2631483,-122.4792851));
        //mLocations.add(new Location("The Cellar", 47.2628085,-122.4790297));
        //mLocations.add(new Location("Collins Library", 47.264242,-122.481759));
        //mLocations.add(new Location("Karlen Quad", 47.2638794,-122.481745));
        //mLocations.add(new Location("South Quad", 47.2624666,-122.4797446));
        //mLocations.add(new Location("Theme Row", 47.2609954,-122.4794028));
        //mLocations.add(new Location("Kittridge Hall", 47.2639602,-122.4791598));
        //mLocations.add(new Location("Ceramic Studio", 47.2642912,-122.4792128));
        //mLocations.add(new Location("Sculpture Studio", 47.2641136,-122.4785153));
        //mLocations.add(new Location("McIntyre Hall", 47.264193,-122.4805196));
        //mLocations.add(new Location("President's House", 47.2654854,-122.4829989));
        //mLocations.add(new Location("Schneebeck Concert Hall", 47.2636322,-122.4821299));
        //mLocations.add(new Location("Alcorn Aboretum", 47.264778,-122.482382));
        //mLocations.add(new Location("Smith Hall", 47.2644341,-122.4798282));
        //mLocations.add(new Location("Oppenheimer Hall", 47.264429,-122.4809467));
        //mLocations.add(new Location("Anderson/Langdon Hall",47.2648607,-122.4806463));
        //mLocations.add(new Location("Harrington Hall", 47.2651619,-122.4808149));
        //mLocations.add(new Location("Schiff Hall", 47.2651129,-122.480116));
        //mLocations.add(new Location("Kilworth Chapel", 47.2653789,-122.4817543));
        //mLocations.add(new Location("Expeditionary", 47.2639838,-122.4778462));
        //mLocations.add(new Location("Security Services", 47.2633136,-122.4778425));
        //mLocations.add(new Location("Print & Copy Services", 47.2627185,-122.4782137));
        //mLocations.add(new Location("Out Hause", 47.2605833,-122.4794021));
        //mLocations.add(new Location("Student Diversity Center", 47.2636513,-122.4785338));
        //mLocations.add(new Location("Residential Life", 47.2636512,-122.4782423));
        //mLocations.add(new Location("Bike Shop", 47.2641155,-122.4782371));
        //mLocations.add(new Location("Baker Stadium", 47.2600233,-122.4825617));

        //for (int i=0; i < mLocations.size(); i++){
            // mLocations.add(new Location(databaseTable.location_name, databaseTable.latitude, databaseTable.longitude)); // needs database logic
          //  Marker info = mMap.addMarker(new MarkerOptions().position(mLocations.get(i).getLatLng()).title(mLocations.get(i).getLocationName()).snippet(mLocations.get(i).getLatLng().toString()) .visible(false));
            //mMap.moveCamera(CameraUpdateFactory.newLatLng(mLocations.get(i).getLatLng()));
            //info.showInfoWindow(); // this only needs to be ran once... so optimization it needs lol
        //}



        // TODO
//        Location oppenheimerCafe = new Location();
//        mMap.addMarker(new MarkerOptions().position(oppenheimerCafe).title("Oppenheimer Cafe"));
//        mMap.moveCamera(CameraUpdateFactory.newLocation(oppenheimerCafe));

       // mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mLocations.get(0).getLatLng(), 16)); // Zoom Level 16 is optimal
    }
}

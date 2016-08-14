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
import android.widget.ExpandableListView;
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

//    private static ArrayList<Location> mresidenceHalls = new ArrayList<>();
//    private static ArrayList<Location> mfoodBev = new ArrayList<>();
//    private static ArrayList<Location> mmusicArt = new ArrayList<>();
//    private static ArrayList<Location> msportsRec = new ArrayList<>();
//    private static ArrayList<Location> mclassRooms = new ArrayList<>();
//    private static ArrayList<Location> mservices = new ArrayList<>();
//    private static ArrayList<ArrayList<Location>> mbuildings = new ArrayList<>();
//
//    static {
//
//        mresidenceHalls.add(new Location("Oppenheimer Hall", 47.264429,-122.4809467, 0));
//        mresidenceHalls.add(new Location("Anderson/Langdon Hall",47.2648607,-122.4806463, 0));
//        mresidenceHalls.add(new Location("Harrington Hall", 47.2651619,-122.4808149, 0));
//        mresidenceHalls.add(new Location("Schiff Hall", 47.2651129,-122.480116, 0));
//        mresidenceHalls.add(new Location("Out Hause", 47.2605833,-122.4794021, 0));
//        mresidenceHalls.add(new Location("Regester Hall", 47.2619825,-122.4810598, 0));
//        mresidenceHalls.add(new Location("Seward Hall", 47.2620252,-122.4798254, 0));
//        mresidenceHalls.add(new Location("Thomas hall", 47.2617864,-122.4797618, 0));
//        mresidenceHalls.add(new Location("Todd-Phibbs Hall North Entrance", 47.2626932,-122.4810082, 0));
//        mresidenceHalls.add(new Location("Todd-Phibbs Hall South Entrance", 47.262107, -122.481038, 0));
//        mresidenceHalls.add(new Location("Trimble", 47.2629495,-122.4804017, 0));
//        mresidenceHalls.add(new Location("Theme Row", 47.2609954,-122.4794028, 0));
//
//        mfoodBev.add(new Location("Diversions Cafe", 47.2634335,-122.4789625, 0));
//        mfoodBev.add(new Location("The Cellar", 47.2628085,-122.4790297, 0));
//        mfoodBev.add(new Location("Wheelock Student Center", 47.2631483,-122.4792851, 0));
//        mfoodBev.add(new Location("Oppenheimer Cafe", 47.263533, -122.483203, 0));
//
//        mmusicArt.add(new Location("Schneebeck Concert Hall", 47.2636322,-122.4821299, 0));
//        mmusicArt.add(new Location("Ceramic Studio", 47.2642912,-122.4792128, 0));
//        mmusicArt.add(new Location("Collins Library", 47.264242,-122.481759, 0));
//        mmusicArt.add(new Location("Kittridge Hall", 47.2639602,-122.4791598, 0));
//        mmusicArt.add(new Location("Sculpture Studio", 47.2641136,-122.4785153, 0));
//        mmusicArt.add(new Location("Kilworth Chapel", 47.2653789,-122.4817543, 0));
//
//        msportsRec.add(new Location("Baseball Diamond", 47.2593788,-122.4829743, 0));
//        msportsRec.add(new Location("Softball Field",47.2599231,-122.4805791, 0));
//        msportsRec.add(new Location("Alcorn Aboretum", 47.264778,-122.482382, 0));
//        msportsRec.add(new Location("Bike Shop", 47.2641155,-122.4782371, 0));
//        msportsRec.add(new Location("Field House", 47.259795, -122.481175, 0));
//        msportsRec.add(new Location("Peyton Field", 47.2601056,-122.4826096, 0));
//        msportsRec.add(new Location("Lower Baker Field", 47.2612333,-122.4826033, 0));
//        msportsRec.add(new Location("Todd Field", 47.2623233,-122.4816467, 0));
//        msportsRec.add(new Location("Expeditionary", 47.2639838,-122.4778462, 0));
//        msportsRec.add(new Location("Baker Stadium", 47.2600233,-122.4825617, 0));
//        msportsRec.add(new Location("Karlen Quad", 47.2638794,-122.481745, 0));
//        msportsRec.add(new Location("South Quad", 47.2624666,-122.4797446, 0));
//        msportsRec.add(new Location("Jones Fountain", 47.2636877,-122.4802265, 0));
//
//        mclassRooms.add(new Location("Smith Hall", 47.2644341,-122.4798282, 0));
//        mclassRooms.add(new Location("Weyerhauser Hall", 47.259795,-122.481175, 0));
//        mclassRooms.add(new Location("Thompson Hall", 47.263635, -122.4837498, 0));
//        mclassRooms.add(new Location("Howarth Hall", 47.263451,-122.4803902, 0));
//        mclassRooms.add(new Location("Jones Hall", 47.2636632,-122.4808371, 0));
//        mclassRooms.add(new Location("Warner Hall", 47.261701,-122.4817124, 0));
//        mclassRooms.add(new Location("Wyatt Hall", 47.2618819,-122.4823751, 0));
//        mclassRooms.add(new Location("McIntyre Hall", 47.264193,-122.4805196, 0));
//
//        mservices.add(new Location("Security Services", 47.2633136,-122.4778425, 0));
//        mservices.add(new Location("President's House", 47.2654854,-122.4829989, 0));
//        mservices.add(new Location("Print & Copy Services", 47.2627185,-122.4782137, 0));
//        mservices.add(new Location("Student Diversity Center", 47.2636513,-122.4785338, 0));
//        mservices.add(new Location("Residential Life", 47.2636512,-122.4782423, 0));
//
//        mbuildings.add(mresidenceHalls);
//        mbuildings.add(mfoodBev);
//        mbuildings.add(mmusicArt);
//        mbuildings.add(mservices);
//        mbuildings.add(mclassRooms);
//        mbuildings.add(msportsRec);
//    }

    private GoogleMap mMap; /** Creating MAP **/
    private UiSettings mUiSettings; /** UI settings for map**/
    private Location mCenter = new Location("University of Puget Sound", 47.262328, -122.481645, 5); /** Center **/
    private DatabaseHelper myDb; /** Opening Database **/
    private List<ArrayList<Location>> mLocations; /** ALL LOCATIONS **/
    private final int maxClassifications = 6;

    //expandable list variables (ArrayList in static block are these, too)
    // TODO: are these private or public.....?
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;


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

        expListView = (ExpandableListView) findViewById(R.id.lvExp); /** Get ListView **/
        prepareListData(); /**  prepares list data **/
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild); /** creates listAdapter **/
        expListView.setAdapter(listAdapter); /** sets up listAdapter **/

        /**
         * [1] List view with child click listeners, but not parent listener.
         * [2] When a location is selected (child), then a marker is placed.
         * TODO: PUT IN OWN METHOD
         */
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            /**
             * TODO: JESSE COMMENTS HERE
             * @param parent
             * @param v
             * @param groupPosition
             * @param childPosition
             * @param id
             * @return
             */
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                mMap.clear(); /** clears markers on click **/

                /** HUE_MAGENTA - residence halls default color **/
                Marker info = mMap.addMarker(new MarkerOptions()
                    .position(mLocations.get(groupPosition).get(childPosition).getLatLng())
                    .title(mLocations.get(groupPosition).get(childPosition).getLocationName())
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_MAGENTA))
                );

             return true;
            }
        });

        /**
         * [1] List view group expanded listener (on parent click)
         * [2] Previous child closed when you open another child
         * TODO: PUT IN OWN METHOD
         */
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedGroupPosition = 0;

            /**
             * TODO: JESSE COMMENTS HERE
             * @param groupPosition
             */
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != lastExpandedGroupPosition){
                    expListView.collapseGroup(lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;
            }
        });

        /**
         * [1] list view group collapsed listener (on parent click)
         * [2] Toast
         * TODO: implement history or something
         * TODO: PUT IN OWN METHOD
         */
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            /**
             * TODO: JESSE COMMENTS HERE
             * @param groupPosition
             */
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getApplicationContext(),
                        listDataHeader.get(groupPosition) + " Collapsed",
                        Toast.LENGTH_SHORT).show();
            }
        });
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

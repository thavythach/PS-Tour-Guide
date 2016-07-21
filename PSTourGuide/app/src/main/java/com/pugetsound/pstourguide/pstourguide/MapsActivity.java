package com.pugetsound.pstourguide.pstourguide;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
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
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
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

        LatLng thompsonHall = new LatLng(47.263635, -122.4837498); // # issue 007: create a new LatLng Thompson obj
        mMap.addMarker(new MarkerOptions().position(thompsonHall).title("Thompson Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thompsonHall));

        LatLng fieldHouse = new LatLng(47.259795,-122.481175);
        mMap.addMarker(new MarkerOptions().position(fieldHouse).title("Field House"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(fieldHouse));

        LatLng weyerhaeuserHall = new LatLng(47.259795,-122.481175);
        mMap.addMarker(new MarkerOptions().position(weyerhaeuserHall).title("Weyerhaeuser Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(weyerhaeuserHall));

        // peyton field aka baker stadium
        LatLng peytonField = new LatLng(47.2601056,-122.4826096);
        mMap.addMarker(new MarkerOptions().position(peytonField).title("Peyton Field"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peytonField));

        LatLng lowerBakerField = new LatLng(47.2612333,-122.4826033);
        mMap.addMarker(new MarkerOptions().position(lowerBakerField).title("Lower Baker Field"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lowerBakerField));

        LatLng warnerHall = new LatLng(47.261701,-122.4817124);
        mMap.addMarker(new MarkerOptions().position(warnerHall).title("Warner Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(warnerHall));

        LatLng wyattHall = new LatLng(47.2618819,-122.4823751);
        mMap.addMarker(new MarkerOptions().position(wyattHall).title("Wyatt Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wyattHall));

        LatLng toddField = new LatLng(47.2623233,-122.4816467);
        mMap.addMarker(new MarkerOptions().position(toddField).title("Todd Field"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toddField));

        LatLng sewardHall = new LatLng(47.2620252,-122.4798254);
        mMap.addMarker(new MarkerOptions().position(sewardHall).title("Seward Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sewardHall));

        LatLng regesterHall = new LatLng(47.2619825,-122.4810598);
        mMap.addMarker(new MarkerOptions().position(regesterHall).title("Regester Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(regesterHall));

        // RIP Commencement
        LatLng thomasHall = new LatLng(47.2617864,-122.4797618);
        mMap.addMarker(new MarkerOptions().position(thomasHall).title("Thomas Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(thomasHall));

        LatLng basballDiamond = new LatLng(47.2593788,-122.4829743);
        mMap.addMarker(new MarkerOptions().position(basballDiamond).title("Baseball Diamond"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(basballDiamond));

        LatLng softballField = new LatLng(47.2599231,-122.4805791);
        mMap.addMarker(new MarkerOptions().position(softballField).title("Softball Field"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(softballField));

        LatLng toddPhibbs = new LatLng(47.2626932,-122.4810082);
        mMap.addMarker(new MarkerOptions().position(toddPhibbs).title("Todd/Phibbs Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(toddPhibbs));

        LatLng trimble = new LatLng(47.2629495,-122.4804017);
        mMap.addMarker(new MarkerOptions().position(trimble).title("Trimble Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(trimble));

        LatLng jonesFountain = new LatLng(47.2636877,-122.4802265);
        mMap.addMarker(new MarkerOptions().position(jonesFountain).title("Jones Fountain"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jonesFountain));

        LatLng diversionsCafe = new LatLng(47.2634335,-122.4789625);
        mMap.addMarker(new MarkerOptions().position(diversionsCafe).title("Diversions Cafe"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(diversionsCafe));

        LatLng howarthHall = new LatLng(47.263451,-122.4803902);
        mMap.addMarker(new MarkerOptions().position(howarthHall).title("Howarth Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(howarthHall));

        LatLng jonesHall = new LatLng(47.2636632,-122.4808371);
        mMap.addMarker(new MarkerOptions().position(jonesHall).title("Jones Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(jonesHall));

        LatLng wheelockStudentCenter = new LatLng(47.2631483,-122.4792851);
        mMap.addMarker(new MarkerOptions().position(wheelockStudentCenter).title("Wheelock Student Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(wheelockStudentCenter));

        LatLng theCellar = new LatLng(47.2628085,-122.4790297);
        mMap.addMarker(new MarkerOptions().position(theCellar).title("The Cellar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(theCellar));

        LatLng collinsLibrary = new LatLng(47.264242,-122.481759);
        mMap.addMarker(new MarkerOptions().position(collinsLibrary).title("Collin's Library"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(collinsLibrary));

        LatLng karlenQuad = new LatLng(47.2638794,-122.481745);
        mMap.addMarker(new MarkerOptions().position(karlenQuad).title("Karlen Quad"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(karlenQuad));

        LatLng southQuad = new LatLng(47.2624666,-122.4797446);
        mMap.addMarker(new MarkerOptions().position(southQuad).title("South Quad"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(southQuad));

        LatLng themeRow = new LatLng(47.2609954,-122.4794028);
        mMap.addMarker(new MarkerOptions().position(themeRow).title("Theme Row"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(themeRow));

        LatLng kittredgeHall = new LatLng(47.2639602,-122.4791598);
        mMap.addMarker(new MarkerOptions().position(kittredgeHall).title("Kittredge Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kittredgeHall));

        LatLng ceramicStudio = new LatLng(47.2642912,-122.4792128);
        mMap.addMarker(new MarkerOptions().position(ceramicStudio).title("Ceramic's Studio"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(ceramicStudio));

        LatLng sculptureStudio = new LatLng(47.2641136,-122.4785153);
        mMap.addMarker(new MarkerOptions().position(sculptureStudio).title("Sculpture Studio"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sculptureStudio));

        LatLng mcintyreHall = new LatLng(47.264193,-122.4805196);
        mMap.addMarker(new MarkerOptions().position(mcintyreHall).title("McIntyre Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(mcintyreHall));

        LatLng presidentsHouse = new LatLng(47.2654854,-122.4829989);
        mMap.addMarker(new MarkerOptions().position(presidentsHouse).title("President's House"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(presidentsHouse));

        LatLng schneebeckConcertHall = new LatLng(47.2636322,-122.4821299);
        mMap.addMarker(new MarkerOptions().position(schneebeckConcertHall).title("Schneebeck Concert Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(schneebeckConcertHall));

        LatLng alcornArboretum = new LatLng(47.264778,-122.482382);
        mMap.addMarker(new MarkerOptions().position(alcornArboretum).title("Alcorn Arboretum"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(alcornArboretum));

        LatLng smithHall = new LatLng(47.2644341,-122.4798282);
        mMap.addMarker(new MarkerOptions().position(smithHall).title("Smith Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(smithHall));

        LatLng oppenheimerHall = new LatLng(47.264429,-122.4809467);
        mMap.addMarker(new MarkerOptions().position(oppenheimerHall).title("Oppenheimer Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(oppenheimerHall));

        LatLng andersonLangdonHall = new LatLng(47.2648607,-122.4806463);
        mMap.addMarker(new MarkerOptions().position(andersonLangdonHall).title("Anderson / Langdon Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(andersonLangdonHall));

        LatLng harringtonHall = new LatLng(47.2651619,-122.4808149);
        mMap.addMarker(new MarkerOptions().position(harringtonHall).title("Harrington Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(harringtonHall));

        LatLng schiffHall = new LatLng(47.2651129,-122.480116);
        mMap.addMarker(new MarkerOptions().position(schiffHall).title("Schiff Hall"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(schiffHall));

        LatLng kilworthChapel = new LatLng(47.2653789,-122.4817543);
        mMap.addMarker(new MarkerOptions().position(kilworthChapel).title("Kilworth Chapel"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(kilworthChapel));

        LatLng expeditionary = new LatLng(47.2639838,-122.4778462);
        mMap.addMarker(new MarkerOptions().position(expeditionary).title("Expeditionary"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(expeditionary));

        LatLng securityServices = new LatLng(47.2633136,-122.4778425);
        mMap.addMarker(new MarkerOptions().position(securityServices).title("Security Services"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(securityServices));

        LatLng printCopyServices = new LatLng(47.2627185,-122.4782137);
        mMap.addMarker(new MarkerOptions().position(printCopyServices).title("Print and Copy Services"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(printCopyServices));

        LatLng outHaus = new LatLng(47.2605833,-122.4794021);
        mMap.addMarker(new MarkerOptions().position(outHaus).title("Out Haus"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(outHaus));

        LatLng studentDiversityCenter = new LatLng(47.2636513,-122.4785338);
        mMap.addMarker(new MarkerOptions().position(studentDiversityCenter).title("Student Diversity Center"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(studentDiversityCenter));

        LatLng resLife = new LatLng(47.2636512,-122.4782423);
        mMap.addMarker(new MarkerOptions().position(resLife).title("Student Activities / Res. Life"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(resLife));

        LatLng bikeShop = new LatLng(47.2641155,-122.4782371);
        mMap.addMarker(new MarkerOptions().position(bikeShop).title("Bicycle Mechanic Shop"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bikeShop));

        LatLng bakerStadium = new LatLng(47.2600233,-122.4825617);
        mMap.addMarker(new MarkerOptions().position(bakerStadium).title("Baker Stadium"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(bakerStadium));

        // TODO
//        LatLng oppenheimerCafe = new LatLng();
//        mMap.addMarker(new MarkerOptions().position(oppenheimerCafe).title("Oppenheimer Cafe"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(oppenheimerCafe));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(regesterHall, 16)); // Zoom Level 16 is optimal
    }
}

package com.pugetsound.pstourguide.pstourguide;

import com.google.android.gms.maps.model.LatLng;

import java.util.UUID;

/**
 * Created by Thavy Thach on 7/21/2016.
 */
public class Location {

    private UUID mId;
    private String mLocationName;
    private double mLatitude;
    private double mLongitude;
    private LatLng mLatLng;
    private int mClassification;


    public Location(String mLocationName, double mLatitude, double mLongitude, int mClassification){
        mId = UUID.randomUUID();

        this.mLocationName = mLocationName;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mLatLng = new LatLng(mLatitude, mLongitude);
        this.mClassification = mClassification; // 0 1 2 3 4 5
    }

    public int getClassification() {
        return mClassification;
    }

    public LatLng getLatLng() {
        return mLatLng;
    }

    public void setLatLng(LatLng latLng) {
        mLatLng = latLng;
    }

    public UUID getId() {
        return mId;
    }

    public String getLocationName() {
        return mLocationName;
    }

    public void setLocationName(String locationName) {
        mLocationName = locationName;
    }

    public double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(double latitude) {
        mLatitude = latitude;
    }

    public double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(double longitude) {
        mLongitude = longitude;
    }
}

package com.pugetsound.pstourguide.pstourguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Thavy Thach on 8/7/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    //
    public static final String DATABASE_NAME = "locations.db";
    public static final String TABLE_NAME = "location_table";

    // cols
    public static final String ID = "ID";
    public static final String LOCATION_NAME = "NAME";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";
    public static final String CLASSIFICATION = "CLASSIFICATION";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +
                " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LATITUDE DOUBLE, LONGITUDE DOUBLE, CLASSIFICATION INTEGER )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    /**
     *  whenever this is called... database will be created
     * @param context
     */
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public boolean insertData(String locationName, double latitude, double longitude, int classification){
        SQLiteDatabase db = this.getWritableDatabase(); // creates database and table - for checking
        ContentValues contentValues = new ContentValues();
        contentValues.put(LOCATION_NAME, locationName);
        contentValues.put(LATITUDE, latitude);
        contentValues.put(LONGITUDE, longitude);
        contentValues.put(CLASSIFICATION, classification);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1)
            return false;
        else
            return true;
    }

    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public Cursor getClassificationData(int classification){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "SELECT * FROM " + TABLE_NAME + " WHERE CLASSIFICATION = " + classification;
        Cursor res = db.rawQuery(q , null);
        return res;
    }
}

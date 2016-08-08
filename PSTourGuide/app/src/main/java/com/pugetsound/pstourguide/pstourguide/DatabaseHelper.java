package com.pugetsound.pstourguide.pstourguide;

import android.content.Context;
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
    public static final String LOCATION_NAME = "NAME";
    public static final String LATITUDE = "LATITUDE";
    public static final String LONGITUDE = "LONGITUDE";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, LATITUDE DOUBLE, LONGITUDE DOUBLE )");
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
        SQLiteDatabase db = this.getWritableDatabase(); // creates database and table - for checking
        System.out.println(db);
        System.out.println("what");
    }
}

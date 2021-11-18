package com.example.locationservice;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.location.Address;
import android.location.Geocoder;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "LocationManager.db";
    public static final String TABLE_ADDRESSES = "addresses";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_LATITUDE = "latitude";
    private static final String KEY_LONGITUDE = "longitude";

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATION_TABLE = "CREATE TABLE " + TABLE_ADDRESSES + "("
                + " INTEGER PRIMARY KEY," + KEY_ADDRESS + " TEXT,"
                + KEY_LATITUDE + " TEXT," + KEY_LONGITUDE + " TEXT" + ")";
        db.execSQL(CREATE_LOCATION_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESSES);

        // Create tables again
        onCreate(db);
    }


    void addLocation(Addresses location) {
        ContentValues values = new ContentValues();
        values.put(KEY_ADDRESS, location.getAddress());
        values.put(KEY_LATITUDE, location.getLatitude());
        values.put(KEY_LONGITUDE, location.getLongitude());
        SQLiteDatabase db = this.getWritableDatabase();

        // Inserting Row
        db.insert(TABLE_ADDRESSES, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    Addresses findCoords(String address) {
        String query = "Select * FROM " + TABLE_ADDRESSES + " WHERE " + KEY_ADDRESS + " =  \"" + address + "\"";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        Addresses location = new Addresses();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();

            location.setAddress(cursor.getString(1));
            location.setLatitude(cursor.getString(2));
            location.setLongitude(cursor.getString(3));
            cursor.close();
        } else {
            location = null;
        }
        db.close();
        return location;
    }

}
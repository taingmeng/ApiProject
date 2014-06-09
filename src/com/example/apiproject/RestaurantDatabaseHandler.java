package com.example.apiproject;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RestaurantDatabaseHandler extends SQLiteOpenHelper {
	// All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;
 
    // Database Name
    private static final String DATABASE_NAME = "HungryGoWhere.db";
 
    // Contacts table name
    private static final String TABLE_RESTAURANT = "restaurant";
 
    // Contacts Table Columns names
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String ADDRESS_BLOB = "address_blob";
    private static final String DESCRIPTION_SHORT ="description_short";
    private static final String COUNTER_DISLIKES ="counter_dislikes";
    private static final String COUNTER_LIKES ="counter_likes";
    private static final String TOTAL_DEALS ="total_deals";
    private static final String IS_HALAL ="is_halal";
    
 
    public RestaurantDatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
 
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_RESTAURANT_TABLE = "CREATE TABLE " + TABLE_RESTAURANT + "("
                + ID + " INTEGER PRIMARY KEY,"
        		+ NAME + " TEXT,"
        		+ ADDRESS_BLOB + " TEXT,"
                + DESCRIPTION_SHORT + " TEXT,"
                + COUNTER_DISLIKES + " INTEGER,"
                + COUNTER_LIKES + " INTEGER,"
                + TOTAL_DEALS + " INTEGER,"
                + IS_HALAL + " INTEGER" + ")";
        db.execSQL(CREATE_RESTAURANT_TABLE);
    }
 
    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANT);
 
        // Create tables again
        onCreate(db);
    }
    
    public void addRestaurant(Restaurant restaurant) {
        SQLiteDatabase db = this.getWritableDatabase();
     
        ContentValues values = new ContentValues();
        values.put(ID, restaurant.getId()); 
        values.put(NAME, restaurant.getName());
        values.put(ADDRESS_BLOB, restaurant.getAddressBlob());
        values.put(DESCRIPTION_SHORT, restaurant.getAddressBlob());
        values.put(COUNTER_DISLIKES, restaurant.getCounterDislikes());
        values.put(COUNTER_LIKES, restaurant.getCounterLikes());
        values.put(TOTAL_DEALS, restaurant.getTotalDeals());
        values.put(IS_HALAL, restaurant.getIsHalal());
        
     
        // Inserting Row
        db.insert(TABLE_RESTAURANT, null, values);
        db.close(); // Closing database connection
    }
    public Restaurant getRestaurant(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
 
        Cursor cursor = db.query(TABLE_RESTAURANT, new String[] { ID, NAME, ADDRESS_BLOB, DESCRIPTION_SHORT, COUNTER_DISLIKES, COUNTER_LIKES, TOTAL_DEALS, IS_HALAL  }, ID + "=?",
                new String[] { id }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
 
        Restaurant restaurant = new Restaurant(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getInt(4), cursor.getInt(5), cursor.getInt(6), cursor.getInt(7));
        // return contact
        return restaurant;
    }
 // Getting All Restaurants
    public List<Restaurant> getAllRestaurants() {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT;
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Restaurant restaurant = new Restaurant();
            	restaurant.setId(cursor.getString(0));
            	restaurant.setName(cursor.getString(1));
            	restaurant.setAddressBlob(cursor.getString(2));
            	restaurant.setDescriptionShort(cursor.getString(3));
            	restaurant.setCounterDislikes(cursor.getInt(4));
            	restaurant.setCounterLikes(cursor.getInt(5));
            	restaurant.setTotalDeals(cursor.getInt(6));
            	restaurant.setIsHalal(cursor.getInt(7));
                // Adding contact to list
            	restaurantList.add(restaurant);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return restaurantList;
    }
    
    public List<Restaurant> getRestaurantsByName(String NAME) {
        List<Restaurant> restaurantList = new ArrayList<Restaurant>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_RESTAURANT + " WHERE NAME LIKE '%"+NAME+"%'";
 
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
 
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
            	Restaurant restaurant = new Restaurant();
            	restaurant.setId(cursor.getString(0));
            	restaurant.setName(cursor.getString(1));
            	restaurant.setAddressBlob(cursor.getString(2));
            	restaurant.setDescriptionShort(cursor.getString(3));
            	restaurant.setCounterDislikes(cursor.getInt(4));
            	restaurant.setCounterLikes(cursor.getInt(5));
            	restaurant.setTotalDeals(cursor.getInt(6));
            	restaurant.setIsHalal(cursor.getInt(7));
                // Adding contact to list
            	restaurantList.add(restaurant);
            } while (cursor.moveToNext());
        }
 
        // return contact list
        return restaurantList;
    }

}

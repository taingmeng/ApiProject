package com.example.apiproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class RestaurantDetail extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_restaurant_detail);
		
		Intent intent = getIntent();
	    String ID = intent.getStringExtra("ID");
	    
	    RestaurantDatabaseHandler restaurantDb = new RestaurantDatabaseHandler(this);
	    Restaurant restaurant = restaurantDb.getRestaurant(ID);
		setTitle(restaurant.getName());
		
		TextView address = (TextView)findViewById(R.id.addressblob);
		address.setText(restaurant.getAddressBlob());
		
		TextView likes = (TextView)findViewById(R.id.likes);
		likes.setText(String.valueOf(restaurant.getCounterLikes()));
		
		TextView dislikes = (TextView)findViewById(R.id.dislikes);
		dislikes.setText(String.valueOf(restaurant.getCounterDislikes()));
		
	}
}

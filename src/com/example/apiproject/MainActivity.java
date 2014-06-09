package com.example.apiproject;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.MenuInflater;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.ListAdapter;
import android.text.TextUtils;

import org.json.JSONObject;
import org.json.JSONException;
import org.json.JSONArray;


import android.content.Intent;

public class MainActivity extends Activity {
	
	private SearchView searchView;
	private String nameQuery;
    private ListView restaurantListView;
    private RestaurantDatabaseHandler restaurantDb;
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		restaurantListView = (ListView) findViewById(R.id.listView_restaurant);	
		restaurantListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this, RestaurantDetail.class);
				Restaurant restaurant = (Restaurant) parent.getAdapter().getItem(position);
				intent.putExtra("ID", restaurant.getId());
				startActivity(intent);
				
			}
		});
	}
	
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the options menu from XML
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        
        // Get the SearchView and set the searchable configuration
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.menu_search).getActionView();
        
        // Assumes current activity is the searchable activity
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(searchQueryListener);
        //searchView.setIconifiedByDefault(false); // Do not iconify the widget; expand it by default
        return super.onCreateOptionsMenu(menu);
    }

    private OnQueryTextListener searchQueryListener = new OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
        	nameQuery = query;
        	String URL = "http://hgwm2359-staging.herokuapp.com/api/v1/restaurants?name="+ query +"&restaurant_fields=id,address_blob,counter_dislikes,counter_likes,description_short,is_halal,name";
        	Fetcher fetch = new Fetcher();
        	fetch.execute(URL);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if (TextUtils.isEmpty(newText)) {

            }
            return true;
        }

    };

    private class Fetcher extends AsyncTask<String, Void, JSONObject> {
    	private ProgressDialog progressDialog;
       
   	 
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = ProgressDialog.show(MainActivity.this, "Finding restaurants whose name include \""+nameQuery+"\"", "Searching...");
        }
    
        @Override
        protected JSONObject doInBackground(String... params) {
        	JSONParser jParser = new JSONParser();
            // Getting JSON from URL
            JSONObject json = jParser.getJSONFromUrl(params[0]);
            try {
            	JSONArray jsonarray = json.getJSONArray("restaurants");
            	createRestaurantDatabase(jsonarray);
               
            } catch (JSONException e) {
              e.printStackTrace();
            }
            
            return json; 
        }
     
        /**
        * After completing background task Dismiss the progress dialog
        * **/
        protected void onPostExecute(JSONObject jsonobject) {
        	super.onPostExecute (jsonobject);
        	progressDialog.dismiss();
        	restaurantListView.setAdapter(new RestaurantBaseAdapter(MainActivity.this, restaurantDb.getRestaurantsByName(nameQuery)));  
    		           
        }
        
        protected void createRestaurantDatabase(JSONArray jsonarray){
        	restaurantDb = new RestaurantDatabaseHandler(MainActivity.this);
        	for (int i= 0; i<jsonarray.length(); i++ ){
        		try {
    	    		JSONObject json = jsonarray.getJSONObject(i);
    	    		Restaurant restaurant = new Restaurant(json.getString("id"), json.getString("name"), json.getString("address_blob"),
    	    				json.getString("description_short"), json.getInt("counter_dislikes"), json.getInt("counter_likes"),
    	    				json.getInt("total_deals"), json.getString("is_halal")=="true"? 1:0);
    	    		
    	    		restaurantDb.addRestaurant(restaurant);
        		} catch (JSONException e) {
      	          e.printStackTrace();
      	        }
        			
        	}
        }
    }
    
}

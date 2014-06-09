package com.example.apiproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.app.SearchManager;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;

public class SearchableActivity extends Activity {
	private TextView txtQuery;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchable);
		
		// get the action bar
        ActionBar actionBar = getActionBar();
 
        // Enabling Back navigation on Action Bar icon
        actionBar.setDisplayHomeAsUpEnabled(true);
 
        txtQuery = (TextView) findViewById(R.id.txtQuery);
		
		handleIntent(getIntent());
	}
	@Override
    public void onNewIntent(Intent intent){
		super.onNewIntent(intent);  
		setIntent(intent);
		handleIntent(intent);
	}
	
	public void handleIntent(Intent intent){
		if(Intent.ACTION_SEARCH.equals(intent.getAction())){
			String query = intent.getStringExtra(SearchManager.QUERY);
			//doMySearch(query);
			txtQuery.setText("Search Query: " + query);
			Toast.makeText(SearchableActivity.this, "Hello", Toast.LENGTH_LONG).show();
		}
	}
	
    private void doMySearch(String query){
    	
    }
}

package com.example.apiproject;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.BaseAdapter;

public class RestaurantBaseAdapter extends BaseAdapter{
	//List<Staff> myStaffList;
	private final List<Restaurant> restaurantlist;
	LayoutInflater inflater;
	private final Context context;
	
	public RestaurantBaseAdapter(Context context, List<Restaurant> restaurantlist){
		this.restaurantlist = restaurantlist;
		this.context = context;
		inflater = LayoutInflater.from(this.context);
	}
	
	@Override
	public int getCount() {
		return restaurantlist.size();
	}
	@Override
	public Restaurant getItem(int position) {
		return restaurantlist.get(position);
	}
	@Override
	public long getItemId(int position) {
		return 0;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder viewHolder;
		
		if(convertView == null) {
			convertView = inflater.inflate(R.layout.restaurant, parent, false);
			viewHolder = new ViewHolder();
			
			viewHolder.name = detail(convertView, R.id.name, restaurantlist.get(position).getName());
			viewHolder.addressblob = detail(convertView, R.id.addressblob, restaurantlist.get(position).getAddressBlob());
			
			
			convertView.setTag(viewHolder);

		} else {
			viewHolder = (ViewHolder) convertView.getTag();			
		}

		
		return convertView;
	} 
	
	TextView detail(View v, int resId, String text) {
		TextView tv = (TextView) v.findViewById(resId);
		tv.setText(text);
		return tv;
	}

	private static class ViewHolder {	
		TextView name, addressblob;
	}
	
}

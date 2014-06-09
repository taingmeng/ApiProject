package com.example.apiproject;

import java.util.List;

public class Page {
	private int previous_page;
	private int next_page;
	private int total_pages;
	private int total_entries;
	private List<Restaurant> restaurants;
	
	public Page(int previous_page, int next_page, int total_pages, int total_entries, List<Restaurant> restaurants){
		this.previous_page = previous_page;
		this.next_page = next_page;
		this.total_pages = total_pages;
		this.total_entries = total_entries;
		this.restaurants = restaurants;
	}
	
	public Page(){
		this.previous_page = 0;
		this.next_page = 0;
		this.total_pages = 0;
		this.total_entries = 0;
		this.restaurants = null;
	}

}

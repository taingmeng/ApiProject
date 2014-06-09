package com.example.apiproject;

public class Restaurant {
	private String address_blob;
	private int counter_dislikes;
	private int counter_likes;
	private String description_short;
	private String id;
	private String name;
	private int total_deals;
	private int is_halal;
	
	public Restaurant(String id, String name, String address_blob, String description_short, 
			int counter_dislikes, int counter_likes, int total_deals, int is_halal){
		this.address_blob = address_blob;
		this.counter_dislikes = counter_dislikes;
		this.counter_likes = counter_likes;
		this.description_short = description_short;
		this.id = id;
		this.is_halal = is_halal;
		this.name = name;
		this.total_deals = total_deals;

	}
	public Restaurant(){
		this.address_blob = null;
		this.counter_dislikes = 0;
		this.counter_likes = 0;
		this.description_short = null;
		this.id = null;
		this.is_halal = 0;
		this.name = null;
		this.total_deals = 0;
	}
	
	public String getAddressBlob(){
		return this.address_blob;
	}
	public void setAddressBlob(String address_blob){
		this.address_blob = address_blob;
	}
	
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name = name;
	}
	
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}
	
	public String getDescriptionShort(){
		return this.description_short;
	}
	public void setDescriptionShort(String description_short){
		this.description_short = description_short;
	}
	
	public int getCounterDislikes(){
		return this.counter_dislikes;
	}
	public void setCounterDislikes(int counter_dislikes){
		this.counter_dislikes = counter_dislikes;
	}
	
	public int getCounterLikes(){
		return this.counter_likes;
	}
	public void setCounterLikes(int counter_likes){
		this.counter_likes = counter_likes;
	}
	
	public int getIsHalal(){
		return this.is_halal;
	}
	public void setIsHalal(int isHalal){
		this.is_halal = isHalal;
	}
	
	public int getTotalDeals(){
		return this.total_deals;
	}
	public void setTotalDeals(int total_deals){
		this.total_deals = total_deals;
	}
}

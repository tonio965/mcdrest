package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class OrderRequest {
	
	private 
	@NotNull(message ="restaurant id is null")
	int restaurantid;
	
	private 
	@NotNull(message="no ordered items")
	int [] orderedItems;

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public int[] getOrderedItems() {
		return orderedItems;
	}

	public void setOrderedItems(int[] orderedItems) {
		this.orderedItems = orderedItems;
	}
	
	

}

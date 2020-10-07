package com.example.model;

public class OrderTable {
	
	private int orderid;
	private int restaurantid;
	
	public OrderTable(int orderid, int restaurantid) {
		super();
		this.orderid = orderid;
		this.restaurantid = restaurantid;
	}
	
	public OrderTable(int restaurantid) {
		this.restaurantid=restaurantid;
	}

	public OrderTable() {
		super();
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	@Override
	public String toString() {
		return "OrderTable [orderid=" + orderid + ", restaurantid=" + restaurantid + "]";
	}
	
	
	
	

}

package com.example.model;

public class OrderTable {
	
	private int orderid;
	private int restaurantid;
	private float cost;
	
	public OrderTable(int orderid, int restaurantid, float cost) {
		super();
		this.orderid = orderid;
		this.restaurantid = restaurantid;
		this.cost = cost;
	}
	
	public OrderTable(int restaurantid) {
		this.restaurantid=restaurantid;
	}

	
	
	public OrderTable() {
		super();
	}

	
	
	
	public float getCost() {
		return cost;
	}

	public void setCost(float cost) {
		this.cost = cost;
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

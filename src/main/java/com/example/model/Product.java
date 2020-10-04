package com.example.model;

public class Product {
	
	private int productid;
	private int calories;
	private String productname;
	
	public Product() {
		super();
	}
	
	public Product(int productid, int calories, String productname) {
		super();
		this.productid = productid;
		this.calories = calories;
		this.productname = productname;
	}
	public int getProductid() {
		return productid;
	}
	public void setProductid(int productid) {
		this.productid = productid;
	}
	public int getCalories() {
		return calories;
	}
	public void setCalories(int calories) {
		this.calories = calories;
	}
	public String getProductname() {
		return productname;
	}
	public void setProductname(String productname) {
		this.productname = productname;
	}

	@Override
	public String toString() {
		return "Product [productid=" + productid + ", calories=" + calories + ", productname=" + productname + "]";
	}
	
	

}

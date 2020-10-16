package com.example.model;

public class MenuItem {
	
	private int menuitemid;
	private String menuitemname;
	private int calories;
	private float price;
	
	public MenuItem() {
		super();
	}

	public MenuItem(int menuitemid, String menuitemname, int calories, float price) {
		super();
		this.menuitemid = menuitemid;
		this.menuitemname = menuitemname;
		this.calories = calories;
		this.price = price;
	}
	public MenuItem(MenuItemRequest menuitemrequest) {
		this.menuitemid=menuitemrequest.getMenuitemid();
		this.menuitemname=menuitemrequest.getMenuitemname();
		this.calories=menuitemrequest.getCalories();
		this.price = menuitemrequest.getPrice();
	}

	public int getMenuitemid() {
		return menuitemid;
	}

	public void setMenuitemid(int menuitemid) {
		this.menuitemid = menuitemid;
	}

	public String getMenuitemname() {
		return menuitemname;
	}

	public void setMenuitemname(String menuitemname) {
		this.menuitemname = menuitemname;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "MenuItem [menuitemid=" + menuitemid + ", menuitemname=" + menuitemname + ", calories=" + calories + "]";
	}
	
	
	
	

}

package com.example.model;

public class MenuItem {
	
	private int menuitemid;
	private String menuitemname;
	private int calories;
	
	public MenuItem() {
		super();
	}

	public MenuItem(int menuitemid, String menuitemname, int calories) {
		super();
		this.menuitemid = menuitemid;
		this.menuitemname = menuitemname;
		this.calories = calories;
	}
	public MenuItem(MenuItemRequest menuitemrequest) {
		this.menuitemid=menuitemrequest.getMenuitemid();
		this.menuitemname=menuitemrequest.getMenuitemname();
		this.calories=menuitemrequest.getCalories();
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

	@Override
	public String toString() {
		return "MenuItem [menuitemid=" + menuitemid + ", menuitemname=" + menuitemname + ", calories=" + calories + "]";
	}
	
	
	
	

}

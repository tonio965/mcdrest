package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class MenuItemRequest {
	
	private int menuitemid;
	
	private 
	@NotNull(message="menuitemname is null")
	String menuitemname;
	
	private 
	@NotNull(message="menuitemcalories is null")
	int calories;
	
	private
	float price;
	

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
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
		return "MenuItemRequest [menuitemid=" + menuitemid + ", menuitemname=" + menuitemname + ", calories=" + calories
				+ "]";
	}
	
	
	

}

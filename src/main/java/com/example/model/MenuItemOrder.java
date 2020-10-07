package com.example.model;

public class MenuItemOrder {
	
	private int orderid;
	private int menuitemid;
	
	public MenuItemOrder(int orderid, int menuitemid) {
		super();
		this.orderid = orderid;
		this.menuitemid = menuitemid;
	}

	public MenuItemOrder() {
		super();
	}

	public int getOrderid() {
		return orderid;
	}

	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	public int getMenuitemid() {
		return menuitemid;
	}

	public void setMenuitemid(int menuitemid) {
		this.menuitemid = menuitemid;
	}

	@Override
	public String toString() {
		return "MenuItemOrder [orderid=" + orderid + ", menuitemid=" + menuitemid + "]";
	}
		

}

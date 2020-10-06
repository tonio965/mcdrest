package com.example.model;

public class MenuitemProduct {
	
	private int menuitemid;
	private int productid;
	
	public MenuitemProduct(int menuitemid, int productid) {
		super();
		this.menuitemid = menuitemid;
		this.productid = productid;
	}
	
	public MenuitemProduct(MenuItemProductRequest mipr) {
		this.menuitemid=mipr.getMenuitemid();
		this.productid=mipr.getProductid();
	}

	public MenuitemProduct() {
		super();
	}

	public int getMenuitemid() {
		return menuitemid;
	}

	public void setMenuitemid(int menuitemid) {
		this.menuitemid = menuitemid;
	}

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	@Override
	public String toString() {
		return "MenuitemProduct [menuitemid=" + menuitemid + ", productid=" + productid + "]";
	}
	
	
	
	

}

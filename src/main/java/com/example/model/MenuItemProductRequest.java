package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class MenuItemProductRequest {
	
	private 
	@NotNull(message ="menuitemid is empty")
	int menuitemid;
	
	private
	@NotNull(message = "productid is empty")
	int productid;

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
		return "MenuItemProductRequest [menuitemid=" + menuitemid + ", productid=" + productid + "]";
	}
	
	
	

}

package com.example.model;

import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

@Validated
public class ProductRequest {
	
	private int productid;
	
	private
	@NotNull(message = "prodictname is empty")
	String productname;
	
	private
	@NotNull(message = "product calories is empty")
	int calories;

	public int getProductid() {
		return productid;
	}

	public void setProductid(int productid) {
		this.productid = productid;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "ProductRequest [productid=" + productid + ", productname=" + productname + ", calories=" + calories
				+ "]";
	}
	
	

}

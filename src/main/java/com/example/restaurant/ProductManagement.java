package com.example.restaurant;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.server.ContainerRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.configurations.Database;
import com.example.daos.ProductDAO;
import com.example.daos.RestaurantDAO;
import com.example.model.IdRequest;
import com.example.model.Product;
import com.example.model.ProductRequest;
import com.example.model.Restaurant;
import com.example.model.TestRequest;

@Component
@Path("/productManagement")
public class ProductManagement {

	
	@Autowired
	Database db;
	
	@Autowired
	ProductDAO productDAO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String process(
//		      @HeaderParam("Authorization")
//		      String auth,
		      @Valid @RequestBody ProductRequest productRequest,
		      @Context ContainerRequest request) {
		Product product = new Product(productRequest);
		productDAO.insert(product);
		System.out.println("request: "+productRequest.toString());
		return String.valueOf(product.getProductid());
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		Product r = productDAO.getById(idrequest.getId());
		JSONObject mainObj = new JSONObject();
		mainObj.put("productid", r.getProductid());
		mainObj.put("productname", r.getProductname());
		mainObj.put("calories", r.getCalories());
		System.out.println(r.toString());
		return mainObj.toJSONString();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllProducts")
	public String getall(
		      @Context ContainerRequest request) throws SQLException {
		List<Product> products = productDAO.getAllProducts();
		JSONObject mainObj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Product p : products) {
			JSONObject smallObj = new JSONObject();
			smallObj.put("productid", p.getProductid());
			smallObj.put("productname", p.getProductname());
			array.add(smallObj);
		}
		mainObj.put("products", array);
		return mainObj.toJSONString();
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteById(
			@Valid @RequestBody IdRequest idrequest,
		    @Context ContainerRequest request) {
	    return productDAO.deleteById(idrequest.getId());

	}
}

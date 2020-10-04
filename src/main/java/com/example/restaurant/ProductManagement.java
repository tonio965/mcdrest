package com.example.restaurant;

import java.sql.SQLException;

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
		return "true";
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		Product r = productDAO.getById(idrequest.getId());
		System.out.println(r.toString());
		return "1";
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

package com.example.restaurant;

import java.sql.SQLException;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.configurations.Database;
import com.example.daos.RestaurantDAO;
import com.example.model.IdRequest;
import com.example.model.Restaurant;
import com.example.model.TestRequest;

@Component
@Path("/restaurantManagement")
public class RestaurantManagement {
	
	@Autowired
	Database db;
	
	@Autowired
	RestaurantDAO restaurantDAO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String process(
//		      @HeaderParam("Authorization")
//		      String auth,
		      @Valid @RequestBody TestRequest testRequest,
		      @Context ContainerRequest request) {
		Restaurant restaurant = new Restaurant(testRequest);
		restaurantDAO.insert(restaurant);
		System.out.println("request: "+testRequest.toString());
		return "true";
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		Restaurant r = restaurantDAO.getById(idrequest.getId());
		System.out.println(r.toString());
		return "1";
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteById(
			@Valid @RequestBody IdRequest idrequest,
		    @Context ContainerRequest request) {
	    return restaurantDAO.deleteById(idrequest.getId());

	}
	
}

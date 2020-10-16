package com.example.endpoints;

import java.sql.SQLException;
import java.util.List;

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
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.configurations.Database;
import com.example.daos.RestaurantDAO;
import com.example.model.IdRequest;
import com.example.model.Product;
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
		return testRequest.toString();
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		Restaurant r = restaurantDAO.getById(idrequest.getId());
		JSONObject mainObj = new JSONObject();
		mainObj.put("restaurantid", r.getId());
		mainObj.put("restaurantname", r.getName());
		mainObj.put("login", r.getLogin());
		mainObj.put("passwordhash", r.getPasswordHash());
		System.out.println(r.toString());
		return mainObj.toJSONString();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllRestaurants")
	public String getall(
		      @Context ContainerRequest request) throws SQLException {
		List<Restaurant> restaurants = restaurantDAO.getAllRestaurants();
		JSONObject mainObj = new JSONObject();
		JSONArray array = new JSONArray();
		for(Restaurant p : restaurants) {
			JSONObject smallObj = new JSONObject();
			smallObj.put("restaurantid", p.getId());
			smallObj.put("restaurantname", p.getName());
			smallObj.put("login", p.getLogin());
			smallObj.put("password", p.getPasswordHash());
			array.add(smallObj);
		}
		mainObj.put("restaurants", array);
		return mainObj.toJSONString();
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

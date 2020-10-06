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
import com.example.daos.MenuItemDAO;
import com.example.model.IdRequest;
import com.example.model.MenuItem;
import com.example.model.MenuItemRequest;
import com.example.model.Restaurant;
import com.example.model.TestRequest;

@Component
@Path("/menuItemManagement")
public class MenuItemManagement {

	@Autowired
	Database db;
	
	@Autowired
	MenuItemDAO menuItemDAO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String process(
//		      @HeaderParam("Authorization")
//		      String auth,
		      @Valid @RequestBody MenuItemRequest menuItemRequest,
		      @Context ContainerRequest request) {
		MenuItem menuitem = new MenuItem(menuItemRequest);
		menuItemDAO.insert(menuitem);
		System.out.println("request: "+menuItemRequest.toString());
		return "true";
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public MenuItem getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		MenuItem r = menuItemDAO.getById(idrequest.getId());
		System.out.println(r.toString());
		return r;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public int deleteById(
			@Valid @RequestBody IdRequest idrequest,
		    @Context ContainerRequest request) {
	    return menuItemDAO.deleteById(idrequest.getId());

	}
}

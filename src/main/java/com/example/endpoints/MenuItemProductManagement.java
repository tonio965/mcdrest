package com.example.endpoints;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
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
import com.example.daos.MenuItemDAO;
import com.example.daos.MenuItemProductDAO;
import com.example.daos.ProductDAO;
import com.example.model.IdRequest;
import com.example.model.MenuItem;
import com.example.model.MenuItemProductRequest;
import com.example.model.MenuitemProduct;
import com.example.model.Product;
import com.example.model.Restaurant;
import com.example.model.TestRequest;

@Component
@Path("/menuitemproductmanagement")
public class MenuItemProductManagement {
	
	@Autowired
	Database db;
	
	@Autowired
	MenuItemProductDAO menuitemproductDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	MenuItemDAO menuitemDAO;
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String process(
//		      @HeaderParam("Authorization")
//		      String auth,
		      @Valid @RequestBody MenuItemProductRequest menuitemproductrequest,
		      @Context ContainerRequest request) {
		MenuitemProduct menuitemproduct = new MenuitemProduct(menuitemproductrequest);
		menuitemproductDAO.insert(menuitemproduct);
		
		System.out.println("request: "+menuitemproductrequest.toString());
		return "true";
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		List<MenuitemProduct> productsInItem = menuitemproductDAO.getByMenuItemId(idrequest.getId()); //gets from n to n table ids
		List<String> products = new ArrayList<String>(); //empty products list etc hamburger has: porkchop, bun
		MenuItem menuItem = menuitemDAO.getById(idrequest.getId()); //gets name of item etc hamburger
		
		JSONObject mainObj = new JSONObject();
		mainObj.put("menuitem", menuItem.getMenuitemname());
		mainObj.put("menuitemid", menuItem.getMenuitemid());
		
		JSONArray array = new JSONArray();
		for(MenuitemProduct mip : productsInItem) {
			Product p = productDAO.getById(mip.getProductid());
			products.add(p.getProductname());
			JSONObject jo = new JSONObject();
			array.add(p.getProductname());
		}
		mainObj.put("ingredients", array);
		return mainObj.toJSONString();
	}
	
	
	

}

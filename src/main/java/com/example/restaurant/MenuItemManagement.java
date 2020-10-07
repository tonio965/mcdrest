package com.example.restaurant;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.example.daos.MenuItemDAO;
import com.example.daos.MenuItemProductDAO;
import com.example.daos.ProductDAO;
import com.example.model.IdRequest;
import com.example.model.MenuItem;
import com.example.model.MenuItemRequest;
import com.example.model.MenuitemProduct;
import com.example.model.Product;
import com.example.model.Restaurant;
import com.example.model.TestRequest;

@Component
@Path("/menuItemManagement")
public class MenuItemManagement {

	@Autowired
	Database db;
	
	@Autowired
	MenuItemDAO menuItemDAO;
	
	@Autowired
	MenuItemProductDAO menuitemproductDAO;
	
	@Autowired
	ProductDAO productDAO;
	
	
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
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		MenuItem r = menuItemDAO.getById(idrequest.getId());
		List<Product> products = new ArrayList<Product>();
		List<MenuitemProduct> menuitemproducts = menuitemproductDAO.getByMenuItemId(r.getMenuitemid());
		JSONObject mainObj = new JSONObject();
		mainObj.put("menuitem", r.getMenuitemname());
		mainObj.put("menuitemid", r.getMenuitemid());
		JSONArray array = new JSONArray();
		for(MenuitemProduct mip: menuitemproducts) {
			Product p = productDAO.getById(mip.getProductid());
			products.add(p);
			JSONObject smallObj = new JSONObject();
			smallObj.put("productid", p.getProductid());
			smallObj.put("productname", p.getProductname());
			array.add(smallObj);
		}
		mainObj.put("ingredients", array);
		System.out.println(r.toString());
		
		
		return mainObj.toJSONString();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getAllMenuItems")
	public String getAll(
		      @Context ContainerRequest request) throws SQLException {

		List<MenuItem> menuItemList = menuItemDAO.getAllMenuItems();//gets name of item etc hamburger
		JSONObject mainObj = new JSONObject(); //tworze glowny obiekt jsonowy
		JSONArray menuItemArray = new JSONArray(); //tworze tablice menuitemow
		for(MenuItem mi : menuItemList) { 
			JSONObject menuItem = new JSONObject(); //pojedynczy item np burger
			menuItem.put("menuitemid", mi.getMenuitemid()); //nazwa 
			menuItem.put("menuitemname", mi.getMenuitemname()); //id
			JSONArray productsInMenuItem = new JSONArray(); //skladniki w potrawie
			List<MenuitemProduct> productsInItem = menuitemproductDAO.getByMenuItemId(mi.getMenuitemid());
			for(MenuitemProduct mip : productsInItem) { //po tabeli n do n szukam produktow
				Product p = productDAO.getById(mip.getProductid());
				productsInMenuItem.add(p.getProductname());
			}
			menuItem.put("ingredients", productsInMenuItem);
			menuItemArray.add(menuItem);
		}
		mainObj.put("menuitems", menuItemArray);
		return mainObj.toJSONString();

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

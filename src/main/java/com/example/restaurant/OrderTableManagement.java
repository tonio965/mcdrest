package com.example.restaurant;

import java.sql.SQLException;
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
import com.example.daos.MenuItemOrderDAO;
import com.example.daos.MenuItemProductDAO;
import com.example.daos.OrderTableDAO;
import com.example.daos.ProductDAO;
import com.example.model.IdRequest;
import com.example.model.MenuItemOrder;
import com.example.model.MenuItemProductRequest;
import com.example.model.MenuitemProduct;
import com.example.model.OrderRequest;
import com.example.model.OrderTable;

@Component
@Path("/ordertablemanagement")
public class OrderTableManagement {
	
	@Autowired
	Database db;
	
	@Autowired
	MenuItemOrderDAO menuitemorderDAO;
	
	@Autowired
	OrderTableDAO ordertableDAO;
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public String process(
//		      @HeaderParam("Authorization")
//		      String auth,
		      @Valid @RequestBody OrderRequest orderRequest,
		      @Context ContainerRequest request) {
		OrderTable order = new OrderTable(orderRequest.getRestaurantid());
		int orderId = ordertableDAO.insert(order);
		int [] items = orderRequest.getOrderedItems();
		for(int i=0; i<items.length; i++) {
			MenuItemOrder mio = new MenuItemOrder(orderId, items[i]);
			menuitemorderDAO.insert(mio);
		}
		System.out.println("orderid: "+orderId);
		return "orderid: "+orderId;
		
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String getById(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		OrderTable order = ordertableDAO.getById(idrequest.getId());
		List<String> names = ordertableDAO.getItemsFromOrder(idrequest.getId());
		JSONObject orderjson = new JSONObject();
		orderjson.put("restaurantid", order.getRestaurantid());
		orderjson.put("orderid", order.getOrderid());
		JSONArray orderedItems = new JSONArray();
		for(String name : names) {
			orderedItems.add(name);
		}
		orderjson.put("orderedItems", orderedItems);
		
		return orderjson.toJSONString();
	}
	
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/getAllOrdersFromRestaurant")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllFromRestaurant(
		      @Valid @RequestBody IdRequest idrequest,
		      @Context ContainerRequest request) throws SQLException {
		List<Integer> orderids = ordertableDAO.getByRestaurantId(idrequest.getId());
		JSONObject mainObj = new JSONObject();
		JSONArray orders = new JSONArray();
		for(Integer orderId : orderids) {
			JSONObject order = new JSONObject();
			order.put("orderId", orderId);
			order.put("restaurantId", idrequest.getId());
			JSONArray itemsInOrder = new JSONArray();
			List<String> names = ordertableDAO.getItemsFromOrder(orderId);
			for(String name : names) {
				itemsInOrder.add(name);
			}
			order.put("orderedItems", itemsInOrder);
			orders.add(order);
		}
		mainObj.put("orders", orders);
		return mainObj.toJSONString();
	}
	

}

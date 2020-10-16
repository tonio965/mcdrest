package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.MenuItem;
import com.example.model.OrderTable;
import com.example.model.Product;

@Component
public class OrderTableDAO {

	  @Autowired
      Database db;
	  
	  public int insert(OrderTable order, float price) {
		    int randomNumber=generateRandomNumber();
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("insert into ordertable (orderid, restaurantid, cost) values(?,?,?)");
		      ps.setInt(1, randomNumber);
		      ps.setInt(2, order.getRestaurantid());
		      ps.setFloat(3, price);
		      ps.executeUpdate();
		      ps.close();

		  } catch (SQLException e) {
		      throw new RuntimeException(e);

		  } 
		    return randomNumber;
		    
	 }
	 
	 public List<MenuItem> getItemsFromOrder(int id) throws SQLException{
		 Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select menuitemname, price from menuitem join "
		  		+ "menuitemorder on menuitem.menuitemid=menuitemorder.menuitemid where menuitemorder.orderid = ?");
		 List<MenuItem> names = new ArrayList<>();
		 ps.setInt(1, id);
	     ResultSet rs=ps.executeQuery();  
	     while(rs.next()){  
	       MenuItem m = new MenuItem();
	       m.setMenuitemname(rs.getString(1));
	       m.setPrice(rs.getFloat(2));
	       names.add(m);
	      }  
	      ps.close();
		 return names;
	 }
	  
	 public List<OrderTable> getAllOrdersFromRestaurant() throws SQLException{
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from ordertable where restaurantid = ?");
		  List<OrderTable> orders = new ArrayList<OrderTable>();
		  OrderTable r = new OrderTable();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new OrderTable(rs.getInt(1), rs.getInt(2), rs.getFloat(3));
	        orders.add(r);
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return orders;
	 }
	 
	  public OrderTable getById(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from ordertable where orderid = ?");
	      ps.setInt(1,id);
	      OrderTable r = new OrderTable();
	      ResultSet rs=ps.executeQuery(); 
	      while(rs.next()){  
	        r = new OrderTable(rs.getInt(1), rs.getInt(2),rs.getFloat(3));
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return r; //zmienic na jsona
	  }
	  
	  public List<Integer> getByRestaurantId(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select orderid from ordertable where restaurantid = ?");
	      ps.setInt(1,id);
	      List<Integer> orderIds = new ArrayList<>();
	      ResultSet rs=ps.executeQuery(); 
	      while(rs.next()){  
	        orderIds.add(rs.getInt(1)); 
	      }  
	      ps.close();
		  return orderIds; //zmienic na jsona
	  }
	  
	  public int deleteById(int id) {
		  try {
		    Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		    PreparedStatement ps = conn.prepareStatement("delete from ordertable where orderid = ?");
		    ps.setInt(1, id);
		    ps.executeUpdate();
		    ps.close();
		  } catch (SQLException e) {
		      throw new RuntimeException(e);
		  }
		  return id;
	  }
	 
	  
	 private int generateRandomNumber() {
		 Random rnd = new Random();
		 int upperbound = 100000;
		 int int_random = rnd.nextInt(upperbound); 
		 return int_random;
	 }
}

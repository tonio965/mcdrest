package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.MenuItemOrder;
import com.example.model.Product;

@Component
public class MenuItemOrderDAO {
	
	@Autowired
	Database db;

	  public int insert(MenuItemOrder menuitemorder) {
		  try {
		    Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		    PreparedStatement ps = conn.prepareStatement("insert into menuitemorder (orderid, menuitemid) values(?,?)");
		    ps.setInt(1, menuitemorder.getOrderid());
		    ps.setInt(2, menuitemorder.getMenuitemid());
		    ps.executeUpdate();
		    ps.close();
		  } catch (SQLException e) {
		      throw new RuntimeException(e);
		  } 
		  return menuitemorder.getMenuitemid();
		    
	 }
	  
	  public MenuItemOrder getByIds(int orderid, int menuitemid) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from product where orderid = ? and menuitemid = ?");
	      ps.setInt(1,orderid);
	      ps.setInt(2, menuitemid);
	      MenuItemOrder r = new MenuItemOrder();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new MenuItemOrder(rs.getInt(1), rs.getInt(2));
	        System.out.println(r.toString());  
	      }  
	      ps.close();
	      return r;
	  }

}

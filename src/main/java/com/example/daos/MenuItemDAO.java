package com.example.daos;

import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.MenuItem;
import com.example.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class MenuItemDAO {

	  @Autowired
	  Database db;
	
	  public int insert(MenuItem menuitem) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("insert into menuitem (menuitemid, menuitemname, calories) values(?,?,?)");
		      ps.setInt(1, menuitem.getMenuitemid());
		      ps.setString(2, menuitem.getMenuitemname());
		      ps.setInt(3, menuitem.getCalories());
		      ps.executeUpdate();
		      ps.close();

		  } catch (SQLException e) {
		      throw new RuntimeException(e);

		  } 
		    return 1;
		    
	 }
	  
	  public MenuItem getById(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from menuitem where menuitemid = ?");
	      ps.setInt(1,id);
	      MenuItem r = new MenuItem();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new MenuItem(rs.getInt(1), rs.getString(2), rs.getInt(3));
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return r;
	  }
	  
	  public int deleteById(int id) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("delete from menuitem where menuitemid = ?");
		      ps.setInt(1, id);
		      ps.executeUpdate();
		      ps.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);

		    }
		    return id;

	  }	
}

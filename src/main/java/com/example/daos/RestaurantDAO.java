package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.Restaurant;

@Component
public class RestaurantDAO {
	
	  @Autowired
      Database db;
	
	  public int insert(Restaurant restaurant) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("insert into Restaurant (restaurantid, restaurantname, login, passwordhash) values(?,?,?,?)");
		      ps.setInt(1, restaurant.getId());
		      ps.setString(2, restaurant.getName());
		      ps.setString(3, restaurant.getLogin());
		      ps.setString(4, restaurant.getPasswordHash());
		      ps.executeUpdate();
		      ps.close();

		  } catch (SQLException e) {
		      throw new RuntimeException(e);

		  } 
		    return 1;
		    
	 }
	 
	  public Restaurant getById(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from Restaurant where restaurantid = ?");
	      ps.setInt(1,id);
	      Restaurant r = new Restaurant();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new Restaurant(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return r;
	  }
	  
	  public int deleteById(int id) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("delete from Restaurant where restaurantid = ?");
		      ps.setInt(1, id);
		      ps.executeUpdate();
		      ps.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);

		    }
		    return 1;

		  }

}

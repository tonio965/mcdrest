package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.Product;
import com.example.model.Restaurant;

@Component
public class ProductDAO {
	
	  @Autowired
      Database db;
	  
	  public int insert(Product product) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("insert into product (productid, productname, calories) values(?,?,?)");
		      ps.setInt(1, product.getProductid());
		      ps.setInt(3, product.getCalories());
		      ps.setString(2, product.getProductname());
		      ps.executeUpdate();
		      ps.close();

		  } catch (SQLException e) {
		      throw new RuntimeException(e);

		  } 
		    return 1;
		    
	 }
	  
	  public Product getById(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from product where productid = ?");
	      ps.setInt(1,id);
	      Product r = new Product();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new Product(rs.getInt(1), rs.getInt(3), rs.getString(2));
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return r;
	  }
	  
	  public int deleteById(int id) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("delete from product where productid = ?");
		      ps.setInt(1, id);
		      ps.executeUpdate();
		      ps.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);

		    }
		    return 1;

		  }

}

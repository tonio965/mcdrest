package com.example.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.model.Product;

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
	  
	  public List<Product> getAllProducts() throws SQLException{
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  PreparedStatement ps = conn.prepareStatement("select * from product");
		  List<Product> products = new ArrayList<Product>();
	      Product r = new Product();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new Product(rs.getInt(1), rs.getInt(3), rs.getString(2));
	        products.add(r);
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return products;
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
		    return id;

	  }

}

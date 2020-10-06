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
import com.example.model.MenuitemProduct;
import com.example.model.Product;

@Component
public class MenuItemProductDAO {

	  @Autowired
      Database db;
	  
	  public int insert(MenuitemProduct product) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("insert into menuitemproduct (menuitemid, productid) values(?,?)");
		      ps.setInt(1, product.getMenuitemid());
		      ps.setInt(2, product.getProductid());
		      ps.executeUpdate();
		      ps.close();

		  } catch (SQLException e) {
		      throw new RuntimeException(e);

		  } 
		    return 1;
		    
	 }
	 
	  public List<MenuitemProduct> getByMenuItemId(int id) throws SQLException {
		  Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		  List<MenuitemProduct> list = new ArrayList<MenuitemProduct>();
		  PreparedStatement ps = conn.prepareStatement("select * from menuitemproduct where menuitemid = ?");
	      ps.setInt(1,id);
	      MenuitemProduct r = new MenuitemProduct();
	      ResultSet rs=ps.executeQuery();  
	      while(rs.next()){  
	        r = new MenuitemProduct(rs.getInt(1), rs.getInt(2));
	        list.add(r);
	        System.out.println(r.toString());  
	      }  
	      ps.close();
		  return list;
	  }
	  
	  public int deleteByIds(int productid, int menuitemid) {
		    try {
		      Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		      PreparedStatement ps = conn.prepareStatement("delete from menuitemproduct where productid = ? and menuitemid = ?");
		      ps.setInt(1, productid);
		      ps.setInt(2, menuitemid);
		      ps.executeUpdate();
		      ps.close();
		    } catch (SQLException e) {
		        throw new RuntimeException(e);

		    }
		    return menuitemid;

	  }
}

package com.example.demo;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.example.restaurant.MenuItemManagement;
import com.example.restaurant.MenuItemProductManagement;
import com.example.restaurant.ProductManagement;
import com.example.restaurant.RestaurantManagement;

@Component
public class MyResourceConfig extends ResourceConfig{
  public MyResourceConfig() {
    loadPath(Test1.class);
    loadPath(RestaurantManagement.class);
    loadPath(ProductManagement.class);
    loadPath(MenuItemManagement.class);
    loadPath(MenuItemProductManagement.class);

  }
  
  private void loadPath(Class<?> klaz) {
    register(klaz);
    showPathInfo(klaz);
  }
  
  private void showPathInfo(Class<?> klaz) {
    Path path = klaz.getAnnotation(Path.class);
    if(path != null) {
      System.out.println(path.value()+" path loaded");
    }
  }
}

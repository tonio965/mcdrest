package com.example.demo;

import javax.ws.rs.Path;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
import com.example.restaurant.RestaurantManagement;

@Component
public class MyResourceConfig extends ResourceConfig{
  public MyResourceConfig() {
    loadPath(Test1.class);
    loadPath(RestaurantManagement.class);

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

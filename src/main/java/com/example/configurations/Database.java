package com.example.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Database{

  private DatabaseSettings settings;
  

  @Autowired
  public Database(DatabaseSettings settings){
    System.out.println("Database(): " + settings.getDbUrl());
    this.settings = settings;
  }
  
  
  public String getUrl() {
	  return settings.getDbUrl();
  }
  
  public String getPw() {
	  return settings.getDbPassword();
  }
  
  public String getLogin() {
	  return settings.getDbUser();
  }
}

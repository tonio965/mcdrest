package com.example.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseSettings {
  
  @Value("${db.url}")
  private String dbUrl;
  
  @Value("${db.user}")
  private String dbUser;
  
  @Value("${db.password}")
  private String dbPassword;

	public String getDbUrl() {
		return dbUrl;
	}
	
	public void setDbUrl(String dbUrl) {
		this.dbUrl = dbUrl;
	}
	
	public String getDbUser() {
		return dbUser;
	}
	
	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}
	
	public String getDbPassword() {
		return dbPassword;
	}
	
	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}
  
  
}

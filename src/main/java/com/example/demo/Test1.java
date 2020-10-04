package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ContainerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.SystemPropertyUtils;

import com.example.configurations.Database;
import com.example.configurations.DatabaseSettings;

@Component
@Path("/test1")
public class Test1 {
	
	
	@Autowired
	Database db;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String process(@Context ContainerRequest request) {   
		try {
			System.out.println("test:");
			System.out.println("url:"+db.getUrl());
			System.out.println("login:"+db.getLogin());
			System.out.println("pw:"+db.getPw());
		    Connection conn = DriverManager.getConnection( db.getUrl(), db.getLogin(), db.getPw());
		    System.out.println("works");
		    String sqlSelectAllPersons = "SELECT * FROM test";
		    PreparedStatement ps = conn.prepareStatement(sqlSelectAllPersons);
		    ResultSet rs = ps.executeQuery();
		    while (rs.next()) {
		        int id = rs.getInt("idtest");
		        System.out.println("test"+ id);
		    }
		}
		catch(Exception e) {
			System.out.println("broken");
		}
		return "tested";
	}
	
	

}

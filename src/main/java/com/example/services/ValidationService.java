package com.example.services;

import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.configurations.Database;
import com.example.daos.RestaurantDAO;
import com.example.endpoints.RestaurantManagement;
import com.example.model.Restaurant;

@Component
public class ValidationService {
	
	@Autowired
	Database db;
	
	@Autowired
	RestaurantDAO restaurantDAO;
	
	public boolean checkAccess(String auth, int restaurantId) throws SQLException {
		String [] decodedValues = decodeBasicAuth(auth);
		String [] splitUsername = decodedValues[0].split("_");
		int restaurantIdFromAuth = Integer.parseInt(splitUsername[2]); // pattern: name_checkoutid_restaurantid
 		if(restaurantId == restaurantIdFromAuth) {
 			Restaurant r = restaurantDAO.getById(restaurantIdFromAuth);
 			String restaurantcreds = r.getLogin()+"_"+r.getName();
 			if(restaurantcreds.equals(splitUsername[0]+"_"+splitUsername[1]) && r.getPasswordHash().equals(decodedValues[1])){ //if provided creds are the same as in db
 				return true;
 			}
 		}
 		else {
 			return false;
 		}
		return false;
		
	}

	private String [] decodeBasicAuth(String auth) {
		if (auth != null && auth.toLowerCase().startsWith("basic")) {
		    // Authorization: Basic base64credentials
		    String base64Credentials = auth.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    // credentials = username:password
		    final String[] values = credentials.split(":", 2);
		    System.out.println(values[0] +" "+ values[1]);
		    return values;
		}
		return null;
	}
	

}

package com.example.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.configurations.DatabaseSettings;

@SpringBootApplication(scanBasePackages={
		"com.example.*"})
public class DemoApplication {

	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(DemoApplication.class, args);
	}
}

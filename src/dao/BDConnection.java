package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class BDConnection {
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		final String URL = "jdbc:postgresql://localhost:5432/drinks";
		final String USER = "lucas";
		final String PASSWORD = "admin";
		final String DRIVER = "org.postgresql.Driver"; 
		
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return conn;
	}
	
}

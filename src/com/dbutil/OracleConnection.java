package com.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleConnection {
	private static Connection connection;
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		if(connection == null || connection.isClosed()) {
			Class.forName("oracle.jdbc.OracleDriver");

//			System.out.println("Driver Loaded");
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			String username = "java";
			String password = "java";
			
			connection = DriverManager.getConnection(url, username, password);
//			System.out.println("Connection Successful");
		}
		return connection;
	}
	
	public static void closeConnection() throws SQLException {
		connection.close();
//		System.out.println("Connection closed");
	}
}

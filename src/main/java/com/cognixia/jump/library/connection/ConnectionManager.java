package com.cognixia.jump.library.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {

	private static Connection connection; // will be null at moment

	private static void makeConnection() {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Registered Driver");
			
			String url;
			String username;
			String password;
			
			try
			{
				Properties props = new Properties();
				
				props.load( new FileInputStream("resources/config.properties") );
				
				url = props.getProperty("url");
				username = props.getProperty("username");
				password = props.getProperty("password");
			}
			catch(FileNotFoundException e)
			{
				url = "jdbc:mysql://localhost:3306/library?serverTimezone=EST5EDT";
				username = "root";
				password = "Root@123";
			}
			
			

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connected.");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();

		} catch (SQLException e) {

			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static Connection getConnection() {

		if (connection == null) {
			makeConnection();
		}

		return connection;
	}

	public static void main(String[] args) {

		Connection conn = BetterConnectionManager.getConnection();

		// Connection other = BetterConnectionManager.getConnection();

		// work with connection manipulating database

		try {
			conn.close();
			System.out.println("Connection closed");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

package com.cognixia.jump.library.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	private static Connection connect; // will be null atm

	private static void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Registered Driver");

			Properties props = new Properties();
			props.load(new FileInputStream("resources/config.properties"));

			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");

			connect = DriverManager.getConnection(url, username, password);
			System.out.println("Connected");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * this just checks if the singleton object has been connected
	 * 
	 * @return Connection object
	 */
	public static Connection getConnection() {
		if (connect == null) {
			makeConnection();
		}
		return connect;
	}

}

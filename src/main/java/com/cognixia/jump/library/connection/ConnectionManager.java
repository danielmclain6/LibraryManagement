package com.cognixia.jump.library.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
	// where is the path/connection we are going to
	private static final String URL = "jdbc:mysql://localhost:3306/library?serverTimezone=EST5EDT"; // fix me!!
	private static final String USERNAME = "root";
	private static final String PASSWORDWindows = "root"; // Windows : root, Mac : Root@123
	private static final String PASSWORDMAC = "Root@123"; 
	

	// singleton example
	private static Connection connect; // will be null atm

	private static void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Registered Driver");
			System.out.println("NEWWWW");
			connect = DriverManager.getConnection(URL, USERNAME, PASSWORDMAC);
			System.out.println("Connected");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("trying alt creds");
			try {
				connect = DriverManager.getConnection(URL, USERNAME, PASSWORDWindows);
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Could not connect to with either creds");
			}
		}
	}

	/**
	 * this just checks if the singleton object has been connected
	 * @return Connection object
	 */
	public static Connection getConnection() {
		if(connect == null) {
			makeConnection();
		}
		return connect;
	}

}

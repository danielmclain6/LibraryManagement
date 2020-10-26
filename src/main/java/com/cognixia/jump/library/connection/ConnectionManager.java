package com.cognixia.jump.library.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	// where is the path/connection we are going to
	private static final String URLWindows = "jdbc:mysql://localhost:3306/library";
	private static final String URLMAC = "";
	private static final String USERNAME = "root";
	private static final String PASSWORDWindows = "root"; // Windows : root, Mac : Root@123
	private static final String PASSWORDMac = ""; // Windows : root, Mac : Root@123
	

	// singleton example
	private static Connection connect; // will be null atm

	private static void makeConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("Registered Driver");
			System.out.println("NEWWWW");

			connect = DriverManager.getConnection(URLWindows, USERNAME, PASSWORDWindows);
			System.out.println("Connected");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
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

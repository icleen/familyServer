package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	
	static Connection getConnection() {
		Connection connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
	}
	
	public static void initializeTable() {
		Connection connection = getConnection();
		Statement statem = null;
		try {
			statem = connection.createStatement();
		} catch (SQLException e) {
			System.out.println("Could not get a statement from the connection " + e.getMessage());
			return;
		}
		
//		add table Users
		try {
			statem.executeUpdate("drop table if exists Users");
			statem.executeUpdate("create table Users (userId INTEGER PRIMARY KEY, personId INTEGER, "
					+ "userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table Users: " + e.getMessage());
			return;
		} 
		
//		add table People
		try {
			statem.executeUpdate("drop table if exists People"); 
			statem.executeUpdate("create table People (personId INTEGER PRIMARY KEY, userId INTEGER, firstName TEXT, lastName TEXT, gender TEXT, "
					+ "father INTEGER, mother INTEGER, spouse INTEGER);");
		} catch (SQLException e) {
			System.out.println("Could not create table People: " + e.getMessage());
			return;
		}
		
//		add table Events
		try {
			statem.executeUpdate("drop table if exists Events"); 
			statem.executeUpdate("create table Events (eventId INTEGER PRIMARY KEY, descendantId INTEGER, personId INTEGER, type TEXT, "
					+ "country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table Events: " + e.getMessage());
			return;
		}
		
//		add table AuthCodes
		try {
			statem.executeUpdate("drop table if exists AuthCodes"); 
			statem.executeUpdate("create table AuthCodes (userName TEXT, password TEXT, authCode TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table AuthCodes: " + e.getMessage());
			return;
		}
	}

}

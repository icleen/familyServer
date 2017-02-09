package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class RunOnceOnly {
	
	public static void main(String args[]) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite:database.db");
			
			initUsers(connect);
			initPeople(connect);
			initEvents(connect);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void initUsers(Connection connection) {
		
		try {
			Statement statem = connection.createStatement();
			statem.executeUpdate("drop table if exists Users"); 
			statem.executeUpdate("create table Users (userId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, "
					+ "spouse INTEGER, email TEXT, userName TEXT, password TEXT);");
		} catch (SQLException e) {
			System.err.println("Could not initialize Users");
			e.printStackTrace();
		}
	}
	
	private static void initPeople(Connection connection) {
		try {
			Statement statem = connection.createStatement();
			statem.executeUpdate("drop table if exists People"); 
			statem.executeUpdate("create table People (personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, "
					+ "descendant INTEGER, father INTEGER, mother INTEGER, spouse INTEGER);");
			
		} catch (SQLException e) {
			System.err.println("Could not initialize People");
			e.printStackTrace();
		}
	}
	
	private static void initEvents(Connection connection) {
		try {
			Statement statem = connection.createStatement();
			statem.executeUpdate("drop table if exists Events"); 
			statem.executeUpdate("create table Events (eventId INTEGER PRIMARY KEY, descendantId INTEGER, personId INTEGER, type TEXT, "
					+ "country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT);");
			
		} catch (SQLException e) {
			System.err.println("Could not initialize Events");
			e.printStackTrace();
		}
	}

}

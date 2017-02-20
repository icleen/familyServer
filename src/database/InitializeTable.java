package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeTable {
	
	public static void main(String args[]) {
		try {
			init();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void init() throws SQLException {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite:database.db");
			
			initUsers(connect);
			initPeople(connect);
			initEvents(connect);
			initAuthCodes(connect);
			
			connect.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void init(Connection connect) throws SQLException {
		initUsers(connect);
		initPeople(connect);
		initEvents(connect);
		initAuthCodes(connect);
	}
	
	private static void initUsers(Connection connection) throws SQLException {
		
		Statement statem = connection.createStatement();
		statem.executeUpdate("drop table if exists Users"); 
		statem.executeUpdate("create table Users (userId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, "
				+ "spouse INTEGER, email TEXT, userName TEXT, password TEXT);");
	}
	
	private static void initPeople(Connection connection) throws SQLException {
		
		Statement statem = connection.createStatement();
		statem.executeUpdate("drop table if exists People"); 
		statem.executeUpdate("create table People (personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, "
				+ "userId INTEGER, father INTEGER, mother INTEGER, spouse INTEGER);");
	}
	
	private static void initEvents(Connection connection) throws SQLException {
		
		Statement statem = connection.createStatement();
		statem.executeUpdate("drop table if exists Events"); 
		statem.executeUpdate("create table Events (eventId INTEGER PRIMARY KEY, descendantId INTEGER, personId INTEGER, type TEXT, "
				+ "country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT);");
	}
	
	private static void initAuthCodes(Connection connection) throws SQLException {
		
		Statement statem = connection.createStatement();
		statem.executeUpdate("drop table if exists AuthCodes"); 
		statem.executeUpdate("create table AuthCodes (userId INTEGER PRIMARY KEY, userName, authCode TEXT);");
		
	}

}

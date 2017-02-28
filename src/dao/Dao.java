package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Dao {
	
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
			statem.executeUpdate("create table Users (userId TEXT, personId TEXT, "
					+ "userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table Users: " + e.getMessage());
			return;
		} 
		
//		add table People
		try {
			statem.executeUpdate("drop table if exists People"); 
			statem.executeUpdate("create table People (personId TEXT, userName TEXT, firstName TEXT, lastName TEXT, gender TEXT, "
					+ "father TEXT, mother TEXT, spouse TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table People: " + e.getMessage());
			return;
		}
		
//		add table Events
		try {
			statem.executeUpdate("drop table if exists Events"); 
			statem.executeUpdate("create table Events (eventId TEXT, userName TEXT, personId TEXT, type TEXT, "
					+ "country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table Events: " + e.getMessage());
			return;
		}
		
//		add table AuthCodes
		try {
			statem.executeUpdate("drop table if exists AuthCodes"); 
			statem.executeUpdate("create table AuthCodes (userName TEXT, password TEXT, authCode TEXT, userId TEXT);");
		} catch (SQLException e) {
			System.out.println("Could not create table AuthCodes: " + e.getMessage());
			return;
		}
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the 'table' table at 'where' column for 'who' event
	 * @param table a String specifying what table you are inserting into
	 * @param userId a String specifying what User you are inserting into (ie which row)
	 * @param column a String specifying the column
	 * @param change a String specifying what you want to change in that row
	 * @return a String informing the user of how the operation went
	 * @throws SQLException 
	 */
	public String insert(String table, String userId, String column, String change) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		Statement state = connection.createStatement();
		state.executeUpdate("update " + table + " set " + column + "='" + change + "' where userId=\"" + userId + "\";");
//		"update Person set Id = '7', Name = 'Suzy' where Name = 'Susan';"
        
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
        
        return "The object was edited in the table " + table;
	}
	
	/**
	 * delete the row of information corresponding to the userId given
	 * @param userName the userName of the person who's info you're deleting
	 * @param table which table you want to delete from (People, Events, Users, or AuthCodes)
	 * @throws SQLException if unable to execute the deletion operation throws this error
	 */
	public void delete(String userName, String table) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		Statement state = connection.createStatement();
		state.executeUpdate("delete from " + table + " where userName=\"" + userName + "\";");
        
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
        
	}

}

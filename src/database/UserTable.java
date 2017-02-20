package database;

import java.sql.Connection;
import java.sql.SQLException;

import model.LoginResponse;
import model.User;

public class UserTable {
	
	/**
	 * gives back the user specified by the id
	 * @param userId
	 * @return an object of User
	 * @throws SQLException 
	 */
	public static User pull(String userId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the users in the database
	 * @return an array of User objects
	 * @throws SQLException 
	 */
	public static User[] pull(Connection connection) throws SQLException {
		return null;
	}
	
	public static LoginResponse getAuthCode(Connection connection) throws SQLException {
		
		return null;
	}
	
}

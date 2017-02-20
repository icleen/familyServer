package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.User;

public class UserDao {
	
	/**
	 * gives back the user specified by the id
	 * @param userId a String containing the user's id
	 * @return an object of User
	 * @throws SQLException 
	 */
	public static User getUser(String userId) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the users in the database
	 * @return an array of User objects
	 * @throws SQLException 
	 */
	public static User[] getUsers() throws SQLException {
		return null;
	}
	
	/**
	 * puts the user specified into the table
	 * @param user User object
	 * @return a String telling how the operation went
	 * @throws SQLException 
	 */
	public static String addUser(User user)  throws SQLException {
		return null;
	}
	
	/**
	 * adds the specified users into the table
	 * @param users an array of User objects
	 * @return a String telling how the operation went
	 * @throws SQLException
	 */
	public static String addUsers(User[] users) throws SQLException {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the User table at 'where' column for 'who' event
	 * @param who a String specifying what User you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public static String insert(String who, String where, String what) {
		return null;
	}

}

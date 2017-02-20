package database;

import java.sql.Connection;
import java.sql.SQLException;

import model.Person;

public class PeopleTable {
	
	/**
	 * gives back the person specified by the id
	 * @param authToken a String containing an authorization token
	 * @param personId the id of the person you want
	 * @param connection the connection to the database
	 * @return an object of Person
	 * @throws SQLException
	 */
	public static Person pull(String authToken, String personId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the people in the database connected to the current user
	 * @param authToken a String containing an authorization token
	 * @param connection the connection to the database
	 * @return an array of Person objects
	 * @throws SQLException
	 */
	public static Person[] pull(String authToken, Connection connection) throws SQLException {
		return null;
	}

}

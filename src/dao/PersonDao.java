package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Person;

public class PersonDao {
	
	/**
	 * gives back the person specified by the id
	 * @param authToken a String containing an authorization token
	 * @param personId a String containing the person's id
	 * @return an object of Person
	 * @throws SQLException
	 */
	public static Person getPerson(String authToken, String personId) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the people in the database connected to the current user
	 * @param authToken a String containing an authorization token
	 * @return an array of Person objects
	 * @throws SQLException
	 */
	public static Person[] getPeople(String authToken) throws SQLException {
		return null;
	}
	
	/**
	 * adds a person's data into the database
	 * @param person an object of type Person
	 * @return a String object describing the result of the operation
	 */
	public static String addPerson(Person person) {
		return null;
	}
	
	/**
	 * adds people to the database
	 * @param people an array of Person objects
	 * @return a String object describing the result of the operation
	 */
	public static String addPeople(Person[] people) {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the Person table at 'where' column for 'who' event
	 * @param who a String specifying what Person you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public static String insert(String who, String where, String what) {
		return null;
	}

}

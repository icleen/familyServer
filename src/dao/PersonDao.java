package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Person;

public class PersonDao {
	
	/**
	 * gives back the person specified by the id
	 * @param personId a String containing the person's id
	 * @return an object of Person
	 * @throws SQLException
	 */
	public Person getPerson(String personId) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the people in the database connected to the current user
	 * @param userId the id of the user you want the people connected to
	 * @return an array of Person objects
	 * @throws SQLException
	 */
	public Person[] getPeople(String userId) throws SQLException {
		return null;
	}
	
	/**
	 * adds a person's data into the database
	 * @param person an object of type Person
	 * @return a String object describing the result of the operation
	 */
	public String addPerson(Person person) throws SQLException {
		return null;
	}
	
	/**
	 * adds people to the database
	 * @param people an array of Person objects
	 * @return a String object describing the result of the operation
	 */
	public String addPeople(Person[] people) throws SQLException {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the Person table at 'where' column for 'who' event
	 * @param who a String specifying what Person you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public String insert(String who, String where, String what) throws SQLException {
		return null;
	}

}

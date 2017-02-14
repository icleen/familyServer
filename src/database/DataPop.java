package database;

import java.sql.Connection;
import java.sql.SQLException;

import model.Event;
import model.Person;
import model.User;

public class DataPop {
	
	/**
	 * gives back the user specified by the id
	 * @param userId
	 * @return an object of User
	 * @throws SQLException 
	 */
	public static User popUser(String userId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the users in the database
	 * @return an array of User objects
	 * @throws SQLException 
	 */
	public static User[] popUsers(Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * gives back the person specified by the id
	 * @param personId
	 * @return an object of Person
	 * @throws SQLException 
	 */
	public static Person popPerson(String personId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the people in the database
	 * @return an array of Person objects
	 */
	public static Person[] popPeople(Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * gives back the event specified by the id
	 * @param eventId
	 * @return an object of Event
	 * @throws SQLException 
	 */
	public static Event popEvent(String eventId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the events in the database
	 * @return an array of Event objects
	 * @throws SQLException 
	 */
	public static Event[] popEvents(Connection connection) throws SQLException {
		return null;
	}
	
}

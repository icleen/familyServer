package database;

import java.sql.Connection;

import model.Event;
import model.Person;
import model.User;

public class DataPop {
	
	private static Connection connection = DataManager.initialize();

	
	/**
	 * gives back the user specified by the id
	 * @param userId
	 * @return an object of User
	 */
	public static User popUser(String userId) {
		return null;
	}
	
	/**
	 * returns an array version of the users in the database
	 * @return an array of User objects
	 */
	public static User[] popUsers() {
		return null;
	}
	
	/**
	 * gives back the person specified by the id
	 * @param personId
	 * @return an object of Person
	 */
	public static Person popPerson(String personId) {
		return null;
	}
	
	/**
	 * returns an array version of the people in the database
	 * @return an array of Person objects
	 */
	public static Person[] popPeople() {
		return null;
	}
	
	/**
	 * gives back the event specified by the id
	 * @param eventId
	 * @return an object of Event
	 */
	public static Event popEvent(String eventId) {
		return null;
	}
	
	/**
	 * returns an array version of the events in the database
	 * @return an array of Event objects
	 */
	public static Event[] popEvents() {
		return null;
	}
	
}

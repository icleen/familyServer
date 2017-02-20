package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Event;

public class EventDao {
	
	/**
	 * gives back the event specified by the id
	 * @param authToken the code allowing access to the database
	 * @param eventId a String containing the event's id
	 * @return an Event object
	 * @throws SQLException
	 */
	public static Event getEvent(String authToken, String eventId) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the events in the database that are connected to the current user (given by his/her authToken
	 * @param authToken the code allowing access to the database
	 * @return an array of Event objects
	 * @throws SQLException
	 */
	public static Event[] getEvents(String authToken) throws SQLException {
		return null;
	}
	
	/**
	 * adds the specified event to the database
	 * @param event an object of Event
	 * @return a String informing of the operation result
	 */
	public static String addEvent(Event event) {
		return null;
	}
	
	/**
	 * adds the specified events into the Event table
	 * @param events an array of Event objects
	 * @return a String informing of the operation result
	 */
	public static String addEvents(Event[] events) {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the event table at 'where' column for 'who' event
	 * @param who a String specifying what event you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public static String insert(String who, String where, String what) {
		return null;
	}

}

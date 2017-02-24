package dao;

import java.sql.Connection;
import java.sql.SQLException;

import model.Event;

public class EventDao extends Dao {
	
	/**
	 * gives back the event specified by the id
	 * @param eventId a String containing the event's id
	 * @return an Event object
	 * @throws SQLException
	 */
	public Event getEvent(String eventId) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the events in the database that are connected to the current user (given by his/her authToken
	 * @param userId the id of the user you want the events for
	 * @return an array of Event objects
	 * @throws SQLException
	 */
	public Event[] getEvents(String userId) throws SQLException {
		return null;
	}
	
	/**
	 * adds the specified event to the database
	 * @param event an object of Event
	 * @return a String informing of the operation result
	 */
	public String addEvent(Event event) throws SQLException {
		return null;
	}
	
	/**
	 * adds the specified events into the Event table
	 * @param events an array of Event objects
	 * @return a String informing of the operation result
	 */
	public String addEvents(Event[] events) throws SQLException {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the event table at 'where' column for 'who' event
	 * @param who a String specifying what event you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public String insert(String who, String where, String what) throws SQLException {
		return null;
	}

}

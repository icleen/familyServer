package database;

import java.sql.Connection;
import java.sql.SQLException;

import model.Event;

public class EventTable {
	
	/**
	 * gives back the event specified by the id
	 * @param authToken
	 * @param eventId
	 * @param connection
	 * @return
	 * @throws SQLException
	 */
	public static Event pull(String authToken, String eventId, Connection connection) throws SQLException {
		return null;
	}
	
	/**
	 * returns an array version of the events in the database that are connected to the current user (given by his/her authToken
	 * @param authToke
	 * @param connection
	 * @return an array of Event objects
	 * @throws SQLException
	 */
	public static Event[] pull(String authToken, Connection connection) throws SQLException {
		return null;
	}

}

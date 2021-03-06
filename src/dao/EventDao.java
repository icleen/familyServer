package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Event;

public class EventDao extends Dao {

	/**
	 * gives back the event specified by the id
	 * 
	 * @param eventId
	 *            a String containing the event's id
	 * @return an Event object
	 * @throws SQLException
	 */
	public Event getEvent(String eventId) throws SQLException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
		ResultSet rs = null;
		Event response = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from Events where eventId=\"" + eventId + "\";");

		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}		
		// eventId TEXT PRIMARY KEY, userName TEXT, personId TEXT, type TEXT, 
		// country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
		String userName = rs.getString(2);
		String personId = rs.getString(3);
		response = new Event(eventId, userName, personId);
		response.setEventType(rs.getString(4));
		response.setCountry(rs.getString(5));
		response.setCity(rs.getString(6));
		response.setYear(rs.getString(7));
		response.setLatitude(rs.getString(8));
		response.setLongitude(rs.getString(9));

		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}

		return response;
	}

	/**
	 * returns an array version of the events in the database that are connected
	 * to the current user (given by his/her authToken
	 * 
	 * @param userName the id of the user you want the events for
	 * @return an array of Event objects
	 * @throws SQLException
	 */
	public ArrayList<Event> getEvents(String userName) throws SQLException {
		Connection connection = getConnection();
		if (connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
		ResultSet rs = null;
		ArrayList<Event> events = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from Events where userName=\"" + userName + "\";");

		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}
		// eventId TEXT PRIMARY KEY, userName TEXT, personId TEXT, type TEXT, 
		// country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
		String eventId = null;
		String personId = null;
		Event response = null;
		events = new ArrayList<>();
		while (rs.next()) {
			eventId = rs.getString(1);
			personId = rs.getString(3);
			response = new Event(eventId, userName, personId);
			response.setEventType(rs.getString(4));
			response.setCountry(rs.getString(5));
			response.setCity(rs.getString(6));
			response.setYear(rs.getString(7));
			response.setLatitude(rs.getString(8));
			response.setLongitude(rs.getString(9));
			events.add(response);
		}

		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		if (events.size() == 0) {
			return null;
		}
		return events;
	}

	/**
	 * adds the specified event to the database
	 * 
	 * @param event
	 *            an object of Event
	 * @return a String informing of the operation result
	 */
	public String addEvent(Event event) throws SQLException {
		Connection connection = Dao.getConnection();
		if (connection == null) {
			throw new NullPointerException();
		}

		PreparedStatement prep = connection.prepareStatement("insert into Events values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
		// eventId TEXT PRIMARY KEY, userName TEXT, personId TEXT, type TEXT, 
		// country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
		if(event.getEventId() != null) {
			prep.setString(1, event.getEventId());
		}
		prep.setString(2, event.getDescendant());
		prep.setString(3, event.getPersonId());
		prep.setString(4, event.getEventType());
		prep.setString(5, event.getCountry());
		prep.setString(6, event.getCity());
		prep.setString(7, event.getYear());
		prep.setString(8, event.getLatitude());
		prep.setString(9, event.getLongitude());
		prep.addBatch();

		connection.setAutoCommit(false);
		prep.executeBatch();
		connection.setAutoCommit(true);

		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}

		return "The person was added to the table";
	}

	/**
	 * adds the specified events into the Event table
	 * 
	 * @param objects
	 *            an array of Event objects
	 * @return a String informing of the operation result
	 */
	public String addEvents(Object[] objects) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into Events values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
		for(int i = 0; i < objects.length; i++) {
			// eventId TEXT PRIMARY KEY, userName TEXT, personId TEXT, type TEXT, 
			// country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
			Event event = (Event) objects[i];
			if(!event.isValid()) {
				return "The event " + event.getEventType() + " had invalid data!";
			}
			if(event.getEventId() != null) {
				prep.setString(1, event.getEventId());
			}
			prep.setString(2, event.getDescendant());
			prep.setString(3, event.getPersonId());
			prep.setString(4, event.getEventType());
			prep.setString(5, event.getCountry());
			prep.setString(6, event.getCity());
			prep.setString(7, event.getYear());
			prep.setString(8, event.getLatitude());
			prep.setString(9, event.getLongitude());
			prep.addBatch();
		}
		
        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
        
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		return null;
	}

}

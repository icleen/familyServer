package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Event;
import model.Person;
import model.User;

public class DataPush {
	
private static Connection connection = DataManager.initialize();
	
	/**
	 * puts the user object specified into the table
	 * @param user User object
	 */
	public static void pushUser(User user) {
		try {
			PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
//			userId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, spouse INTEGER, email TEXT, userName TEXT, password TEXT
			prep.setInt(1, user.getId());
			prep.setString(2, user.getFirstName());
			prep.setString(3, user.getLastName());
			prep.setString(4, user.getGender());
			if(user.getSpouse() == null) {
				prep.setInt(5, -1);
			}else {
				prep.setInt(5, user.getSpouse().getId());
			}
			prep.setString(6, user.getEmail());
			prep.setString(7, user.getUserName());
			prep.setString(8, user.getPassword());
			prep.addBatch();
			
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
			
            recursivePush(user.getFather());
            recursivePush(user.getMother());
            
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void recursivePush(Person person) {
		if(person == null) {
			return;
		}
		try {
			PreparedStatement prep = connection.prepareStatement("insert into People values(?, ?, ?, ?, ?, ?, ?, ?);");
//			personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, descendant INTEGER, father INTEGER, mother INTEGER, spouse INTEGER
			prep.setInt(1, person.getId());
			prep.setString(2, person.getFirstName());
			prep.setString(3, person.getLastName());
			prep.setString(4, person.getGender());
			prep.setInt(5, person.getDescendant().getId());
			if(person.getFather() == null) {
				prep.setInt(6, -1);
			}else {
				prep.setInt(6, person.getFather().getId());
			}
			if(person.getMother() == null) {
				prep.setInt(7, -1);
			}else {
				prep.setInt(7, person.getMother().getId());
			}			
			if(person.getSpouse() == null) {
				prep.setInt(8, -1);
			}else {
				prep.setInt(8, person.getSpouse().getId());
			}
			prep.addBatch();
			
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
            
            recursivePush(person.getFather());
            recursivePush(person.getMother());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	/**
	 * puts the person object specified into the table
	 * @param person object of Person
	 */
	public static void pushPerson(Person person) {
		try {
			PreparedStatement prep = connection.prepareStatement("insert into People values(?, ?, ?, ?, ?, ?, ?, ?);");
//			personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, descendant INTEGER, father INTEGER, mother INTEGER, spouse INTEGER
			prep.setInt(1, person.getId());
			prep.setString(2, person.getFirstName());
			prep.setString(3, person.getLastName());
			prep.setString(4, person.getGender());
			prep.setInt(5, person.getDescendant().getId());
			prep.setInt(6, person.getFather().getId());
			prep.setInt(7, person.getMother().getId());
			if(person.getSpouse() == null) {
				prep.setInt(8, -1);
			}else {
				prep.setInt(8, person.getId());
			}
			prep.addBatch();
			
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * puts the event object specified into the table
	 * @param event Event object
	 */
	public static void pushEvent(Event event) {
		try {
			PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
//			eventId INTEGER PRIMARY KEY, descendantId INTEGER, personId INTEGER, type TEXT, 
//			country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
			prep.setInt(1, event.getId());
//			prep.setString(2, event.getFirstName());
//			prep.setString(3, event.getLastName());
//			prep.setString(4, event.getGender());
			
			prep.addBatch();
			
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

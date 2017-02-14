package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Event;
import model.LoginOutput;
import model.Person;
import model.RegisterInput;
import model.User;

public class DataPush {
	
	/**
	 * puts the user object specified into the table
	 * @param user User object
	 * @throws SQLException 
	 */
	public static LoginOutput pushUser(User user, Connection connection) throws SQLException {
		PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
//		userId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, spouse INTEGER, email TEXT, userName TEXT, password TEXT
//		prep.setInt(1, (Integer) null);
		prep.setString(2, user.getFirstName());
		prep.setString(3, user.getLastName());
		prep.setString(4, user.getGender());
		prep.setInt(5, -1);
		prep.setString(6, user.getEmail());
		prep.setString(7, user.getUserName());
		prep.setString(8, user.getPassword());
		prep.addBatch();
		
        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
        
        return pushAuthCode(user, connection);
		
//        recursivePush(user.getFather());
//        recursivePush(user.getMother());
	}
	
//	private static void recursivePush(Person person) throws SQLException {
//		if(person == null) {
//			return;
//		}
//		PreparedStatement prep = connection.prepareStatement("insert into People values(?, ?, ?, ?, ?, ?, ?, ?);");
////		personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, descendant INTEGER, father INTEGER, mother INTEGER, spouse INTEGER
//		prep.setInt(1, person.getId());
//		prep.setString(2, person.getFirstName());
//		prep.setString(3, person.getLastName());
//		prep.setString(4, person.getGender());
//		prep.setInt(5, person.getDescendant().getId());
//		if(person.getFather() == null) {
//			prep.setInt(6, -1);
//		}else {
//			prep.setInt(6, person.getFather().getId());
//		}
//		if(person.getMother() == null) {
//			prep.setInt(7, -1);
//		}else {
//			prep.setInt(7, person.getMother().getId());
//		}			
//		if(person.getSpouse() == null) {
//			prep.setInt(8, -1);
//		}else {
//			prep.setInt(8, person.getSpouse().getId());
//		}
//		prep.addBatch();
//		
//        connection.setAutoCommit(false);
//        prep.executeBatch();
//        connection.setAutoCommit(true);
//        
//        recursivePush(person.getFather());
//        recursivePush(person.getMother());
//        
//	}
	
	/**
	 * puts the person object specified into the table
	 * @param person object of Person
	 * @throws SQLException 
	 */
	public static void pushPerson(Person person, Connection connection) throws SQLException {
		PreparedStatement prep = connection.prepareStatement("insert into People values(?, ?, ?, ?, ?, ?, ?, ?);");
//		personId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, descendant INTEGER, father INTEGER, mother INTEGER, spouse INTEGER
		prep.setInt(1, person.getId());
		prep.setString(2, person.getFirstName());
		prep.setString(3, person.getLastName());
		prep.setString(4, person.getGender());
		prep.setInt(5, person.getDescendant().getId());
		if(person.getSpouse() == null) {
			prep.setInt(6, -1);
		}else {
			prep.setInt(6, person.getFather().getId());
		}
		
		if(person.getSpouse() == null) {
			prep.setInt(7, -1);
		}else {
			prep.setInt(7, person.getMother().getId());
		}
		
		if(person.getSpouse() == null) {
			prep.setInt(8, -1);
		}else {
			prep.setInt(8, person.getId());
		}
		prep.addBatch();
		
        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
	}
	
	/**
	 * puts the event object specified into the table
	 * @param event Event object
	 * @throws SQLException 
	 */
	public static void pushEvent(Event event, Connection connection) throws SQLException {
		PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?, ?);");
//		eventId INTEGER PRIMARY KEY, descendantId INTEGER, personId INTEGER, type TEXT, 
//		country TEXT, city TEXT, year TEXT, latitude TEXT, longitude TEXT
		prep.setInt(1, event.getId());
//		prep.setString(2, event.getFirstName());
//		prep.setString(3, event.getLastName());
//		prep.setString(4, event.getGender());
		
		prep.addBatch();
		
        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
	}
	
	public static LoginOutput pushAuthCode(User user, Connection connection) throws SQLException {
		PreparedStatement prep = connection.prepareStatement("insert into AuthCodes values(?, ?, ?);");
//		userId INTEGER PRIMARY KEY, userName TEXT, authCode TEXT
		StringBuilder authCode = new StringBuilder(user.getId() + user.getUserName() + user.getId());
		authCode.setCharAt(authCode.length()/2, 'z');
		authCode.insert(0, "ba");
		authCode.insert(authCode.length(), "ab");
//		prep.setInt(1, (Integer) null);
		prep.setString(2, user.getUserName());
		prep.setString(3, authCode.toString());
		
		prep.addBatch();
		
        connection.setAutoCommit(false);
        prep.executeBatch();
        connection.setAutoCommit(true);
        
        Statement statem = connection.createStatement();
        ResultSet rs = statem.executeQuery("select * from AuthCodes where userName=\""+ user.getUserName() + "\";");
        LoginOutput output = new LoginOutput();
        output.setAuthToken(authCode.toString());
        output.setPersonId(rs.getString(1));
        output.setUserName(user.getUserName());
        return output;
	}

}

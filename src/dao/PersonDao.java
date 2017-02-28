package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Person;

public class PersonDao extends Dao {
	
	private static int id = 0;
	
	/**
	 * gives back the person specified by the id
	 * @param personId a String containing the person's id
	 * @return an object of Person
	 * @throws SQLException
	 */
	public Person getPerson(String personId) throws SQLException {
		Connection connection = getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
        ResultSet rs = null;
        Person response = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from People where personId=\""+ personId + "\";");
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the person info failed!");
			e.printStackTrace();
		}
//		personId INTEGER PRIMARY KEY, userName TEXT, firstName TEXT, lastName TEXT, gender TEXT, father INTEGER, mother INTEGER, spouse INTEGER
		String userName = rs.getString(2);
		response = new Person(personId, userName);
		response.setFirstName(rs.getString(3));
		response.setLastName(rs.getString(4));
		response.setGender(rs.getString(5));
		response.setFather(rs.getString(6));
		response.setMother(rs.getString(7));
		response.setSpouse(rs.getString(8));
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * returns an array version of the people in the database connected to the current user
	 * @param userName the username of the user you want the people connected to
	 * @return an array of Person objects
	 * @throws SQLException
	 */
	public ArrayList<Person> getPeople(String userName) throws SQLException {
		Connection connection = getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
        ResultSet rs = null;
        ArrayList<Person> people = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from People where userName=\""+ userName + "\";");
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the person info failed!");
			e.printStackTrace();
		}
//		personId INTEGER PRIMARY KEY, userName TEXT, firstName TEXT, lastName TEXT, gender TEXT, father INTEGER, mother INTEGER, spouse INTEGER
		Person response = null;
		String personId = null;
		people = new ArrayList<>();
		while(rs.next()) {
			personId = rs.getString(1);
			response = new Person(personId, userName);
			response.setFirstName(rs.getString(3));
			response.setLastName(rs.getString(4));
			response.setGender(rs.getString(5));
			response.setFather(rs.getString(6));
			response.setMother(rs.getString(7));
			response.setSpouse(rs.getString(8));
			people.add(response);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		if(people.size() == 0) {
			return null;
		}
		return people;
	}
	
	/**
	 * adds a person's data into the database
	 * @param person an object of type Person
	 * @return a String object describing the result of the operation; if it went well it returns the new id
	 */
	public String addPerson(Person person) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into People values(?, ?, ?, ?, ?, ?, ?, ?);");
//		personId INTEGER PRIMARY KEY, userName INTEGER, firstName TEXT, lastName TEXT, gender TEXT, father INTEGER, mother INTEGER, spouse INTEGER
		prep.setString(1, person.getId());
		prep.setString(2, person.getUserName());
		prep.setString(3, person.getFirstName());
		prep.setString(4, person.getLastName());
		prep.setString(5, person.getGender());
		prep.setString(6, person.getFather());
		prep.setString(7, person.getMother());
		prep.setString(8, person.getSpouse());
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
	 * adds people to the database
	 * @param people an array of Person objects
	 * @return a String object describing the result of the operation
	 */
	public String addPeople(Person[] people) throws SQLException {
		return null;
	}

}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Event;
import model.Person;
import model.RegisterInput;
import model.User;
import model.LoginResponse;

public class DataManager {
	
	String CLEAR_SUCCESS = "Clear Succeeded";
	String CLEAR_FAIL = "Clear failed: ";
	
//	private Connection connection = initialize();

	/**
	 * creates the user in the database and generates information for that user's past 4 generations
	 * @param user an object of type User
	 * @return an object of loginOutput
	 */
	public LoginResponse register(User user) {
		Connection connection = initialize();
		LoginResponse output = new LoginResponse();
		try {
			output = DataPush.pushUser(user, connection);
		} catch (SQLException e) {
			output.setErrorMessage("Register of user failed!");
		}
//		try to close the connection
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		return output;
	}
	
	/**
	 * checks to make sure a user exists and returns an object containing the authorization code
	 * @param userName
	 * @param password
	 * @return and object of LoginOutput
	 */
	public LoginResponse userLogin(String userName, String password) {
		Connection connection = initialize();
		Statement statem;
        ResultSet rs;
        LoginResponse output = new LoginResponse();
		try {
			statem = connection.createStatement();
			rs = statem.executeQuery("select * from AuthCodes where userName=\""+ userName + "\";");
			
			try {
		        output.setPersonId(rs.getString(1));
		        output.setUserName(rs.getString(2));
		        output.setAuthToken(rs.getString(3));
			} catch (SQLException e) {
				output.setErrorMessage("The user password combination does not exits!");
			}
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
        return output;
	}
	
	/**
	 * deletes all data from the database and gives you a message of success or failure
	 * @return a message
	 */
	public String clear() {
		Connection connection = initialize();
		try {
			InitializeTable.init(connection);
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return CLEAR_FAIL;
		}
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		return CLEAR_SUCCESS;
	}
	
	/**
	 * clears any data associated with the specified user and adds the number of generations specified 
	 * to the user's data (the default is 4 generations if nothing is specified)
	 * @param userName a String containing the userName of the user whose info you want
	 * @param generations an int representing how many generations you want to generate
	 * @return a String describing if the operation succeeded or how it failed
	 */
	public String fill(String userName, int generations) {
		Connection connection = initialize();
		
		try {
			FillData.fill(userName, generations, connection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * loads the specified arrays into the database deleting everything that existed previously
	 *  and returns a message of success or failure with the number of people and events loaded
	 * @param users an array of User objects
	 * @param people an array of Person objects
	 * @param events an array of Event objects
	 * @return a message
	 */
	public String load(User[] users, Person[] people, Event[] events) {
		String check = clear();
		if(check.equals(CLEAR_FAIL)) {
			return check;
		}
		Connection connection = initialize();
		String val = LoadData.load(users, people, events, connection);
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		return val;
	}
	
	/**
	 * get all of the people related to the user
	 * @return an array of Person objects, for each of the Persons connected to the user
	 */
	public Person[] getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the person a specific person based on the id
	 * @param id the id of the person you want access to
	 * @return a person object 
	 */
	public Person getPerson(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * gives you all of the events for all the family members of the current user 
	 * @return an array of Event objects
	 */
	public Event[] getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the event with the id
	 * @param id a specific event id
	 * @return an event object
	 */
	public Event getEvent(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * puts all of the information for a certain table on the command line for testing purposes
	 * @param tableName a String with the name of the table wanted
	 */
	public void printTable(String tableName) {
		Connection connection = initialize();
		PrintTable.print(connection, tableName);
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
	}
	
	Connection initialize() {
		Connection connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
	}
	
	static Connection staticInitialize() {
		Connection connect = null;
		try {
			Class.forName("org.sqlite.JDBC");
			connect = DriverManager.getConnection("jdbc:sqlite:database.db");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connect;
	}

}

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
import model.LoginOutput;

public class DataManager {
	
	String CLEAR_SUCCESS = "Clear Succeeded";
	String CLEAR_FAIL = "Clear failed: ";
	
//	private Connection connection = initialize();

	/**
	 * creates the user in the database and generates information for that user's past 4 generations
	 * @param input
	 * @return an object of loginOutput
	 */
	public LoginOutput register(User user) {
		Connection connection = initialize();
		LoginOutput output = new LoginOutput();
		try {
			output = DataPush.pushUser(user, connection);
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
		} catch (SQLException e) {
			output.setErrorMessage("Register of user failed!");
			try {
				connection.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return output;
		}
	}
	
	/**
	 * checks to make sure a user exists and returns an object containing the authorization code
	 * @param userName
	 * @param password
	 * @return and object of LoginOutput
	 */
	public LoginOutput userLogin(String userName, String password) {
		Connection connection = initialize();
		Statement statem;
        ResultSet rs;
        LoginOutput output = new LoginOutput();
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
			// TODO Auto-generated catch block
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CLEAR_SUCCESS;
	}
	
	/**
	 * loads the user's information into the database and returns a message of success or failure with the number of people and events loaded
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
			// TODO Auto-generated catch block
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
	
	public void printTable(String tableName) {
		Connection connection = initialize();
		PrintTable.print(connection, tableName);
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private Connection initialize() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite:database.db");
			
			return connect;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	static Connection staticInitialize() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection connect = DriverManager.getConnection("jdbc:sqlite:database.db");
			
			return connect;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.User;

public class UserDao extends Dao {
	
	/**
	 * gives back the user specified by the id
	 * @param userId a String containing the user's id
	 * @return an object of User
	 * @throws SQLException when the user does not exist
	 */
	public User getUser(String userName) throws SQLException {
		Connection connection = getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
        ResultSet rs = null;
        User response = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users where userName=\""+ userName + "\";");
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}
		
//		userId TEXT PRIMARY KEY, personId TEXT, userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT
		String id = rs.getString(1);
		String personId = rs.getString(2);
		response = new User(id, personId);
		response.setusername(rs.getString(3));
		response.setPassword(rs.getString(4));
		response.setEmail(rs.getString(5));
		response.setfirstname(rs.getString(6));
		response.setlastname(rs.getString(7));
		response.setGender(rs.getString(8));
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		
		return response;
	}
	
	/**
	 * returns an array version of the users in the database
	 * @return an array of User objects
	 * @throws SQLException 
	 */
	public ArrayList<User> getUsers() throws SQLException {
		Connection connection = getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
        ResultSet rs = null;
        ArrayList<User> users = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from Users;");
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}
//		userId TEXT PRIMARY KEY, personId TEXT, userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT
		User response = null;
		String userId = null;
		String personId = null;
		users = new ArrayList<>();
		while(rs.next()) {
			userId = rs.getString(1);
			personId = rs.getString(2);
			response = new User(userId, personId);
			response.setusername(rs.getString(3));
			response.setPassword(rs.getString(4));
			response.setEmail(rs.getString(5));
			response.setfirstname(rs.getString(6));
			response.setlastname(rs.getString(7));
			response.setGender(rs.getString(8));
			users.add(response);
		}
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		if(users.size() == 0) {
			return null;
		}
		return users;
	}
	
	/**
	 * puts the user specified into the table
	 * @param user User object
	 * @return a String telling how the operation went
	 * @throws SQLException
	 */
	public String addUser(User user)  throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
//		userId TEXT PRIMARY KEY, personId TEXT, userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT
		if(user.getId() != null) {
//			System.out.println(user.getId());
			prep.setString( 1, user.getId() );
		}
		prep.setString(2, user.getPersonId());
		prep.setString(3, user.getusername());
		prep.setString(4, user.getPassword());
		prep.setString(5, user.getEmail());
		prep.setString(6, user.getfirstname());
		prep.setString(7, user.getlastname());
		prep.setString(8, user.getGender());
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
        
        return "The user was added to the table";
	}
	
	/**
	 * adds the specified users into the table
	 * @param users an array of User objects
	 * @return a String telling how the operation went
	 * @throws SQLException
	 */
	public String addUsers(Object[] objects) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
		for(int i = 0; i < objects.length; i++) {
			User user = (User) objects[i];
			if(!user.isValid()) {
				return "The user had invalid data!";
			}
//			userId TEXT PRIMARY KEY, personId TEXT, userName TEXT, password TEXT, email TEXT, firstName TEXT, lastName TEXT, gender TEXT
			if(user.getId() != null) {
				prep.setString(1, user.getId());
			}
			prep.setString(2, user.getPersonId());
			prep.setString(3, user.getusername());
			prep.setString(4, user.getPassword());
			prep.setString(5, user.getEmail());
			prep.setString(6, user.getfirstname());
			prep.setString(7, user.getlastname());
			prep.setString(8, user.getGender());
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

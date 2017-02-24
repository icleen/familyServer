package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.AuthToken;

public class AuthDao {
	
	/**
	 * takes in a userName and gives back the authorization token with all of it's information
	 * @param userName a String containing the userName of the person you want
	 * @return an object of AuthToken
	 */
	public AuthToken getAuth(String userName) {
		return null;
	}
	
	/**
	 * adds an authorization token to the database
	 * @param token an object of AuthToken to be added to the database
	 * @return a String describing how the operation went
	 * @throws SQLException throws when the token could not be added
	 */
	public String addAuth(AuthToken token) throws SQLException {
		Connection connection = Connector.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into AuthCodes values(?, ?, ?);");
//		userName TEXT, password TEXT, authCode TEXT
		
		prep.setString(1, token.getUserName());
		prep.setString(2, token.getPassword());
		prep.setString(3, token.getAuthCode());
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
        
        return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the AuthToken table at 'where' column for 'who' event
	 * @param who a String specifying what authToken you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public String insert(String who, String where, String what) {
		return null;
	}

}

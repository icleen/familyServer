package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.AuthToken;

public class AuthDao extends Dao {
	
	/**
	 * takes in a userName and gives back the authorization token with all of it's information
	 * @param userName a String containing the userName of the person you want
	 * @return an object of AuthToken
	 * @throws SQLException throws when the entry matching the given userName is not found
	 */
	public AuthToken getAuth(String userName) throws SQLException {
		Connection connection = getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		Statement statement;
        ResultSet rs = null;
        AuthToken token = null;
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from AuthCodes where userName=\""+ userName + "\";");
			
		} catch (SQLException e) {
			System.err.println("The attempt to get the user info failed!");
			e.printStackTrace();
		}
		
		token = new AuthToken();
		token.setUserName(rs.getString(1));
		token.setPassword(rs.getString(2));
		token.setAuthCode(rs.getString(3));
		token.setUserId(rs.getString(4));
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.err.println("Couldn't close the connection!");
			e.printStackTrace();
		}
		
		return token;
	}
	
	/**
	 * adds an authorization token to the database
	 * @param token an object of AuthToken to be added to the database
	 * @return a String describing how the operation went
	 * @throws SQLException throws when the token could not be added
	 */
	public String addAuth(AuthToken token) throws SQLException {
		Connection connection = Dao.getConnection();
		if(connection == null) {
			throw new NullPointerException();
		}
		
		PreparedStatement prep = connection.prepareStatement("insert into AuthCodes values(?, ?, ?, ?);");
//		userName TEXT, password TEXT, authCode TEXT, userId INTEGER
		
		prep.setString(1, token.getUserName());
		prep.setString(2, token.getPassword());
		prep.setString(3, token.getAuthCode());
		prep.setString(4, token.getUserId());
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
        
        return "The authorization code was added to the table";
	}

}

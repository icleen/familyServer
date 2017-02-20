package dao;

import model.AuthToken;

public class AuthDao {
	
	/**
	 * takes in a userName and gives back the authorization token with all of it's information
	 * @param userName a String containing the userName of the person you want
	 * @return an object of AuthToken
	 */
	public static AuthToken getAuth(String userName) {
		return null;
	}
	
	/**
	 * adds an authorization token to the database
	 * @param token an object of AuthToken to be added to the database
	 * @return a String describing how the operation went
	 */
	public static String addAuth(AuthToken token) {
		return null;
	}
	
	/**
	 * inserts a certain 'what' object (TEXT) into the AuthToken table at 'where' column for 'who' event
	 * @param who a String specifying what authToken you are inserting into
	 * @param where a String specifying the column
	 * @param what a String specifying what you are inserting
	 * @return a String informing the user of how the operation went
	 */
	public static String insert(String who, String where, String what) {
		return null;
	}

}

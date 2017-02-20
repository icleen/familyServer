package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class FillData {
	
	/**
	 * clears any data associated with the specified user and adds the number of generations specified 
	 * to the user's data (the default is 4 generations if nothing is specified)
	 * @param userName the userName of the user you want to add data for
	 * @param generations the amount of generations you want to generate
	 * @param connection connection of the sql database you want to access
	 * @return a String reporting whether the process succeeded or not
	 * @throws SQLException
	 */
	public static String fill(String userName, int generations, Connection connection) throws SQLException {
		String result = "";
		
		Statement statement = connection.createStatement();
		ResultSet userId = statement.executeQuery("select userId from AuthCodes where userName=\"" + userName + "\"");
		String id = "";
		try {
			id = userId.getString(1);
		} catch (SQLException e) {
			return "The user does not exits!";
		}
		
		try {
			statement.executeUpdate("delete from People where userId=\"" + id + "\"");
		} catch (SQLException e) {
			return "The relatives could not be deleted from the People table!";
		}
		
//		generate the data for the user
		
		return result;
	}

}

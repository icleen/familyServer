package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Event;
import model.Person;
import model.User;

public class DataManager {
	
	private static Connection connection = initialize();
	
	public Person loadUser() {
		
		
		return null;
	}
	
	public static void printTable(String tableName) {
		PrintTable.print(connection, tableName);
	}
	
	static Connection initialize() {
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

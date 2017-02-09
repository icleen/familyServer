package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Person;
import model.User;

public class DataManager {
	
	private static Connection connection = initialize();
	
	public static void push(User user) {
		try {
			PreparedStatement prep = connection.prepareStatement("insert into Users values(?, ?, ?, ?, ?, ?, ?, ?);");
//			userId INTEGER PRIMARY KEY, firstName TEXT, lastName TEXT, gender TEXT, spouse INTEGER, email TEXT, userName TEXT, password TEXT
			StringBuilder s = new StringBuilder(user.getId());
			prep.setInt(1, user.getId());
			prep.setString(2, user.getFirstName());
			prep.setString(3, user.getLastName());
			prep.setString(4, user.getGender());
			if(user.getSpouse() == null) {
				prep.setInt(5, -1);
			}else {
				s = new StringBuilder(user.getSpouse().getId());
				prep.setString(5, s.toString());
			}
			prep.setString(6, user.getEmail());
			prep.setString(7, user.getUserName());
			prep.setString(8, user.getPassword());
			prep.addBatch();
			
            connection.setAutoCommit(false);
            prep.executeBatch();
            connection.setAutoCommit(true);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Person load() {
		
		
		return null;
	}
	
	public static void printTable(String tableName) {
		PrintTable.print(connection, tableName);
	}
	
	private static Connection initialize() {
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

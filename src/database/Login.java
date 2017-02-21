//package database;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//import model.LoginResponse;
//
//public class Login {
//	
//	/**
//	 * checks to make sure a user exists and returns an object containing the authorization code
//	 * @param userName
//	 * @param password
//	 * @return and object of LoginOutput
//	 */
//	public LoginResponse userLogin(String userName, String password) {
//		Connection connection = DataManager.staticInitialize();
//		Statement statem;
//        ResultSet rs;
//        LoginResponse output = new LoginResponse();
//		try {
//			statem = connection.createStatement();
//			rs = statem.executeQuery("select * from AuthCodes where userName=\""+ userName + "\";");
//			
//			try {
//		        output.setPersonId(rs.getString(1));
//		        output.setUserName(rs.getString(2));
//		        output.setAuthToken(rs.getString(3));
//			} catch (SQLException e) {
//				output.setErrorMessage("The user password combination does not exits!");
//			}
//			
//		} catch (SQLException e) {
//			System.err.println("The attempt to get the user info failed!");
//			e.printStackTrace();
//		}
//		try {
//			connection.close();
//		} catch (SQLException e) {
//			System.err.println("Couldn't close the connection!");
//			e.printStackTrace();
//		}
//        return output;
//	}
//
//}

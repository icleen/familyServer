package services;

import java.sql.SQLException;

import dao.AuthDao;
import dao.UserDao;
import model.AuthToken;
import model.LoginResponse;
import model.User;

public class RegisterService {
	
	public static LoginResponse register(User user) {
		LoginResponse response = new LoginResponse();
//		find out if the person exists
//		if so, return the error of a pre-existing user in the errorMessage field in the response
		UserDao uDao = new UserDao();
		Boolean found = true;
		try {
			uDao.getUser(user.getUserName());
		} catch (SQLException e1) {
//			System.out.println("User did not exist");
			found = false;
		}
		if(found) {
			response.setErrorMessage("The user already exists!");
			return response;
		}
//		if not, create the user and return the authCode, userName and id in the response
		try {
			uDao.addUser(user);
		} catch (SQLException e) {
			System.err.println("Could not add the user: " + e.getMessage());
			e.printStackTrace();
			response.setErrorMessage("Could not add the user");
			return response;
		}
//		get back the additional server info like the id
		User temp = null;
		try {
			temp = uDao.getUser(user.getUserName());
		} catch (SQLException e1) {
			System.err.println("Could not find the recently created user! " + e1.getMessage());
			e1.printStackTrace();
			response.setErrorMessage("Could not find the recently created user");
			return response;
		}
//		add the info to the AuthCodes table
		AuthDao aDao = new AuthDao();
		String authCode = "newAuthCode";
		try {
			aDao.addAuth(new AuthToken(temp.getUserName(), temp.getPassword(), authCode));
		} catch (SQLException e) {
			System.err.println("Could not add the authToken: " + e.getMessage());
			response.setErrorMessage("Could not add the authToken");
			return response;
		}
		response.setAuthCode(authCode);
		response.setPersonId(temp.getId());
		response.setUserName(temp.getUserName());
		return response;
	}
	
	@SuppressWarnings("serial")
	public class UserAlreadyExistsException extends Exception {
	}

}

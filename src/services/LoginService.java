package services;

import java.sql.SQLException;

import dao.AuthDao;
import model.AuthToken;
import model.LoginRequest;
import model.LoginResponse;

public class LoginService {
	
	public static LoginResponse serve(LoginRequest request) {
		LoginResponse response = new LoginResponse();
//		Check to see if the user exists.  If not, add an error message to the LoginResponse and return it
		AuthDao aDao = new AuthDao();
		AuthToken aToken = null;
		try {
			aToken = aDao.getAuth(request.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
			response.setErrorMessage("The given userName does not exist in the database");
			return response;
		}
		if( !aToken.getPassword().equals(request.getPassword()) ) {
//			System.out.println( "token and request passwords: " + aToken.getPassword() + ", " + request.getPassword() );
			response.setErrorMessage("The password did not match the given userName");
			return response;
		}
//		Else, return the response with the userName, id number and authCode
		response.setUserName(aToken.getUserName());
		response.setPersonId(aToken.getUserId());
		response.setAuthCode(aToken.getAuthCode());
		return response;
	}

}

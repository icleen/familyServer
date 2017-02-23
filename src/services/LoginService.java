package services;

import model.LoginRequest;
import model.LoginResponse;

public class LoginService {
	
	public static LoginResponse serve(LoginRequest info) {
//		Check to see if the user exists.  If not, add an error message to the LoginResponse and return it
//		Else, return the response with the authCode, userName and id number
		LoginResponse response = new LoginResponse("bobobob", info.getUserName(), "1");
		return response;
	}

}

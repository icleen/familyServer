package services;

import model.LoginResponse;
import model.User;

public class LoginService {
	
	public static LoginResponse serve(User user) {
//		Check to see if the user exists.  If not, add an error message to the LoginResponse and return it
//		Else, return the response with the authCode, userName and id number
		LoginResponse response = new LoginResponse("bobobob", user.getUserName(), "1");
		return response;
	}

}

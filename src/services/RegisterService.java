package services;

import model.LoginResponse;
import model.User;

public class RegisterService {
	
	public static LoginResponse register(User user) {
//		find out if the person exists
//		if not, create the user and return the authCode, userName and id in the response
//		if so, return the error of a pre-existing user in the errorMessage field in the response
		LoginResponse response = new LoginResponse("bobobob", user.getUserName(), "1");
		return response;
	}

}

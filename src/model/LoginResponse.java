package model;

public class LoginResponse {
	
	private String authToken;	// Non-empty auth token string
	private String userName;	// User name passed in with request
	private String personId;	// Non-empty string containing the Person ID of the
	private String errorMessage;// error for if the operation failed
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(String authToken, String userName, String personId) {
		this.authToken = authToken;
		this.userName = userName;
		this.personId = personId;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		if(this.errorMessage != null) {
			output.append(errorMessage);
		}else {
			output.append(authToken + ", " + userName + ", " + personId);
		}
		return output.toString();
	}

}

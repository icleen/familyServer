package model;

public class LoginOutput {
	
	private String authToken;	// Non-empty auth token string
	private String userName;	// User name passed in with request
	private String personId;	// Non-empty string containing the Person ID of the
	private String errorMessage;// error for if the operation failed
	
	public LoginOutput() {
		
	}
	
	public LoginOutput(String authToken, String userName, String personId) {
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

}

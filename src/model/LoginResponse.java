package model;

public class LoginResponse {
	
	private String authCode;	// Non-empty auth token string
	private String userName;	// User name passed in with request
	private String personId;	// Non-empty string containing the Person ID of the
	private String errorMessage;// error for if the operation failed
	
	public LoginResponse() {
		
	}
	
	public LoginResponse(String authCode, String userName, String personId) {
		this.authCode = authCode;
		this.userName = userName;
		this.personId = personId;
	}

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
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
		output.append("{\n");
		if(this.errorMessage != null) {
			output.append("message:\"" + errorMessage + "\"\n");
		}else {
			output.append("authToken:\"" + authCode + "\",\n");
			output.append("userName:\"" + userName + "\"\n");
			output.append("persondId:\"" + personId + "\"\n");
		}
		output.append("}");
		return output.toString();
	}

}

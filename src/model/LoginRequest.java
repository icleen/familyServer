package model;

public class LoginRequest {
	
	private String username;
	private String password;
	
	public LoginRequest() {
	}
	
	public LoginRequest(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUserName() {
		return username;
	}

	public void setUserName(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return ("username: " + username + ", password: " + password);
	}

}

package model;

public class LoginRequest {
	
	private String userName;
	private String password;
	
	public LoginRequest() {
	}
	
	public LoginRequest(String userName, String password) {
		this.setUserName(userName);
		this.setPassword(password);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString() {
		return ("userName: " + userName + ", password: " + password);
	}

}

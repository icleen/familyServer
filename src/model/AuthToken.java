package model;

public class AuthToken {
	
	private String username;
	private String password;
	private String authCode;
	private String userId;
	
	public AuthToken() {
	}
	
	public AuthToken(String username, String password, String authCode, String userId) {
		this.username = username;
		this.password = password;
		this.authCode = authCode;
		this.userId = userId;
	}
	
	/**
	 * @return a String with the username
	 */
	public String getUserName() {
		return username;
	}
	/**
	 * set the username
	 * @param username a String witht the username
	 */
	public void setUserName(String username) {
		this.username = username;
	}
	
	/**
	 * @return a String with the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * set the password
	 * @param password a String with the password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 * @return a String with the authCode
	 */
	public String getAuthCode() {
		return authCode;
	}
	/**
	 * set the authCode
	 * @param authCode a String with the authCode
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public boolean equals(AuthToken a) {
		if(this.authCode.equals(a.getAuthCode()) && this.username.equals(a.getUserName()) && this.password.equals(a.getPassword())) {
			return true;
		}
		return false;
	}
	
}

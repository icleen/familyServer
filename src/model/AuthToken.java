package model;

public class AuthToken {
	
	private String userName;
	private String password;
	private String authCode;
	private String userId;
	
	public AuthToken() {
	}
	
	public AuthToken(String userName, String password, String authCode, String userId) {
		this.userName = userName;
		this.password = password;
		this.authCode = authCode;
		this.userId = userId;
	}
	
	/**
	 * @return a String with the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * set the userName
	 * @param userName a String witht the userName
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
		if(this.authCode.equals(a.getAuthCode()) && this.userName.equals(a.getUserName()) && this.password.equals(a.getPassword())) {
			return true;
		}
		return false;
	}
	
}

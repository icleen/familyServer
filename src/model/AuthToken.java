package model;

public class AuthToken {
	
	private String userName;
	private String password;
	private String authCode;
	
	
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
	
}

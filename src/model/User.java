package model;

import database.DataManager;
import database.DataPush;
import generate.Generate;

public class User {
	
	private final int DefaultGenDistance = 4;
	private static int currentId = 0;
	private final int id;
	private final int personId;
	
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;

	public User(String userName, String password, String email, String firstName, String lastName, String gender) {
		this.id = currentId++;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		Person p = new Person(this.id, this.firstName, this.lastName, this.gender);
		this.personId = p.getId();
	}
	
	public User(String userName, String password, String email, String firstName, String lastName, String gender, int id, int personId) {
		this.id = id;
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.personId = personId;
	}
	
	public boolean isCorrectPassword(String password) {
		if(this.password.equals(password)) {
			return true;
		}
		return false;
	}
	
	public String getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}

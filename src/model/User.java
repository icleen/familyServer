package model;

import database.DataManager;
import database.DataPush;

public class User extends Person {
	
	private final int DefaultGenDistance = 4;
	
	private String userName;
	private String password;
	private String email;

	public User(String userName, String password, String email, String firstName, String lastName, String gender) {
		super(null, firstName, lastName, gender); 
		
		this.userName = userName;
		this.email = email;
		this.password = password;
		
		Generate gen = new Generate();
		this.father = gen.father(this, DefaultGenDistance);
		this.mother = gen.mother(this, DefaultGenDistance);
		this.father.setSpouse(this.mother);
		this.mother.setSpouse(this.father);
		
		DataPush.pushUser(this);
		DataManager.printTable("Users");
		DataManager.printTable("People");
		DataManager.printTable("Events");
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
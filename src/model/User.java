package model;

public class User {
	
	private final int DefaultGenDistance = 4;
	private final String id;
	private final String personId;
	
	private String userName;
	private String password;
	private String email;
	private String firstName;
	private String lastName;
	private String gender;
	
	public User(String id, String personId) {
		this.id = id;
		this.personId = personId;
	}
	
	public User(String id, String personId, String userName, String password, String email) {
		this.id = id;
		this.personId = personId;
		this.userName = userName;
		this.password = password;
		this.email = email;
	}
	
	public User(String id, String personId, String userName, String password, String email, String firstName, String lastName, String gender) {
		this.id = id;
		this.personId = personId;
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
	}
	
	public String getId() {
		return id;
	}

	public String getPersonId() {
		return personId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append(userName + ", ");
		output.append(password + ", ");
		output.append(email + ", ");
		output.append(firstName + ", ");
		output.append(lastName + ", ");
		output.append(gender);
		return output.toString();
	}

}

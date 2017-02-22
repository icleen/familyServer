package model;

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
	
	public int getId() {
		return id;
	}

	public int getPersonId() {
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

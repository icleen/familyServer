package model;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String lastname;
	private String gender;
	
	private final String id;
	private final String personId;
	
	public User(String id, String personId) {
		this.id = id;
		this.personId = personId;
	}
	
	public User(String username, String password, String email, String id, String personId) {
		this.id = id;
		this.personId = personId;
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User(String username, String password, String email, String firstname, String lastname, String gender, String id, String personId) {
		this.id = id;
		this.personId = personId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.lastname = lastname;
		this.gender = gender;
	}
	
	public String getId() {
		return id;
	}

	public String getPersonId() {
		return personId;
	}

	public String getfirstname() {
		return firstname;
	}

	public void setfirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getlastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
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

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("{\n");
//		output.append("\tuserId:\"" + this.id + "\",\n");
		output.append("\tusername: " + this.username + "\",\n");
		output.append("\tpassword:\"" + this.password + "\",\n");
		output.append("\temail:\"" + this.email + "\",\n");
		output.append("\tfirstname:\"" + this.firstname + "\",\n");
		output.append("\tlastname:\"" + this.lastname + "\",\n");
		output.append("\tgender:\"" + this.gender + "\",\n");
		output.append("}\n");
		return output.toString();
	}

}

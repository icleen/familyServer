package model;

public class Person {

	protected final String id;
	protected final String userId;
	protected String firstName;
	protected String lastName;
	protected String gender;
	
	protected String father;
	protected String mother;
	protected String spouse;
	protected String[] events;
	
	public Person(String id, String userId) {
		this.id = id;
		this.userId = userId;
	}
	
	public Person(String id, String userId, String fName, String lName, String gender) {
		this.id = id;
		this.userId = userId;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
	}
	
	public Person(String id, String userId, String fName, String lName, String gender, String father, String mother, String spouse, String[] events) {
		this.id = id;
		this.userId = userId;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
		this.spouse = spouse;
		this.events = events;
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

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getSpouse() {
		return spouse;
	}

	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}

	public String[] getEvents() {
		return events;
	}

	public void setEvents(String[] events) {
		this.events = events;
	}

	public String getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append("Descendant: " + this.userId);
		output.append(" Name: " + this.firstName + " " + this.lastName);
		output.append(" Gender: " + this.gender);
		
		return output.toString();
	}

}

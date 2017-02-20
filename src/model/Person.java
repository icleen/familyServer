package model;

public class Person {
	
	protected static int currentId = 0;
	protected final int id;

	protected String firstName;
	protected String lastName;
	protected String gender;
	
	protected final int descendant;
	protected Person father;
	protected Person mother;
	protected Person spouse;
	protected Event[] events;
	
	
	public Person(int descendant, String fName, String lName, String gender) {
		id = currentId++;
		this.descendant = descendant;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
	}
	
	public Person(int descendant, String fName, String lName, String gender, 
			Person father, Person mother, Person spouse) {
		id = currentId++;
		this.descendant = descendant;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
		this.spouse = spouse;
	}
	
	public Person(int descendant, String fName, String lName, String gender, 
			Person father, Person mother, Person spouse, Event[] events) {
		id = currentId++;
		this.descendant = descendant;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
		this.spouse = spouse;
		this.events = events;
	}
	
	public int getId() {
		return id;
	}

	public int getDescendant() {
		return descendant;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
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

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Event[] getEvents() {
		return events;
	}

	public void setEvents(Event[] events) {
		this.events = events;
	}
	
	

}

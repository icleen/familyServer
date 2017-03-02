package model;

public class Person {

//	protected final String userId;
	protected String descendant;
	protected final String personID;
	protected String firstName;
	protected String lastName;
	protected String gender;
	
	protected String father;
	protected String mother;
	protected String spouse;
	
	public Person(String personID, String descendant) {
		this.personID = personID;
//		this.userId = userId;
		this.descendant = descendant;
	}
	
	public Person(String personID, String descendant, String fName, String lName, String gender) {
		this.personID = personID;
//		this.userId = userId;
		this.descendant = descendant;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
	}
	
	public Person(String personID, String descendant, String fName, String lName, String gender, String father, String mother, String spouse) {
		this.personID = personID;
//		this.userId = userId;
		this.descendant = descendant;
		this.firstName = fName;
		this.lastName = lName;
		this.gender = gender;
		this.father = father;
		this.mother = mother;
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

	public String getId() {
		return personID;
	}

//	public String getUserId() {
//		return userId;
//	}
	
	public void setDescendant(String descendant) {
		this.descendant = descendant;
	}
	
	public String getDescendant() {
		return descendant;
	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		output.append("{\n");
		output.append("descendant: " + this.descendant + "\",\n");
		output.append("personID:\"" + this.personID + "\",\n");
		output.append("firstName:\"" + this.firstName + "\",\n");
		output.append("lastName:\"" + this.lastName + "\",\n");
		output.append("gender:\"" + this.gender + "\",\n");
		output.append("father:\"" + this.father + "\",\n");
		output.append("mother:\"" + this.mother + "\",\n");
		output.append("spouse:\"" + this.spouse + "\"\n");
		output.append("}\n");
		return output.toString();
	}
	
	public boolean equals(Person other) {
		if( this.personID.equals(other.getId()) && this.descendant.equals(other.getDescendant()) && this.firstName.equals(other.getFirstName()) 
				&& this.lastName.equals(other.getLastName()) && this.gender.equals(other.getGender()) ) {
			return true;
		}
		return false;
	}

}

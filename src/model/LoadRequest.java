package model;

public class LoadRequest {
	public User[] users;
	public Person[] persons;
	public Event[] events;
	
	public LoadRequest(User[] users, Person[] people, Event[] events) {
		this.users = users;
		this.persons = people;
		this.events = events;
	}
}

package client;

import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.Person;
import model.User;

public class ServerProxy {
	
	/**
	 * creates a new user and generates 4 generations of ancestors for the user
	 * @param user an object of User
	 */
	public LoginResponse registerUser(User user) {
		LoginResponse response = ClientCommunicator.SINGLETON.register(user);
		if(response.getErrorMessage() != null) {
			System.out.println(response.getErrorMessage());
		}
		return response;
	}

	/**
	 * logs a user in and makes the server's user object equal to the user specified
	 * @param user an object of User
	 */
	public LoginResponse userLogin(User user) {
		LoginRequest lr = new LoginRequest();
		lr.setUserName(user.getusername());
		lr.setPassword(user.getPassword());
		LoginResponse response = ClientCommunicator.SINGLETON.login(lr);
		if(response.getErrorMessage() != null) {
			System.out.println(response.getErrorMessage());
		}
		return response;
	}

	/**
	 * deletes all data from the database
	 */
	public String clear() {
		String result = ClientCommunicator.SINGLETON.clear();
		System.out.println(result);
		return result;
	}

	/**
	 * generates the number of generations specified in the database
	 * @param user an object of User
	 * @param generations the number of generations you want to add to this user
	 */
	public String fill(User user, int generations) {
		String result = ClientCommunicator.SINGLETON.fill(user.getusername(), "" + generations);
		System.out.println(result);
		return result;
	}

	/**
	 * loads the specified data into the database, deleting everything that was there before
	 * @param users an array of User objects
	 * @param people an array of Person objects
	 * @param events an array of Event objects
	 */
	public String load(User[] users, Person[] people, Event[] events) {
		String result = ClientCommunicator.SINGLETON.load(users, people, events);
		System.out.println(result);
		return result;
	}

	/**
	 * get all of the people related to the user
	 * @return an array of Person objects, for each of the Persons connected to the user
	 */
	public Person[] getPeople() {
		Object result = ClientCommunicator.SINGLETON.person(null);
		if(result.getClass() == String.class) {
			System.out.println(result);
			return null;
		}
		assert(result.getClass() == Person[].class);
		Person[] people = (Person[]) result;
		return people;
	}

	/**
	 * get the person a specific person based on the id
	 * @param id a String; the id of the person you want access to
	 * @return a person object 
	 */
	public Person getPerson(String id) {
		Object result = ClientCommunicator.SINGLETON.person(id);
		if(result.getClass() == String.class) {
			System.out.println(result);
			return null;
		}
		assert(result.getClass() == Person.class);
		Person person = (Person) result;
		return person;
	}

	/**
	 * gives you all of the events for all the family members of the current user 
	 * @return an array of Event objects
	 */
	public Event[] getEvents() {
		Object result = ClientCommunicator.SINGLETON.event(null);
		if(result.getClass() == String.class) {
			System.out.println(result);
			return null;
		}
		assert(result.getClass() == Event[].class);
		Event[] events = (Event[]) result;
		return events;
	}

	/**
	 * get an event specified by the id
	 * @param id a String for a specific event
	 * @return an event object
	 */
	public Event getEvent(String id) {
		Object result = ClientCommunicator.SINGLETON.event(id);
		if(result.getClass() == String.class) {
			System.out.println(result);
			return null;
		}
		assert(result.getClass() == Event.class);
		Event event = (Event) result;
		return event;
	}

}

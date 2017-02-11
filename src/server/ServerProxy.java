package server;

import model.Event;
import model.Person;
import model.User;

public class ServerProxy implements IServer {

	/**
	 * creates a new user and generates 4 generations of ancestors for the user
	 * @param userName
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param gender
	 */
	@Override
	public void registerUser(String userName, String password, String email, String firstName, String lastName, String gender) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * logs a user in and makes the server's user object equal to the user specified
	 * @param userName
	 * @param password
	 */
	@Override
	public void userLogin(String userName, String password) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * deletes all data from the database
	 */
	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * generates the number of generations specified in the database
	 * @param userName the name of the user to whom you want to add generations to
	 * @param generations the number of generations you want to add to this user
	 */
	@Override
	public void fill(String userName, int generations) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * loads the specified data into the database, deleting everything that was there before
	 * @param users an array of User objects
	 * @param people an array of Person objects
	 * @param events an array of Event objects
	 */
	@Override
	public void load(User[] users, Person[] people, Event[] events) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * get all of the people related to the user
	 * @return an array of Person objects, for each of the Persons connected to the user
	 */
	@Override
	public Person[] getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the person a specific person based on the id
	 * @param id the id of the person you want access to
	 * @return the person object with the id specified
	 */
	@Override
	public Person getPerson(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 
	 * @return the all the events for all the family members of the current user
	 */
	@Override
	public Event[] getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * get the event with the id
	 * @param id a specific event id
	 * @return the event object of the specified id
	 */
	@Override
	public Event getEvent(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

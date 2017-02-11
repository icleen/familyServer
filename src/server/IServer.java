package server;

import model.Event;
import model.Person;
import model.User;

public interface IServer {
	
	/**
	 * creates a new user and generates 4 generations of ancestors for the user
	 * @param userName
	 * @param password
	 * @param email
	 * @param firstName
	 * @param lastName
	 * @param gender
	 */
	public void registerUser(String userName, String password, String email, String firstName, String lastName, String gender);
	
	/**
	 * logs a user in and makes the server's user object equal to the user specified
	 * @param userName
	 * @param password
	 * 
	 */
	public void userLogin(String userName, String password);
	
	/**
	 * deletes all data from the database
	 */
	public void clear();
	
	/**
	 * generates the number of generations specified in the database
	 * @param userName the name of the user to whom you want to add generations to
	 * @param generations the number of generations you want to add to this user
	 */
	public void fill(String userName, int generations);
	
	/**
	 * loads the specified data into the database, deleting everything that was there before
	 * @param users nd array of User objects
	 * @param people an array of Person objects
	 * @param events an array of Event objects
	 */
	public void load(User[] users, Person[] people, Event[] events);
	
	/**
	 * 
	 * @return an array of Person objects, for each of the Persons connected to the user
	 */
	public Person[] getPeople();
	
	/**
	 * 
	 * @param id the id of the person you want access to
	 * @return the person object with the id specified
	 */
	public Person getPerson(String id);
	
	/**
	 * 
	 * 
	 * @return the all the events for all the family members of the current user
	 */
	public Event[] getEvents();
	
	/**
	 * 
	 * @param id a specific event id
	 * @return the event object of the specified id
	 */
	public Event getEvent(String id);

}

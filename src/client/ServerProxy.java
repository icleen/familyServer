package client;

public class ServerProxy {

	private static ClientCommunicator communicator = new ClientCommunicator();
	private String authCode;
	
//	/**
//	 * creates a new user and generates 4 generations of ancestors for the user
//	 * @param user an object of User
//	 */
//	public void registerUser(User user) {
//		// TODO Auto-generated method stub
////		communicator.registerUser(user.getUserName(), user.getPassword(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getGender());
//	}
//
//	/**
//	 * logs a user in and makes the server's user object equal to the user specified
//	 * @param user an object of User
//	 */
//	public void userLogin(User user) {
//		// TODO Auto-generated method stub
////		communicator.userLogin(user.getUserName(), user.getPassword());
//	}
//
//	/**
//	 * deletes all data from the database
//	 */
//	public void clear() {
//		// TODO Auto-generated method stub
////		communicator.clear();
//	}
//
//	/**
//	 * generates the number of generations specified in the database
//	 * @param user an object of User
//	 * @param generations the number of generations you want to add to this user
//	 */
//	public void fill(User user, int generations) {
//		// TODO Auto-generated method stub
////		communicator.fill(user.getUserName(), generations);
//	}
//
//	/**
//	 * loads the specified data into the database, deleting everything that was there before
//	 * @param users an array of User objects
//	 * @param people an array of Person objects
//	 * @param events an array of Event objects
//	 */
//	public void load(User[] users, Person[] people, Event[] events) {
//		// TODO Auto-generated method stub
////		communicator.load(users, people, events);
//	}
//
//	/**
//	 * get all of the people related to the user
//	 * @return an array of Person objects, for each of the Persons connected to the user
//	 */
//	public Person[] getPeople() {
//		// TODO Auto-generated method stub
////		return communicator.getPeople();
//	}
//
//	/**
//	 * get the person a specific person based on the id
//	 * @param id a String; the id of the person you want access to
//	 * @return a person object 
//	 */
//	public Person getPerson(String id) {
//		// TODO Auto-generated method stub
////		return communicator.getPerson(id);
//	}
//
//	/**
//	 * gives you all of the events for all the family members of the current user 
//	 * @return an array of Event objects
//	 */
//	public Event[] getEvents() {
//		// TODO Auto-generated method stub
////		return communicator.getEvents();
//	}
//
//	/**
//	 * get an event specified by the id
//	 * @param id a String for a specific event
//	 * @return an event object
//	 */
//	public Event getEvent(String id) {
//		// TODO Auto-generated method stub
////		return communicator.getEvent(id);
//	}

}

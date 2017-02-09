package server;

import model.Event;
import model.Person;

public interface IServer {
	
	public void registerUser(String userName, String password, String email, String firstName, String lastName, String gender);
	
	public void userLogin(String userName, String password);
	
	public void clear();
	
	public void fill(String userName, int generations);
	
	public void load();
	
	public Person[] getPeople();
	
	public Person getPerson(String id);
	
	public Event[] getEvents();
	
	public Event getEvent(String id);

}

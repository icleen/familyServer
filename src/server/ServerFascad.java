package server;

import model.Event;
import model.Person;
import model.User;

public class ServerFascad implements IServer {

	private Person user;
	
	public ServerFascad() {
	}
	
	@Override
	public void registerUser(String userName, String password, String email, String firstName, String lastName, String gender) {
		user = new User(userName, password, email, firstName, lastName, gender);
	}

	@Override
	public void userLogin(String userName, String password) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void fill(String userName, int generations) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Person[] getPeople() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPerson(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event[] getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Event getEvent(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}

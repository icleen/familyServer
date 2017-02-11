package model;

import database.InitializeTable;
import server.IServer;

public class ServerModel implements IServer {
	
	private Person user;
	
	public ServerModel() {
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
		InitializeTable.init();
		
	}

	@Override
	public void fill(String userName, int generations) {
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

	@Override
	public void load(User[] users, Person[] people, Event[] events) {
		// TODO Auto-generated method stub
		
	}

}

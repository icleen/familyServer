package services;

import java.sql.SQLException;

import dao.Dao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import model.Event;
import model.LoadRequest;
import model.Person;
import model.User;

public class LoadService {
	
	public static String serve(LoadRequest request) {
		
		Dao.initializeTable();
		
		UserDao uDao = new UserDao();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		
		User[] users = null;
		Person[] people = null;
		Event[] events = null;
		
		users = request.users;
		people = request.persons;
		events = request.events;
		
		try {
			uDao.addUsers(users);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not add the users: " + e.getMessage());
			return "Could not add the users";
		}
		try {
			pDao.addPeople(people);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not add the persons: " + e.getMessage());
			return "Could not add the persons";
		}
		try {
			eDao.addEvents(events);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Could not add the events: " + e.getMessage());
			return "Could not add the events";
		}
		
		for(int i = 0; i < users.length; i++) {
			try {
				RegisterService.generateAuthCode(users[i]);
			} catch (SQLException e) {
				e.printStackTrace();
				System.err.println("Could not add the authTokens: " + e.getMessage());
				return "Could not add the authTokens";
			}
		}
		String success = "Successfully added " + users.length + " users, " + people.length 
				+ " persons, and " + events.length + " events to the database.";
		return success;
	}

}

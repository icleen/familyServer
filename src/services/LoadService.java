package services;

import java.sql.SQLException;

import dao.Dao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import model.Event;
import model.LoadRequest;
import model.Message;
import model.Person;
import model.User;

public class LoadService {
	
	public static Message serve(LoadRequest request) {
		
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
			String err = "Could not add the users: " + e.getMessage();
			System.err.println(err);
			return new Message(err);
		}
		try {
			pDao.addPeople(people);
		} catch (SQLException e) {
			e.printStackTrace();
			String err = "Could not add the persons: " + e.getMessage();
			System.err.println(err);
			return new Message(err);
		}
		try {
			eDao.addEvents(events);
		} catch (SQLException e) {
			e.printStackTrace();
			String err = "Could not add the events: " + e.getMessage();
			System.err.println(err);
			return new Message(err);
		}
		
		for(int i = 0; i < users.length; i++) {
			try {
				RegisterService.generateAuthCode(users[i]);
			} catch (SQLException e) {
				e.printStackTrace();
				String err = "Could not add the authTokens: " + e.getMessage();
				System.err.println(err);
				return new Message(err);
			}
		}
		String success = "Successfully added " + users.length + " users, " + people.length 
				+ " persons, and " + events.length + " events to the database.";
		return new Message(success);
	}

}

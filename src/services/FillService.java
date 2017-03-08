package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import generate.Generate;
import model.Event;
import model.Message;
import model.Person;
import model.User;

public class FillService {
	
	public static Message serve(String userName, String gens) {
		
		int generations = 0;
		try {
			generations = Integer.parseInt(gens);
			if(generations < 0) {
				String result = "Invalid value for the generations field";
				return new Message(result);
			}
		} catch(NumberFormatException e) {
			String result = "Invalid value for the generations field";
			return new Message(result);
		}

		
		UserDao uDao = new UserDao();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		User user = null;
		Person userP = null;
		try {
			user = uDao.getUser(userName);
		} catch (SQLException e2) {
			e2.printStackTrace();
			String result = "Couldn't find the username " + userName;
			return new Message(result);
		}
		
		try {
			pDao.delete(userName, "People");
			eDao.delete(userName, "Events");
		} catch (SQLException e1) {
			e1.printStackTrace();
			String result = "Couldn't delete the data associated with " + userName;
			return new Message(result);
		}
		
		String id = generateFamily(user, generations);
		user.setPersonId(id);
		try {
			uDao.insert("Users", user.getusername(), "personId", id);
		} catch (SQLException e1) {
			e1.printStackTrace();
			String result = "Couldn't persondID to the user " + userName;
			return new Message(result);
		}
		
		String result = "You filled " + userName + " with " + gens + " generations";
		return new Message(result);
	}
	
	public static String generateFamily(User user, int gens) {
		Generate gen = new Generate();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		ArrayList<Person> p = gen.generatePeople(gens, user.getusername());
		ArrayList<Event> events = gen.getEvents();
		String id = user.getPersonId();
		if(user.getPersonId() == null) {
			id = gen.nextId();
			gen.updateID();
			user.setPersonId(id);
		}
		// grabs the last two people in the ArrayList to be the parents of the user because those are the last ones created
		Person userP = new Person(id, user.getusername(), user.getfirstname(), user.getlastname(),
				user.getGender(), p.get(p.size() - 2).getId(), p.get(p.size() - 1).getId(), null);
		try {
			pDao.addPeople(p.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			pDao.addPerson(userP);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			eDao.addEvents(events.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

}

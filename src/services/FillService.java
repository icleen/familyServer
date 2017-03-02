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
		
		UserDao uDao = new UserDao();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		User user = null;
		Person userP = null;
		try {
			user = uDao.getUser(userName);
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		try {
			System.out.println(user.getPersonId());
			userP = pDao.getPerson(user.getPersonId());
		} catch (SQLException e2) {
			e2.printStackTrace();
		}
		
		Generate gen = new Generate();
		ArrayList<Person> people = gen.generatePeople(Integer.parseInt(gens), userName);
		ArrayList<Event> events = gen.getEvents();
		
		try {
			pDao.delete(userName, "People");
			eDao.delete(userName, "Events");
		} catch (SQLException e1) {
			e1.printStackTrace();
			String result = "Couldn't delete the data associated with " + userName;
			return new Message(result);
		}
		
		try {
			pDao.addPerson(userP);
			pDao.addPeople(people.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			String result = "Couldn't add the people";
			return new Message(result);
		}
		try {
			eDao.addEvents(events.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			String result = "Couldn't add the events";
			return new Message(result);
		}
		
		String result = "You filled " + userName + " with " + gens + " generations";
		return new Message(result);
	}

}

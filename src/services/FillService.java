package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import generate.Generate;
import model.Event;
import model.Person;
import model.User;

public class FillService {
	
	public static String serve(String userName, String gens) {
		
		UserDao uDao = new UserDao();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		User user = null;
		Person userP = null;
		try {
			user = uDao.getUser(userName);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			userP = pDao.getPerson(user.getPersonId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
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
			return "Couldn't delete the data associated with " + userName;
		}
		
		try {
			pDao.addPerson(userP);
			pDao.addPeople(people.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return "Couldn't add the people";
		}
		try {
			eDao.addEvents(events.toArray());
		} catch (SQLException e) {
			e.printStackTrace();
			return "Couldn't add the events";
		}
		
		return "You filled " + userName + " with " + gens + " generations";
	}

}

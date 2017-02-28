package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import generate.Generate;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;

public class FillService {
	
	public static String serve(String authCode, String gens) {
		AuthDao aDao = new AuthDao();
		AuthToken token = null;
		try {
			token = aDao.getAuthByCode(authCode);
		} catch (SQLException e1) {
			e1.printStackTrace();
			return "Couldn't find the information matching the authCode";
		}
		UserDao uDao = new UserDao();
		User user = null;
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		try {
			user = uDao.getUser(token.getUserName());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		Person userP = null;
		try {
			userP = pDao.getPerson(user.getPersonId());
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		Generate gen = new Generate();
		ArrayList<Person> people = gen.generatePeople(Integer.parseInt(gens), token.getUserName());
		ArrayList<Event> events = gen.getEvents();
		
		try {
			pDao.delete(token.getUserName(), "People");
			eDao.delete(token.getUserName(), "Events");
		} catch (SQLException e1) {
			e1.printStackTrace();
			return "Couldn't delete the data associated with " + token.getUserName();
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
		
		return "You filled " + token.getUserName() + " with " + gens + " generations";
	}

}

package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.PersonDao;
import model.AuthToken;
import model.Person;

public class PersonService {
	
	public static Object serve(String id) {
		
		PersonDao pDao = new PersonDao();
		Person person = null;
		try {
			person = pDao.getPerson(id);
		} catch (SQLException e) {
			e.printStackTrace();
			String response = "Could not get the person";
			return response;
		}
		
		return person;
	}
	
	public static Object people(String authCode) {
		
		PersonDao pDao = new PersonDao();
		AuthDao aDao = new AuthDao();
		AuthToken temp;
		try {
			temp = aDao.getAuth(authCode);
		} catch (SQLException e1) {
			e1.printStackTrace();
			String response = "The authCode was invalid";
			return response;
		}
		
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople(temp.getUserName());
		} catch (SQLException e) {
			e.printStackTrace();
			String response = "Could not get the people";
			return response;
		}
		
		return people;
	}

}

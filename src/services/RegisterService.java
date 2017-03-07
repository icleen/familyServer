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
import model.LoginResponse;
import model.Person;
import model.User;

public class RegisterService {
	
	public static LoginResponse register(User user) {
		LoginResponse response = new LoginResponse();
//		find out if the person exists
//		if so, return the error of a pre-existing user in the errorMessage field in the response
		UserDao uDao = new UserDao();
		Boolean found = true;
		try {
			uDao.getUser(user.getusername());
		} catch (SQLException e1) {
//			System.out.println(e1.getMessage());
//			System.out.println("The user already exists!");
			found = false;
		}
		if(found) {
			response.setErrorMessage("The user already exists!");
			return response;
		}
//		if not, create the user and return the authCode, userName and id in the response
		try {
			uDao.addUser(user);
		} catch (SQLException e) {
			System.err.println("Could not add the user: " + e.getMessage());
			e.printStackTrace();
			response.setErrorMessage("Could not add the user");
			return response;
		}
//		get back the additional server info like the id
		User temp = null;
		try {
			temp = uDao.getUser(user.getusername());
//			System.out.println(temp);
		} catch (SQLException e1) {
			System.err.println("Could not find the recently created user! " + e1.getMessage());
			e1.printStackTrace();
			response.setErrorMessage("Could not find the recently created user");
			return response;
		}
		
		String id = generateFamily(temp);
		user.setPersonId(id);
		try {
			uDao.insert("Users", user.getusername(), "personId", id);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
//		add the info to the AuthCodes table
		try {
			response = generateAuthCode(temp);
		} catch (SQLException e) {
			System.err.println("Could not add the authToken: " + e.getMessage());
			response.setErrorMessage("Could not add the authToken");
			return response;
		}
		return response;
	}
	
	public static LoginResponse generateAuthCode(User user) throws SQLException {
		LoginResponse response = new LoginResponse();
		AuthDao aDao = new AuthDao();
		StringBuilder authCode = new StringBuilder(user.getId() + user.getusername() + user.getId());
		authCode.setCharAt(authCode.length()/2, 'z');
		authCode.insert(0, "ba");
		authCode.insert(authCode.length(), "ab");
		aDao.addAuth(new AuthToken(user.getusername(), user.getPassword(), authCode.toString(), user.getId()));
		response.setAuthCode(authCode.toString());
		response.setPersonId(user.getId());
		response.setUserName(user.getusername());
		return response;
	}
	
	public static String generateFamily(User user) {
		Generate gen = new Generate();
		PersonDao pDao = new PersonDao();
		EventDao eDao = new EventDao();
		ArrayList<Person> p = gen.generatePeople(4, user.getusername());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			eDao.addEvents(events.toArray());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
	@SuppressWarnings("serial")
	public class UserAlreadyExistsException extends Exception {
	}

}

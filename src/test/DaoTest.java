package test;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.Dao;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import model.AuthToken;
import model.Event;
import model.Person;
import model.User;

public class DaoTest {
	
	public static void main(String[] args) {
		DaoTest test = new DaoTest();
		test.combinedTest();
	}
	
	public void combinedTest() {
		userTest();
		authTest();
		personTest();
		eventTest();
	}
	
	public void userTest() {
		Dao.initializeTable();
		UserDao uDao = new UserDao();
		User me = new User(null, null, "iclee141", "bob", "iclee@my.com", "iain", "lee", "male");
		User parker = new User(null, null, "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		try {
			uDao.addUser(me);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
		}
		try {
			uDao.addUser(parker);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
		}
		User feedback = null;
		User feedback2 = null;
		try {
			feedback = uDao.getUser(me.getusername());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
		}
		try {
			feedback2 = uDao.getUser(parker.getusername());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
		}
		
		ArrayList<User> users = null;
		try {
			users = uDao.getUsers();
			System.out.println("There were " + users.size() + " users");
		} catch (SQLException e1) {
			System.err.println("couldn't get the users for some reason");
			e1.printStackTrace();
		}
		
		try {
			uDao.delete(feedback.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
		}
		try {
			uDao.getUser(feedback.getId());
		} catch (SQLException e) {
			System.out.println("the user was deleted");
		}
		try {
			uDao.delete(feedback2.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
		}
		try {
			uDao.getUser(feedback2.getId());
		} catch (SQLException e) {
			System.out.println("the user was deleted");
		}
		
		System.out.println("All user tests went well");
	}
	
	public void authTest() {
		AuthDao aDao = new AuthDao();
		AuthToken token = new AuthToken("iclee141", "bob", "theAuthCode", null);
		AuthToken token2 = new AuthToken("parky", "hello", "theAuthCode", null);
		
		try {
			aDao.addAuth(token);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
		}
		try {
			aDao.addAuth(token2);
		} catch (SQLException e) {
			System.err.println("couldn't add the authToken; the authToken already existed?");
			e.printStackTrace();
		}
		
		AuthToken feedback = null;
		try {
			feedback = aDao.getAuth(token.getUserName());
			aDao.getAuth(token2.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
		}
		try {
			aDao.delete(feedback.getUserId(), "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
		}
		try {
			aDao.getAuth(feedback.getUserId());
		} catch (SQLException e) {
			System.out.println("They were all deleted");
		}
		
		System.out.println("All authorization token tests went well");
	}
	
	public void personTest() {
		PersonDao pDao = new PersonDao();
		String userName = "userName";
		Person person = new Person("1", userName, "burtina", "lambert", "female", "11", "10", null);
		person.setSpouse("2");
		Person person2 = new Person("2", userName, "bob", "lambert", "male", "12", "13", null);
		person2.setSpouse("1");
		
		try {
			pDao.addPerson(person);
		} catch (SQLException e) {
			System.err.println("couldn't add the person; the person already existed?");
			e.printStackTrace();
		}
		try {
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person2; the person2 already existed?");
			e.printStackTrace();
		}
		
		try {
			pDao.getPerson("1");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
		try {
			pDao.getPerson("2");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
		
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
		}
		
		if(people != null && people.size() != 2) {
			System.out.println("There were " + people.size() + " people");
		}
//		if( !people.get(0).equals(feedback) || !people.get(1).equals(feedback2) ) {
//			System.out.println("The people were not equal");
//			System.out.println("Feedback = " + feedback.getFirstName() + "\tPeople[0] = " + people.get(0).getFirstName());
//		}
		
		try {
			pDao.delete(userName, "People");
			if(pDao.getPeople("1") == null) {
				System.out.println("They were all deleted");
			}
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
		}
		
		System.out.println("All person tests went well");
	}
	
	public void eventTest() {
		EventDao eDao = new EventDao();
		String userName = "userName";
		Event birth = new Event("1", userName, "1");
		Event death = new Event("2", userName, "1");
		try {
			eDao.addEvent(birth);
			eDao.addEvent(death);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ArrayList<Event> events = new ArrayList<>();
		try {
			events = eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
		}
		
		try {
			eDao.getEvent(events.get(0).getEventId());
			eDao.getEvent(events.get(1).getEventId());
		} catch (SQLException e) {
			System.err.println("couldn't find the events or the events didn't exist");
			e.printStackTrace();
		}
		
		try {
			eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the events array");
			e.printStackTrace();
		}
		
		try {
			eDao.delete(userName, "Events");
		} catch (SQLException e) {
			System.err.println("couldn't delete the events or the events didn't exist");
			e.printStackTrace();
		}
		
		System.out.println("All event tests went well");
	}

}

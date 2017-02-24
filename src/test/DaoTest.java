package test;

import java.sql.SQLException;

import dao.AuthDao;
import dao.Connector;
import dao.EventDao;
import dao.PersonDao;
import dao.UserDao;
import model.AuthToken;
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
		Connector.initializeTable();
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
		
		try {
			uDao.getUser(me.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
		try {
			uDao.getUser(parker.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
	}
	
	public void authTest() {
		AuthDao aDao = new AuthDao();
		AuthToken token = new AuthToken();
		AuthToken token2 = new AuthToken();
		
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
		
		try {
			aDao.getAuth(token.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
		}
		try {
			aDao.getAuth(token2.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
		}
	}
	
	public void personTest() {
		PersonDao pDao = new PersonDao();
		
		Person person = new Person(1, null, null, null);
		Person person2 = new Person(1, null, null, null);
		
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
	}
	
	public void eventTest() {
		EventDao eDao = new EventDao();
		try {
			eDao.addEvent(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

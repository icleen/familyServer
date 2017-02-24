package test;

import java.sql.SQLException;

import dao.AuthDao;
import dao.Dao;
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
			feedback = uDao.getUser(me.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
		}
		try {
			feedback2 = uDao.getUser(parker.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
		}
		
		try {
			uDao.delete(feedback.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
		}
		try {
			uDao.delete(feedback2.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
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
		AuthToken feedback2 = null;
		try {
			feedback = aDao.getAuth(token.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
		}
		try {
			feedback2 = aDao.getAuth(token2.getUserName());
		} catch (SQLException e) {
			System.err.println("couldn't find the authToken; the authToken didn't exist");
			e.printStackTrace();
		}
		
		try {
			aDao.delete(feedback.getUserId(), "AuthCodes");
		} catch (SQLException e) {
			System.err.println("couldn't delete the authToken or the authToken didn't exist");
			e.printStackTrace();
		}
		try {
			aDao.delete(feedback2.getUserId(), "AuthCodes");
		} catch (SQLException e) {
			System.err.println("couldn't delete the authToken or the authToken didn't exist");
			e.printStackTrace();
		}
		
		System.out.println("All authorization token tests went well");
	}
	
	public void personTest() {
		PersonDao pDao = new PersonDao();
		
		Person person = new Person("1", "1", "burt", "lambert", "female");
		person.setSpouse("2");
		Person person2 = new Person("2", "1", "bob", "lambert", "male");
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
		
		Person feedback = null;
		Person feedback2 = null;
		try {
			feedback = pDao.getPerson("1");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
		try {
			feedback2 = pDao.getPerson("2");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
		}
		
		try {
			pDao.delete(feedback.getId(), "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the person or the person didn't exist");
			e.printStackTrace();
		}
		try {
			pDao.delete(feedback2.getId(), "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the person or the person didn't exist");
			e.printStackTrace();
		}
		
		System.out.println("All person tests went well");
	}
	
	public void eventTest() {
		EventDao eDao = new EventDao();
		try {
			eDao.addEvent(null);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("All event tests went well");
	}

}

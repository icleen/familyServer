package daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import dao.UserDao;
import model.User;

public class UserDaoTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetUser() {
		Dao.initializeTable();
		UserDao uDao = new UserDao();
		User me = new User("iclee141", "bob", "iclee@my.com", "iain", "lee", "male", "2", "5");
		User parker = new User("parky", "hello", "parkman@my.com", "parker", "robin", "male", "1", "2");
		boolean failed = false;
		try {
			uDao.addUser(me);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			uDao.addUser(parker);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		User feedback = null;
		User feedback2 = null;
		try {
			feedback = uDao.getUser(me.getusername());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			feedback2 = uDao.getUser(parker.getusername());
		} catch (SQLException e) {
			System.err.println("couldn't find the user; the user didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		
		try {
			uDao.delete(feedback.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			uDao.getUser(feedback.getId());
		} catch (SQLException e) {
//			System.out.println("the user was deleted");
			failed = true;
		}
		assertTrue(failed);
		failed = false;
		try {
			uDao.delete(feedback2.getId(), "Users");
		} catch (SQLException e) {
			System.err.println("couldn't delete the user or the user didn't exist");
			e.printStackTrace();
			failed = true;
		}
		assertFalse(failed);
		try {
			uDao.getUser(feedback2.getId());
		} catch (SQLException e) {
//			System.out.println("the user was deleted");
			failed = true;
		}
		assertTrue(failed);
		
//		System.out.println("All user tests went well");
	}

	@Test
	public void testGetUsers() {
		Dao.initializeTable();
		UserDao uDao = new UserDao();
		User me = new User("iclee141", "bob", "iclee@my.com", "iain", "lee", "male", "2", "5");
		User parker = new User("parky", "hello", "parkman@my.com", "parker", "robin", "male", "1", "3");
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
		
		ArrayList<User> users = null;
		try {
			users = uDao.getUsers();
		} catch (SQLException e1) {
			System.err.println("couldn't get the users for some reason");
			e1.printStackTrace();
		}
		assertEquals(2, users.size());
//		System.out.println(users.get(0));
//		System.out.println(users.get(1));
		assertTrue(me.equals(users.get(1)));
		assertTrue(parker.equals(users.get(0)));
	}

	@Test
	public void testAddUser() {
		Dao.initializeTable();
		UserDao uDao = new UserDao();
		User me = new User("iclee141", "bob", "iclee@my.com", "iain", "lee", "male", "2", "5");
		User parker = new User("parky", "hello", "parkman@my.com", "parker", "robin", "male", "1", "2");
		try {
			uDao.addUser(me);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			fail();
		}
		try {
			uDao.addUser(parker);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddUsers() {
		Dao.initializeTable();
		UserDao uDao = new UserDao();
		User me = new User("iclee141", "bob", "iclee@my.com", "iain", "lee", "male", "2", "5");
		User parker = new User("parky", "hello", "parkman@my.com", "parker", "robin", "male", "1", "3");
		try {
			uDao.addUser(me);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			fail();
		}
		try {
			uDao.addUser(parker);
		} catch (SQLException e) {
			System.err.println("Couldn't add the user or that user existed already");
			e.printStackTrace();
			fail();
		}
		
		ArrayList<User> users = null;
		try {
			users = uDao.getUsers();
		} catch (SQLException e1) {
			System.err.println("couldn't get the users for some reason");
			e1.printStackTrace();
		}
		assertEquals(2, users.size());
		assertTrue(me.equals(users.get(1)));
		assertTrue(parker.equals(users.get(0)));
	}

}

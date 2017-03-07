package daoTests;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import dao.PersonDao;
import model.Person;

public class DaoTest {

	@Before
	public void setUp() throws Exception {
		Dao.initializeTable();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetConnection() {
		Connection connection = Dao.getConnection();
		assertTrue(connection != null);
	}

	@Test
	public void testInitializeTable() {
		PersonDao pDao = new PersonDao();
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople("username");
			if(people != null) {
				fail();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDelete() {
		PersonDao pDao = new PersonDao();
		String userName = "userName";
		Person person = new Person("100", userName, "burtina", "lambert", "female", "110", "100", null);
		person.setSpouse("200");
		Person person2 = new Person("200", userName, "bob", "lambert", "male", "120", "130", null);
		person2.setSpouse("100");
		
		try {
			pDao.addPerson(person);
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person; the person already existed?");
			e.printStackTrace();
			fail();
		}
		try {
			pDao.getPerson("100");
			pDao.getPerson("200");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
			fail();
		}
		try {
			pDao.delete(userName, "People");
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
			fail();
		}
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople(userName);
			if(people != null) {
				fail();
			}
		} catch (SQLException e) {
//			e.printStackTrace();
		}
	}

}

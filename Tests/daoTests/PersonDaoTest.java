package daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import dao.PersonDao;
import model.Person;

public class PersonDaoTest {

	@Before
	public void setUp() throws Exception {
		Dao.initializeTable();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetPerson() {
		PersonDao pDao = new PersonDao();
		String userName = "userName";
		Person person = new Person("100", userName, "burtina", "lambert", "female", "110", "100", null);
		person.setSpouse("200");
		Person person2 = new Person("200", userName, "bob", "lambert", "male", "120", "130", null);
		person2.setSpouse("100");
		
		try {
			pDao.addPerson(person);
		} catch (SQLException e) {
			System.err.println("couldn't add the person; the person already existed?");
			e.printStackTrace();
			fail();
		}
		try {
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person2; the person2 already existed?");
			e.printStackTrace();
			fail();
		}
		
		try {
			pDao.getPerson("100");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
			fail();
		}
		try {
			pDao.getPerson("200");
		} catch (SQLException e) {
			System.err.println("couldn't find the person; the person didn't exist");
			e.printStackTrace();
			fail();
		}
		
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
			fail();
		}
		assertTrue(people != null);
		assertEquals(2, people.size());
//		if(people != null && people.size() != 2) {
//			System.out.println("There were " + people.size() + " people");
//		}
//		if( !people.get(0).equals(feedback) || !people.get(1).equals(feedback2) ) {
//			System.out.println("The people were not equal");
//			System.out.println("Feedback = " + feedback.getFirstName() + "\tPeople[0] = " + people.get(0).getFirstName());
//		}
		
		try {
			pDao.delete(userName, "People");
			assertTrue(pDao.getPeople("1") == null);
//				System.out.println("They were all deleted");
			
		} catch (SQLException e) {
			System.err.println("couldn't delete the persons or the persons didn't exist");
			e.printStackTrace();
			fail();
		}
		
//		System.out.println("All person tests went well");
	}

	@Test
	public void testGetPeople() {
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
			fail();
		}
		try {
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person2; the person2 already existed?");
			e.printStackTrace();
			fail();
		}
		
		ArrayList<Person> people = null;
		try {
			people = pDao.getPeople(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
			fail();
		}
		assertTrue(people != null);
		assertEquals(2, people.size());
		assertTrue(person.equals(people.get(0)));
		assertTrue(person2.equals(people.get(1)));
	}

	@Test
	public void testAddPerson() {
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
			fail();
		}
		try {
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person2; the person2 already existed?");
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddPeople() {
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
			fail();
		}
		try {
			pDao.addPerson(person2);
		} catch (SQLException e) {
			System.err.println("couldn't add the person2; the person2 already existed?");
			e.printStackTrace();
			fail();
		}
	}

}

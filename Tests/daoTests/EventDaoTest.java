package daoTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.Dao;
import dao.EventDao;
import model.Event;

public class EventDaoTest {

	@Before
	public void setUp() throws Exception {
		Dao.initializeTable();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetEvent() {
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
			fail();
		}
		
		ArrayList<Event> events = new ArrayList<>();
		try {
			events = eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
			fail();
		}
		
		try {
			eDao.getEvent(events.get(0).getEventId());
			eDao.getEvent(events.get(1).getEventId());
		} catch (SQLException e) {
			System.err.println("couldn't find the events or the events didn't exist");
			e.printStackTrace();
			fail();
		}
		
		try {
			eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the events array");
			e.printStackTrace();
			fail();
		}
		
		try {
			eDao.delete(userName, "Events");
		} catch (SQLException e) {
			System.err.println("couldn't delete the events or the events didn't exist");
			e.printStackTrace();
			fail();
		}
		
//		System.out.println("All event tests went well");
	}

	@Test
	public void testGetEvents() {
		EventDao eDao = new EventDao();
		String userName = "userName";
		Event birth = new Event("1", userName, "1", "lat", "long", "country", "city", "type", "year");
		Event death = new Event("2", userName, "1", "lat", "long", "country", "city", "type", "year");
		try {
			eDao.addEvent(birth);
			eDao.addEvent(death);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		ArrayList<Event> events = new ArrayList<>();
		try {
			events = eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
			fail();
		}
		assertTrue(birth.equals(events.get(0)));
		assertTrue(death.equals(events.get(1)));
	}

	@Test
	public void testAddEvent() {
		EventDao eDao = new EventDao();
		String userName = "userName";
		Event birth = new Event("1", userName, "1", "lat", "long", "country", "city", "type", "year");
		Event death = new Event("2", userName, "1", "lat", "long", "country", "city", "type", "year");
		try {
			eDao.addEvent(birth);
			eDao.addEvent(death);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testAddEvents() {
		EventDao eDao = new EventDao();
		String userName = "userName";
		Event birth = new Event("1", userName, "1", "lat", "long", "country", "city", "type", "year");
		Event death = new Event("2", userName, "1", "lat", "long", "country", "city", "type", "year");
		try {
			eDao.addEvent(birth);
			eDao.addEvent(death);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		ArrayList<Event> events = new ArrayList<>();
		try {
			events = eDao.getEvents(userName);
		} catch (SQLException e) {
			System.err.println("couldn't get the array");
			e.printStackTrace();
			fail();
		}
		assertTrue(birth.equals(events.get(0)));
		assertTrue(death.equals(events.get(1)));
	}

}

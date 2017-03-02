package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.ClientCommunicator;
import client.ServerProxy;
import model.Event;
import model.LoginResponse;
import model.Message;
import model.Person;
import model.User;

public class ServerProxyTest {

	@Before
	public void setUp() throws Exception {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegisterUser() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
	}

	@Test
	public void testUserLogin() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		
		proxy.registerUser(me);
		result = proxy.userLogin(me);
		assertTrue(expected.equals(result));
	}

	@Test
	public void testClear() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		
		proxy.registerUser(me);
		result = proxy.userLogin(me);
		assertTrue(expected.equals(result));
		
		proxy.clear();
		result = proxy.userLogin(me);
		assertEquals("The given userName does not exist in the database", result.getErrorMessage());
	}

	@Test
	public void testFill() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
		
//		2 + 2*2 + 2*2*2 + 2*2*2*2 = 2 + 4 + 8 + 16 = 30
//		add 1 person for the user's person object and the total should be 31
		Person[] people = proxy.getPeople();
		assertEquals(31, people.length);
		
//		30 persons * 4 events = 30 * 4 = 120 events for all of the people
		Event[] events = proxy.getEvents();
		assertEquals(120, events.length);
		
		proxy.fill(me, 5);
		people = proxy.getPeople();
//		2 + 2*2 + 2*2*2 + 2*2*2*2 + 2*2*2*2*2 = 2 + 4 + 8 + 16 + 32 = 62
//		add 1 person for the user's person object and the total should be 63
		assertEquals(63, people.length);
		events = proxy.getEvents();
//		62 persons * 4 events = 62 * 4 = 248 events for all of the people
		assertEquals(248, events.length);
	}

	@Test
	public void testLoad() {
		User[] users = {
				new User("iclee141", "password", "icleen@my.com", "iain", "lee", "male", "1", "10")
		};
		Person[] people = {
				new Person("10", "iclee141", "iain", "lee", "m", "10", "11", null), 
				new Person("20", "iclee141", "bob", "lee", "m", null, null, "11"),
				new Person("21", "iclee141", "bobina", "lee", "f", null, null, "10")
		};
		Event[] events = {
				new Event("30", "iclee141", "20", "lat", "long", "country", "city", "birth", "1990"),
				new Event("31", "iclee141", "20", "lat", "long", "country", "city", "death", null),
				new Event("32", "iclee141", "21", "lat", "long", "country", "city", "birth", "1990"),
				new Event("33", "iclee141", "21", "lat", "long", "country", "city", "death", null)
		};
		Message response = ClientCommunicator.SINGLETON.load(users, people, events);
		System.out.println(response);
		String expected = "Successfully added " + users.length + " users, " + people.length 
				+ " persons, and " + events.length + " events to the database.";
		assertEquals(expected, response.getMessage());
	}

	@Test
	public void testGetPeople() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
		
		Person[] people = proxy.getPeople();
		assertEquals(31, people.length);
		
		proxy.clear();
		people = proxy.getPeople();
		assertTrue(people == null);
	}

	@Test
	public void testGetPerson() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
		
		Person[] people = proxy.getPeople();
		assertEquals(31, people.length);
		
		Person person = proxy.getPerson(people[0].getId());
		assertTrue(person != null);
		System.out.println(person);
		
		proxy.clear();
		person = proxy.getPerson(people[0].getId());
		assertTrue(person == null);
	}

	@Test
	public void testGetEvents() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
		
		Event[] events = proxy.getEvents();
		assertEquals(120, events.length);
		
		proxy.clear();
		events = proxy.getEvents();
		assertTrue(events == null);
	}

	@Test
	public void testGetEvent() {
		ServerProxy proxy = new ServerProxy();
		proxy.clear();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "10", "23");
		LoginResponse expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		LoginResponse result = null;
		result = proxy.registerUser(me);
		assertTrue(expected.equals(result));
		
		Event[] events = proxy.getEvents();
		assertEquals(120, events.length);
		
		Event event = proxy.getEvent(events[0].getEventId());
		assertTrue(event != null);
		System.out.println(event);
		
		proxy.clear();
		event = proxy.getEvent(events[0].getEventId());
		assertTrue(event == null);
		System.out.println(event);
	}

}

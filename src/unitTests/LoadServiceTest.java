package unitTests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.Event;
import model.LoadRequest;
import model.Message;
import model.Person;
import model.User;
import services.LoadService;

public class LoadServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testServe() {
		User[] users = {
				new User("1", "1", "iclee141", "password", "icleen@my.com", "iain", "lee", "male")
		};
		Person[] people = {
				new Person("1", "iclee141", "iain", "lee", "m", "10", "11", null), 
				new Person("10", "iclee141", "bob", "lee", "m", null, null, "11"),
				new Person("11", "iclee141", "bobina", "lee", "f", null, null, "10")
		};
		Event[] events = {
				new Event("10", "iclee141", "10", "lat", "long", "country", "city", "birth", "1990"),
				new Event("11", "iclee141", "10", "lat", "long", "country", "city", "death", null),
				new Event("12", "iclee141", "11", "lat", "long", "country", "city", "birth", "1990"),
				new Event("13", "iclee141", "11", "lat", "long", "country", "city", "death", null)
		};
		LoadRequest request = new LoadRequest(users, people, events);
		Message response = LoadService.serve(request);
		System.out.println(response);
		String expected = "Successfully added " + users.length + " users, " + people.length 
				+ " persons, and " + events.length + " events to the database.";
		assertEquals(expected, response.toString());
	}

}

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
import services.ClearService;
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
		ClearService.serve();
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
		LoadRequest request = new LoadRequest(users, people, events);
		Message response = LoadService.serve(request);
//		System.out.println(response);
		String expected = "{\n\t\"message\":\"Successfully added " + users.length + " users, " + people.length 
				+ " persons, and " + events.length + " events to the database.\"\n}";
		assertEquals(expected, response.toString());
	}

}

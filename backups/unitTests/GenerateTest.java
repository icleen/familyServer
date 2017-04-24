package unitTests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import generate.Generate;
import model.Event;
import model.Person;

public class GenerateTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGeneratePeople() {
		Generate gen = new Generate();
		ArrayList<Person> people = gen.generatePeople(4, "userName");
		assertEquals(people.size(), 30);
	}

	@Test
	public void testGenerateEvents() {
		Generate gen = new Generate();
		ArrayList<Event> e = gen.generateEvents("1", "userName", 4);
		assertEquals(e.size(), 4);
		gen.generateEvents("2", "userName", 4);
		e = gen.getEvents();
		assertEquals(e.size(), 8);
	}

}

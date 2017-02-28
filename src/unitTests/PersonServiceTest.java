package unitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.PersonDao;
import model.LoginResponse;
import model.Person;
import model.User;
import services.ClearService;
import services.EventService;
import services.PersonService;
import services.RegisterService;

public class PersonServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testServe() {
		ClearService.serve();
		User me = new User("1", "1", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		LoginResponse response = RegisterService.register(me);
		Person check = (Person) PersonService.serve(response.getAuthCode(), "1010");
		assertTrue(check != null);
		Person bob = new Person("10", "iclee141", "bob", "lee", "m", null, null, null);
		PersonDao pDao = new PersonDao();
		try {
			pDao.addPerson(bob);
			check = pDao.getPerson(bob.getId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(bob.equals(check));
		assertEquals(bob.getId(), check.getId());
		
		check = (Person) PersonService.serve(response.getAuthCode(), bob.getId());
		assertTrue(bob.equals(check));
		assertEquals(bob.getId(), check.getId());
		System.out.print(check.toString());
		
		ArrayList<Person> listCheck = (ArrayList<Person>) PersonService.serve(response.getAuthCode(), null);
		assertTrue(listCheck != null);
		assertEquals(32, listCheck.size());
		
		try {
			check = pDao.getPerson("monkey");
		} catch (SQLException e) {
			System.out.println("there was no monkey eventId");
			check = null;
		}
		assertTrue(check == null);
		
		Object o = PersonService.serve(response.getAuthCode(), "null");
		assertEquals(String.class, o.getClass());
		System.out.println(o);
	}

}

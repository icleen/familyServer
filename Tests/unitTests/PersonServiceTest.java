package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.PersonDao;
import model.LoginResponse;
import model.Message;
import model.People;
import model.Person;
import model.User;
import services.ClearService;
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
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "1", "10");
		LoginResponse response = RegisterService.register(me);
		Person check = null;
		Object result = PersonService.serve(response.getAuthCode(), "10");
		if(result.getClass() == Message.class) {
			System.out.println(result);
		}
		assertEquals(Person.class, result.getClass());
		check = (Person) result;
		Person bob = new Person("27", "iclee141", "bob", "lee", "m", null, null, null);
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
		
		Object listCheck = PersonService.serve(response.getAuthCode(), null);
		assertTrue(listCheck != null);
		assertEquals(People.class, listCheck.getClass());
		People or = (People) listCheck;
		assertEquals(32, or.data.length);
		
		try {
			check = pDao.getPerson("monkey");
		} catch (SQLException e) {
			System.out.println("there was no monkey eventId");
			check = null;
		}
		assertTrue(check == null);
		
		Object o = PersonService.serve(response.getAuthCode(), "null");
		assertEquals(Message.class, o.getClass());
		System.out.println(o);
	}

}

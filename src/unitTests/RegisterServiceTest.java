package unitTests;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.EventDao;
import dao.PersonDao;
import model.Event;
import model.LoginResponse;
import model.Person;
import model.User;
import services.ClearService;
import services.RegisterService;

public class RegisterServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() {
		ClearService.serve();
		User me = new User("1", "1", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		LoginResponse response = RegisterService.register(me);
		LoginResponse expected = new LoginResponse("ba1iclez1411ab", "iclee141", "1");
		assertEquals(response.getUserName(), expected.getUserName());
		assertEquals(response.getAuthCode(), expected.getAuthCode());
		assertEquals(response.getPersonId(), expected.getPersonId());
	}
	
	@Test
	public void testGenerateFamily() {
		ClearService.serve();
		User me = new User("1", "1", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		RegisterService.generateFamily(me);
		PersonDao pDao = new PersonDao();
		ArrayList<Person> peeps = null;
		try {
			peeps = pDao.getPeople(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(peeps.size(), 31);
		EventDao eDao = new EventDao();
		ArrayList<Event> events = null;
		try {
			events = eDao.getEvents(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(events.size(), 120);
	}

}

package unitTests;

import static org.junit.Assert.*;

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
import services.FillService;
import services.RegisterService;

public class FillServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testServeFiveGen() {
		ClearService.serve();
		User me = new User("1", "1", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		LoginResponse response = RegisterService.register(me);
		PersonDao pDao = new PersonDao();
		ArrayList<Person> peeps = null;
		EventDao eDao = new EventDao();
		ArrayList<Event> events = null;
		try {
			peeps = pDao.getPeople(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		2 + 2*2 + 2*2*2 + 2*2*2*2 = 2 + 4 + 8 + 16 = 30
		assertEquals(31, peeps.size());
		try {
			events = eDao.getEvents(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		30 persons * 4 events = 30 * 4 = 120
		assertEquals(120, events.size());
		
		System.out.println(FillService.serve(response.getAuthCode(), "5"));
		try {
			peeps = pDao.getPeople(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		2 + 2*2 + 2*2*2 + 2*2*2*2 + 2*2*2*2*2 = 2 + 4 + 8 + 16 + 32 = 62
		assertEquals(63, peeps.size());
		try {
			events = eDao.getEvents(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		62 persons * 4 events = 62 * 4 = 248
		assertEquals(248, events.size());
	}
	
	@Test
	public void testServeThreeGen() {
		ClearService.serve();
		User me = new User("1", "1", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		LoginResponse response = RegisterService.register(me);
		PersonDao pDao = new PersonDao();
		ArrayList<Person> peeps = null;
		EventDao eDao = new EventDao();
		ArrayList<Event> events = null;
		try {
			peeps = pDao.getPeople(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		2 + 2*2 + 2*2*2 + 2*2*2*2 = 2 + 4 + 8 + 16 = 30
		assertEquals(31, peeps.size());
		try {
			events = eDao.getEvents(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		30 persons * 4 events = 30 * 4 = 120
		assertEquals(120, events.size());
		
		System.out.println(FillService.serve(response.getAuthCode(), "3"));
		try {
			peeps = pDao.getPeople(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		2 + 2*2 + 2*2*2 = 2 + 4 + 8 = 14
		assertEquals(15, peeps.size());
		try {
			events = eDao.getEvents(me.getUserName());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		14 persons * 4 events = 14 * 4 = 56
		assertEquals(56, events.size());
	}

}

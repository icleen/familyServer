package unitTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dao.EventDao;
import model.Event;
import model.LoginResponse;
import model.Message;
import model.ObjectResponse;
import model.User;
import services.ClearService;
import services.EventService;
import services.RegisterService;

public class EventServiceTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testServe() {
		ClearService.serve();
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male", "1", "1");
		LoginResponse response = RegisterService.register(me);
		Event check = null;
		Event original = new Event("10", "iclee141", "10", "lat", "long", "country", "city", "birth", "1990");
		EventDao eDao = new EventDao();
		try {
			eDao.addEvent(original);
			check = eDao.getEvent(original.getEventId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(original.equals(check));
		assertEquals(original.getEventId(), check.getEventId());
		
		check = (Event) EventService.serve(response.getAuthCode(), original.getEventId());
		assertTrue(original.equals(check));
		assertEquals(original.getEventId(), check.getEventId());
		System.out.print(check.toString());
		
		Object listCheck = EventService.serve(response.getAuthCode(), null);
		assertTrue(listCheck != null);
		assertEquals(ObjectResponse.class, listCheck.getClass());
		ObjectResponse or = (ObjectResponse) listCheck;
		assertEquals(121, or.data.length);
		
		try {
			check = eDao.getEvent("monkey");
		} catch (SQLException e) {
			System.out.println("there was no monkey eventId");
			check = null;
		}
		assertTrue(check == null);
		
		listCheck = EventService.serve(response.getAuthCode(), "null");
		assertEquals(Message.class, listCheck.getClass());
		System.out.println(listCheck);
		
		listCheck = EventService.serve("null", null);
		assertEquals(Message.class, listCheck.getClass());
		System.out.println(listCheck);
	}

}

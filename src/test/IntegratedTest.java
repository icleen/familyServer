package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import client.ClientCommunicator;
import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.User;

public class IntegratedTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRegister() {
		ClientCommunicator.SINGLETON.clear();
		User me = new User("10", "23", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		User parker = new User("12", "24", "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		LoginResponse expected = null;
		Object result = null;
		
		expected = new LoginResponse("ba12pazky12ab", "parky", "12");
		result = ClientCommunicator.SINGLETON.register(parker);
		assertEquals(LoginResponse.class, result.getClass());
		assertTrue(expected.equals(result));
		LoginResponse r = (LoginResponse) result;
		assertTrue(r.getErrorMessage() == null);
		System.out.println("result of register parker: " + result.toString());
		
		expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		result = ClientCommunicator.SINGLETON.register(me);
		assertEquals(LoginResponse.class, result.getClass());
		assertTrue(expected.equals(result));
		r = (LoginResponse) result;
		assertTrue(r.getErrorMessage() == null);
		System.out.println("result of register me: " + result.toString());
	}

	@Test
	public void testLogin() {
		User me = new User("10", "23", "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		User parker = new User("12", "24", "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		LoginResponse expected = null;
		Object result = null;
		ClientCommunicator.SINGLETON.register(parker);
		ClientCommunicator.SINGLETON.register(me);
		
		expected = new LoginResponse("ba12pazky12ab", "parky", "12");
		result = ClientCommunicator.SINGLETON.login(new LoginRequest(parker.getusername(), parker.getPassword()));
		assertEquals(LoginResponse.class, result.getClass());
		assertTrue(expected.equals(result));
		LoginResponse r = (LoginResponse) result;
		assertTrue(r.getErrorMessage() == null);
		System.out.println("result of login: " + result.toString());
		
		expected = new LoginResponse("ba10iclez14110ab", "iclee141", "10");
		result = ClientCommunicator.SINGLETON.login(new LoginRequest(me.getusername(), me.getPassword()));
		assertEquals(LoginResponse.class, result.getClass());
		assertTrue(expected.equals(result));
		r = (LoginResponse) result;
		assertTrue(r.getErrorMessage() == null);
		System.out.println("result of login: " + result.toString());
	}

	@Test
	public void testClear() {
		ClientCommunicator.SINGLETON.clear();
	}

//	@Test
//	public void testFill() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testLoad() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testEvent() {
		ClientCommunicator.SINGLETON.clear();
		User parker = new User("12", "24", "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		Object result = null;
		LoginResponse response = (LoginResponse) ClientCommunicator.SINGLETON.register(parker);
		assertTrue(response.getErrorMessage() == null);

		result = ClientCommunicator.SINGLETON.event("0");
		System.out.println(result.getClass());
		System.out.println(result);
//		assertEquals(Event.class, result.getClass());
		
		result = ClientCommunicator.SINGLETON.event(null);
		assertEquals(Event[].class, result.getClass());
		System.out.println(result.toString());
	}

//	@Test
//	public void testPerson() {
//		fail("Not yet implemented");
//	}

}

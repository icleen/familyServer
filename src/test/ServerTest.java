package test;

import java.sql.SQLException;
import java.util.ArrayList;

import client.ClientCommunicator;
import dao.PersonDao;
import generate.Generate;
import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.Person;
import model.User;
import services.ClearService;

public class ServerTest {
	
	public static void main(String args[]) {
//		Object result = ClientCommunicator.SINGLETON.clear();
//		if(result.getClass() != String.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of clear: " + result.toString());
//		
//		System.out.println();
		
//		registerLoginTest();
//		personEventTests();
		generateTest();
		
		return;
	}

	static void registerLoginTest() {
		
		User me = new User(null, null, "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		User parker = new User(null, null, "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		
		Object result = ClientCommunicator.SINGLETON.register(parker);
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of register parker: " + result.toString());
		
		System.out.println();
		
		result = ClientCommunicator.SINGLETON.register(me);
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of register me: " + result.toString());
		
		System.out.println();
		
		result = ClientCommunicator.SINGLETON.login(new LoginRequest(parker.getUserName(), parker.getPassword()));
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of login: " + result.toString());
		
		System.out.println();
		
		result = ClientCommunicator.SINGLETON.login(new LoginRequest(me.getUserName(), me.getPassword()));
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of login: " + result.toString());
		
		System.out.println();
	}
	
	static void personEventTests() {
		User me = new User(null, null, "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		
		Object result = null;
		
		result = ClientCommunicator.SINGLETON.person("1");
		if(result.getClass() != Person.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of person: " + result.toString());
		
		System.out.println();
		
		result = ClientCommunicator.SINGLETON.event("1");
		if(result.getClass() != Event.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of event:\n" + result.toString());
		
	}
	
	static void generateTest() {
		ClearService.serve();
		
		Generate gen = new Generate();
		String userName = "userName";
		ArrayList<Person> people = gen.generatePeople(4, userName);
		PersonDao pDao = new PersonDao();
		try {
			pDao.addPeople(people.toArray());
		} catch (SQLException e1) {
			System.out.println("It didn't work!");
			e1.printStackTrace();
		}
		ArrayList<Person> others = null;
//		for(int i = 0; i < people.size(); i++) {
//			try {
//				pDao.addPerson(people.get(i));
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
		try {
			others = pDao.getPeople(userName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("People: " + others);
	}
	
}

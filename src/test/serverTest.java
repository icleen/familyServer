package test;

import client.ClientCommunicator;
import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.Person;
import model.User;

public class serverTest {
	
	public static void main(String args[]) {
		Object result = ClientCommunicator.SINGLETON.clear();
		if(result.getClass() != String.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
//		System.out.println("result of clear: " + result.toString());
//		
//		System.out.println();
		
		registerLoginTest();
//		communicatorTests();
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
	
	static void communicatorTests() {
		User me = new User(null, null, "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		
		Object result = ClientCommunicator.SINGLETON.fill(me.getUserName(), "5");
		if(result.getClass() != String.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of fill: " + result.toString());
		
		System.out.println();
		
		result = ClientCommunicator.SINGLETON.load(null, null, null);
		if(result.getClass() != String.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of load: " + result.toString());
		
		System.out.println();
		
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
	
}

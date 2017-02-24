package test;

import client.ClientCommunicator;
import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.Person;
import model.User;

public class serverTest {
	
	public static void main(String args[]) {
		
//		String result = (String) ClientCommunicator.SINGLETON.hello("Hello");
//		System.out.println("result: " + result);
//		
//		result = (String) ClientCommunicator.SINGLETON.primitive(13);
//		System.out.println("result: " + result);
		
		Object result = ClientCommunicator.SINGLETON.clear();
		if(result.getClass() != String.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of clear: " + result.toString());
		
		System.out.println();
		
		User me = new User(null, null, "iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		User parker = new User(null, null, "parky", "hello", "parkman@my.com", "parker", "robin", "male");
		
		result = ClientCommunicator.SINGLETON.register(parker);
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result of register: " + result.toString());
		
		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.login(new LoginRequest(me.getUserName(), me.getPassword()));
//		if(result.getClass() != LoginResponse.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of login: " + result.toString());
//		
//		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.login(new LoginRequest(me.getUserName(), me.getPassword()));
//		if(result.getClass() != LoginResponse.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of login: " + result.toString());
//		
//		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.fill("iclee141", "5");
//		if(result.getClass() != String.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of fill: " + result.toString());
//		
//		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.load(null, null, null);
//		if(result.getClass() != String.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of load: " + result.toString());
//		
//		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.person("1");
//		if(result.getClass() != Person.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of person: " + result.toString());
//		
//		System.out.println();
//		
//		result = ClientCommunicator.SINGLETON.event("1");
//		if(result.getClass() != Event.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		System.out.println("result of event:\n" + result.toString());
		
		return;
	}

}

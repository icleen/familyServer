package test;

import client.ClientCommunicator;
import model.LoginResponse;
import model.User;

public class serverTest {
	
	public static void main(String args[]) {
		
//		String result = (String) ClientCommunicator.SINGLETON.hello("Hello");
//		System.out.println("result: " + result);
//		
//		result = (String) ClientCommunicator.SINGLETON.primitive(13);
//		System.out.println("result: " + result);
		
		User me = new User("iclee141", "bob", "icleen@my.com", "iain", "lee", "male");
		Object result = ClientCommunicator.SINGLETON.register(me);
		if(result.getClass() != LoginResponse.class) {
			System.out.println("It isn't the right class?" + result.getClass());
		}
		System.out.println("result: " + result.toString());
		
		
		return;
	}

}

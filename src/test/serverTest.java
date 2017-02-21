package test;

import client.ClientCommunicator;

public class serverTest {
	
	public static void main(String args[]) {
		
		String result = (String) ClientCommunicator.SINGLETON.hello("Hello");
		System.out.println("result: " + result);
		
		return;
	}

}

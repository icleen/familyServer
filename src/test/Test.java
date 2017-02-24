package test;

import client.ClientCommunicator;

public class Test {
	
	public static void main(String args[]) {
//		Object result = ClientCommunicator.SINGLETON.clear();
//		if(result.getClass() != String.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		serverTest.registerLoginTest();
		
		DaoTest dTest = new DaoTest();
		dTest.userTest();
		dTest.authTest();
		
		return;
	}

}

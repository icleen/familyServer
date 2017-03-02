package test;

import java.util.ArrayList;

import model.Event;

public class Test {
	
	public static void main(String args[]) {
		
		System.out.println("----Starting tests----");
		
//		Object result = ClientCommunicator.SINGLETON.clear();
//		if(result.getClass() != String.class) {
//			System.out.println("It isn't the right class?" + result.getClass());
//		}
//		serverTest.registerLoginTest();
		
//		DaoTest dTest = new DaoTest();
//		dTest.combinedTest();
		
		ArrayList<Event> events = new ArrayList<>();
		events.add(new Event("10", "iclee141", "10", "lat", "long", "country", "city", "birth", "1990"));
		events.add(new Event("11", "iclee141", "10", "lat", "long", "country", "city", "marriage", "1990"));
		events.add(new Event("12", "iclee141", "10", "lat", "long", "country", "city", "death", "1990"));
		
		Event temp = null;
		
		
		Object[] objects = null;
//			new Object[events.size()];
//		for(int i = 0; i < events.size(); i++) {
//			objects[i] = (Event) events.get(i);
//		}
//		System.out.println(objects.getClass());
//		temp = (Event) objects[0];
		
		objects = events.toArray();
		System.out.println(objects[0].getClass());
//		temp = (Event) objects[0];
		
		Event[] eve = new Event[objects.length];
		for(int i = 0; i < objects.length; i++) {
			eve[i] = (Event) objects[i];
		}
		
		System.out.println("----End of tests----");
		
		return;
	}

}

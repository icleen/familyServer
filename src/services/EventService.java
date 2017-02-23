package services;

import model.Event;

public class EventService {
	
	public static Event serve(String id) {
		return new Event(Integer.parseInt(id), "iain", "bob", "1", "1");
	}

}

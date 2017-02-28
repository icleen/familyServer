package services;

import model.Event;

public class EventService {
	
	public static Event serve(String id) {
		return new Event(id, "userName", "1");
	}

}

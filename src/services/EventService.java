package services;

import model.Event;

public class EventService {
	
	public static Event serve(String id) {
		return new Event(id, "1", "1");
	}

}

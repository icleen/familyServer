package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.EventDao;
import model.AuthToken;
import model.Event;
import model.Events;
import model.Message;

public class EventService {
	
	public static Object serve(String authCode, String id) {

		AuthDao aDao = new AuthDao();
		AuthToken token = null;
		try {
			token = aDao.getAuthByCode(authCode);
		} catch (SQLException e1) {
//			e1.printStackTrace();
			String response = "The authCode was incorrect";
			return new Message(response);
		}
		if(token == null) {
			String response = "The authCode was incorrect";
			return new Message(response);
		}
		
		EventDao eDao = new EventDao();
//		if the id is not null, get just the event with the specified id
		if(id != null) {
			Event event = null;
			try {
				event = eDao.getEvent(id);
			} catch (SQLException e) {
//				e.printStackTrace();
				String response = "Could not get the event";
				return new Message(response);
			}
			
			return event;
		}
//		if the id is null, get all of the events associated with the user
		else {
			ArrayList<Event> events = null;
			try {
				events = eDao.getEvents(token.getUserName());
			} catch (SQLException e) {
				e.printStackTrace();
				String response = "Could not get the events";
				return new Message(response);
			}
			return new Events(events.toArray());
		}
	}

}

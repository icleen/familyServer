package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.EventDao;
import dao.PersonDao;
import model.AuthToken;
import model.Event;
import model.Person;

public class EventService {
	
	public static Object serve(String authCode, String id) {

		AuthDao aDao = new AuthDao();
		AuthToken token = null;
		try {
			token = aDao.getAuthByCode(authCode);
		} catch (SQLException e1) {
//			e1.printStackTrace();
			String response = "The authCode was incorrect";
			return response;
		}
		if(token == null) {
			return "The authCode was incorrect";
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
				return response;
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
				return response;
			}
			return events;
		}
	}

}

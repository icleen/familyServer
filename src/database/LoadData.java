package database;

import java.sql.Connection;
import java.sql.SQLException;

import model.Event;
import model.Person;
import model.User;

public class LoadData {
	
	public static String load(User[] users, Person[] people, Event[] events, Connection connection) {
		int i, j, k;
		for(i = 0; i < users.length; i++) {
			try {
				DataPush.pushUser(users[i], connection);
			} catch (SQLException e) {
				return ("user " + i + " failed!");
			} catch (NullPointerException e) {
				System.err.println("There were no users");
			}
		}
		
		for(j = 0; j < people.length; j++) {
			try {
				DataPush.pushPerson(people[j], connection);
			} catch (SQLException e) {
				return ("person " + j + " failed!");
			} catch (NullPointerException e) {
				System.err.println("There were no persons");
			}
		}
		
		for(k = 0; k < events.length; k++) {
			try {
				DataPush.pushEvent(events[k], connection);
			} catch (SQLException e) {
				return ("Event " + k + " failed!");
			} catch (NullPointerException e) {
				System.err.println("There were no events");
			}
		}
		String val = "Successfully added " + i + " users, " + j + " persons, " + k + " events";
		return val;
	}

}

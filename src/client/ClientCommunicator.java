package client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import model.Event;
import model.LoginRequest;
import model.LoginResponse;
import model.Person;
import model.User;
import server.ServerCommunicator;

public class ClientCommunicator extends BaseClientCommunicator {
	
	public static ClientCommunicator SINGLETON = new ClientCommunicator();
	
	public static final String USERNAME_KEY = "username";
	public static final String GENERATIONS_KEY = "gens";
	public static final String PERSONS_KEY = "person";
	public static final String EVENTS_KEY = "event";
	
	public Object hello(Object toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.HELLO_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, toBeSent.getClass());
		return response;
	}
	
	public Object primitive(Object toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.PRIMITIVE_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, toBeSent.getClass());
		return response;
	}
	
	public Object register(User toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.REGISTER_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, LoginResponse.class);
		return response;
	}
	
	public Object login(LoginRequest toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.LOGIN_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, LoginResponse.class);
		return response;
	}
	
	public Object clear() {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.CLEAR_DESIGNATOR, HTTP_POST, authCode, false);
		if(connection == null) {
			return null;
		}
		response = getResponse(connection, String.class);
		return response;
	}
	
	public Object fill(String userName, String generations) {
		Object response = null;
		String header = null;
		if(generations == null) {
			header = "/" + userName;
		}else {
			header = "/" + userName + "/" + generations;
		}
		HttpURLConnection connection = openConnection(ServerCommunicator.FILL_DESIGNATOR, HTTP_POST, authCode, false, header);
		if(connection == null) {
			return null;
		}
		response = getResponse(connection, String.class);
		userName = null;
		generations = null;
		return response;
	}
	
	public Object load(User[] users, Person[] people, Event[] events) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.LOAD_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, users);
		response = getResponse(connection, String.class);
		return response;
	}
	
	public Object event(String eventId) {
		Object response = null;
		String header = null;
		if(eventId != null) {
			header = "/" + eventId;
		}
		HttpURLConnection connection = openConnection(ServerCommunicator.EVENT_DESIGNATOR, HTTP_POST, authCode, false, header);
		if(connection == null) {
			return null;
		}
		response = getResponse(connection, Event.class);
		return response;
	}
	
	public Object person(String personId) {
		Object response = null;
		String header = null;
		if(personId != null) {
			header = "/" + personId;
		}
		HttpURLConnection connection = openConnection(ServerCommunicator.PERSON_DESIGNATOR, HTTP_POST, authCode, false, header);
		if(connection == null) {
			return null;
		}
		response = getResponse(connection, Person.class);
		return response;
	}
	
	
	
	protected HttpURLConnection openConnection(String context,
												String action,
												String authCode,
												boolean sendingToServer,
												String header)
	{
		HttpURLConnection result = null;
		try {
			URL url = new URL(URL_PREFIX + context + header);
			result = (HttpURLConnection) url.openConnection();
			result.setRequestMethod(action);
			result.setDoOutput(sendingToServer);
			result.setRequestProperty(AUTHORIZATION_KEY, authCode);
			
			result.connect();

		} catch (MalformedURLException e) {
			System.err.println("The url did not work! " + e.getMessage());
			return null;
		} catch (IOException e) {
			System.err.println("Could not connect to the server! " + e.getMessage());
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
}

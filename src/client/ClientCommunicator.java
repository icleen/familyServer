package client;

import java.net.HttpURLConnection;

import model.User;
import server.ServerCommunicator;

public class ClientCommunicator extends BaseClientCommunicator {
	
	public static ClientCommunicator SINGLETON = new ClientCommunicator();
	
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
		HttpURLConnection connection = openConnection(ServerCommunicator.PRIMITIVE_DESIGNATOR, HTTP_GET, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, toBeSent.getClass());
		return response;
	}
	
	public Object register(User toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.HELLO_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, toBeSent.getClass());
		return response;
	}
	
}

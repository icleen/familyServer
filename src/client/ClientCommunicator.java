package client;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import server.Server;

public class ClientCommunicator {
	
	public static ClientCommunicator SINGLETON = new ClientCommunicator();
	
	private static final String SERVER_HOST = "localhost";
	private static final String URL_PREFIX = "http://" + SERVER_HOST + ":" + Server.getServerPortNumber();
	private static final String HTTP_POST = "POST";
	private static final String COMMAND_HANDLER_DESIGNATOR = "";
	
	ClientCommunicator() {
	}
	
	public Object send() {
		Object response = null;
		
		HttpURLConnection connection = 
				openConnection(COMMAND_HANDLER_DESIGNATOR, null, false);
		if(connection == null) {
			return null;
		}
		response = getResponse(connection);
		return response;
	}
	
	private HttpURLConnection openConnection(String context, 
											String authCode,
											boolean sendingToServer)
	{
		HttpURLConnection result = null;
		try {
			URL url = new URL(URL_PREFIX + context);
			result = (HttpURLConnection) url.openConnection();
			result.setRequestMethod(HTTP_POST);
			result.setDoOutput(sendingToServer);
			result.setRequestProperty(AUTHORIZATION_KEY, authCode);
			result.connect();
			
		} catch (MalformedURLException e) {
			System.err.println("The url did not work!");
			return null;
		} catch (IOException e) {
			System.err.println("Could not connect to the server!");
			return null;
		}
		
		return result;
	}
	
	private Object getResponse(HttpURLConnection connection) {
		Object result = null;
		
		try {
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				int length = connection.getContentLength();
				if(length == 0) { // 0 indicates an empty response body
					System.out.println("There was nothing in the response body");
				}else if( length == -1) { // -1 indicates unknown amount of info
					System.out.println("There was something there");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				connection.getInputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public static void main(String args[]) {
		ClientCommunicator.SINGLETON.send();
	}
	
	public static String AUTHORIZATION_KEY = "myAuth";
	
}

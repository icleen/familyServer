package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import server.ServerCommunicator;

public class ClientCommunicator {
	
	public static ClientCommunicator SINGLETON = new ClientCommunicator();
	
	private static final String SERVER_HOST = "localhost";
	private static final String URL_PREFIX = "http://" + SERVER_HOST + ":" + ServerCommunicator.getServerPortNumber();
	private static final String HTTP_POST = "POST";
	private static final String HTTP_GET = "GET";
	
	public static String AUTHORIZATION_KEY = "myAuth";
	private static String authCode;
	
	private static Gson gson = new Gson();
	
	ClientCommunicator() {
	}
	
	public Object hello(Object toBeSent) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.HELLO_DESIGNATOR, HTTP_POST, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, toBeSent);
		response = getResponse(connection, String.class);
		return response;
	}
	
	public Object primitive(Object object) {
		Object response = null;
		HttpURLConnection connection = openConnection(ServerCommunicator.PRIMITIVE_DESIGNATOR, HTTP_GET, authCode, true);
		if(connection == null) {
			return null;
		}
		sendToServer(connection, 12);
		response = getResponse(connection, Integer.class);
		return response;
	}
	
	private void sendToServer(HttpURLConnection connection, Object toBeSent) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(connection.getOutputStream());
			gson.toJson(toBeSent, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) {
				writer.close();
			}
		}
		return;
	}	
	
	private Object getResponse(HttpURLConnection connection, Class<?> klass) {
		Object result = null;
		
		try {
			if(connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				int length = connection.getContentLength();
				if(length == 0) { 
//					0 indicates an empty response body
					System.out.println("There was nothing in the response body");
				}else if( length == -1) { 
//					-1 indicates unknown amount of info
					System.out.println("There was something there");
					Scanner scanner = new Scanner(connection.getInputStream());
					result = scanner.nextLine();
					scanner.close();
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
	
	private HttpURLConnection openConnection(String context,
											String action,
											String authCode,
											boolean sendingToServer)
	{
		HttpURLConnection result = null;
		try {
			URL url = new URL(URL_PREFIX + context);
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
			return null;
		}
		
		return result;
	}
	
}

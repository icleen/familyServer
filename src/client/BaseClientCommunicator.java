package client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import server.ServerCommunicator;

public class BaseClientCommunicator {
	public static BaseClientCommunicator SINGLETON;
	
	protected static final String SERVER_HOST = "localhost";
	protected static final String URL_PREFIX = "http://" + SERVER_HOST + ":" + ServerCommunicator.getServerPortNumber();
	protected static final String HTTP_POST = "POST";
	protected static final String HTTP_GET = "GET";
	
	public static String AUTHORIZATION_KEY = "myAuth";
	protected static String authCode;
	
	protected static Gson gson = new Gson();
	
	BaseClientCommunicator() {
	}
	
	protected void sendToServer(HttpURLConnection connection, Object toBeSent) {
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
	
	protected Object getResponse(HttpURLConnection connection, Class<?> klass) {
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
	
	protected HttpURLConnection openConnection(String context,
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
			e.printStackTrace();
			return null;
		}
		
		return result;
	}
}

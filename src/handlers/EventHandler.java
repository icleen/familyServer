package handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.InvalidPathException;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import client.ClientCommunicator;
import model.Event;
import server.ServerCommunicator;
import services.EventService;

public class EventHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println();
		System.out.println("Starting event handler");
		
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		System.out.println("Path: " + path);
		
		String[] pathParts = path.split("/");
		String id = null;
		if(pathParts.length == 3) {
			id = pathParts[2];
		}else if(pathParts.length > 3) {
			throw new InvalidPathException(path, "The path had too many segments");
		}
		
		Headers headers = exchange.getRequestHeaders();
		String authCode = headers.getFirst(ClientCommunicator.AUTHORIZATION_KEY);
		System.out.println("the authCode is: " + authCode);
		System.out.println("the event id is: " + id);
		
		Object response = EventService.serve(authCode, id);
		
		System.out.print(response);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		/**
		 * Use this for the app when you know the client will be expected a json file
		 */
		if(!ServerCommunicator.sendingToBrowser) {
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
			gson.toJson(response, outputStreamWriter);
			outputStreamWriter.close();
		}
		
		/**
		 * when you run this for the browser you will not be using the gson command because that won't work.  
		 * You want to just write an array of bytes to the OutputStreamWriter and when you close it will go to the browser.
		 * You also need it to match the expected response on their website (browser)
		 */
		if(ServerCommunicator.sendingToBrowser) {
			OutputStream writer = exchange.getResponseBody();
			writer.write(response.toString().getBytes());

			writer.close();
		}
		
	}

}

package handlers;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.InvalidPathException;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.Message;
import server.ServerCommunicator;
import services.EventService;

public class EventHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
//		System.out.println();
//		System.out.println("Starting event handler");
		
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
//		System.out.println("Path: " + path);
		
		String[] pathParts = path.split("/");
		String id = null;
		if(pathParts.length == 3) {
			id = pathParts[2];
		}else if(pathParts.length > 3) {
			throw new InvalidPathException(path, "The path had too many segments");
		}
		
		Headers headers = exchange.getRequestHeaders();
		String authCode = headers.getFirst(ServerCommunicator.AUTHORIZATION);
//		System.out.println("the authCode is: " + authCode);
//		System.out.println("the event id is: " + id);
		
		Object response = EventService.serve(authCode, id);
//		if(response.getClass() == Message.class) {
//			System.out.println(response);
//		}
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);
		outputStreamWriter.close();
	}

}

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
import services.FillService;

public class FillHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	private static String DEFAULT_GENERATIONS = "4";
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
//		System.out.println();
//		System.out.println("Starting fill handler");
		
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
//		System.out.println("Path: " + path);
		
		String[] pathParts = path.split("/");
		String userName = null;
		String gens = null;
		if(pathParts.length == 3) {
			userName = pathParts[2];
			gens = DEFAULT_GENERATIONS;
		}else if(pathParts.length == 4) {
			userName = pathParts[2];
			gens = pathParts[3];
		}else if(pathParts.length > 4) {
			throw new InvalidPathException(path, "The path had too many segments");
		}
		
		Headers headers = exchange.getRequestHeaders();
//		System.out.println("the authCode is: " + headers.getFirst(ClientCommunicator.AUTHORIZATION_KEY));
//		System.out.println("the userName is: " + userName);
//		System.out.println("the generations are: " + gens);
		
		Message response = FillService.serve(userName, gens);
//		System.out.println(response);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);
		outputStreamWriter.close();
	}

}

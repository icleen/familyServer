package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import client.ClientCommunicator;
import model.User;
import services.FillService;

public class FillHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println();
		System.out.println("Starting fill handler");
		
		Headers headers = exchange.getRequestHeaders();
		String userName = headers.getFirst(ClientCommunicator.USERNAME_KEY);
		String gens = headers.getFirst(ClientCommunicator.GENERATIONS_KEY);
		System.out.println("the authCode is: " + headers.getFirst(ClientCommunicator.AUTHORIZATION_KEY));
		System.out.println("the userName is: " + userName);
		System.out.println("the generations are: " + gens);
		
		String response = FillService.serve(userName, gens);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);

		outputStreamWriter.close();
	}

}

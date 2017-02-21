package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import client.ClientCommunicator;

public class PrimitiveHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers headers = exchange.getRequestHeaders();
		String authCode = headers.getFirst(ClientCommunicator.AUTHORIZATION_KEY);
		System.out.println("The authorization code in sendingPrimitiveHandler = " + authCode);
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		float floatNumber = (float) gson.fromJson(inputStreamReader, float.class);
		System.out.println("The float transmitted  = '" + floatNumber + "'");
		inputStreamReader.close();
		
		//-1 means the response body is empty
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, -1);
	}

}

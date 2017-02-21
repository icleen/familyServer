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

public class PrimitiveHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		Headers headers = exchange.getRequestHeaders();
		String authCode = headers.getFirst(ClientCommunicator.AUTHORIZATION_KEY);
		System.out.println("The authorization code in PrimitiveHandler = " + authCode);
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		Object object = (Object) gson.fromJson(inputStreamReader, Object.class);
		System.out.println("The object  = '" + object + "'");
		inputStreamReader.close();
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson("Primitive worked!", outputStreamWriter);

		outputStreamWriter.close();
	}

}

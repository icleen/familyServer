package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.LoginResponse;
import model.User;

public class RegisterHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		
		System.out.println("Starting handler");
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		User object = (User) gson.fromJson(inputStreamReader, User.class);
		inputStreamReader.close();
		System.out.println("The object  = '" + object.toString() + "'");
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		LoginResponse response = new LoginResponse("bobobob", object.getUserName(), "1");
		gson.toJson(response, outputStreamWriter);

		outputStreamWriter.close();
		
	}

}

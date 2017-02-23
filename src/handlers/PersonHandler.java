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
import services.LoginService;

public class PersonHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println("Starting person handler");
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		User user = (User) gson.fromJson(inputStreamReader, User.class);
		inputStreamReader.close();
		System.out.println("The user  = '" + user.toString() + "'");
		
		LoginResponse response = LoginService.serve(user);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);

		outputStreamWriter.close();
	}

}

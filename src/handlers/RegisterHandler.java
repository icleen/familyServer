package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.LoginResponse;
import model.User;
import server.ServerCommunicator;
import services.RegisterService;

public class RegisterHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println();
		System.out.println("Starting register handler");
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		User user = (User) gson.fromJson(inputStreamReader, User.class);
		inputStreamReader.close();
		System.out.println("The user  = '" + user.toString() + "'");
		
		LoginResponse response = RegisterService.register(user);
		
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

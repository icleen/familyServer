package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.LoginRequest;
import model.LoginResponse;
import model.User;
import server.ServerCommunicator;
import services.LoginService;

public class LoginHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
//		System.out.println();
//		System.out.println("Starting login handler");
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		LoginRequest request = (LoginRequest) gson.fromJson(inputStreamReader, LoginRequest.class);
		inputStreamReader.close();
//		System.out.println("The request  = '" + request.toString() + "'");
		
		LoginResponse response = LoginService.serve(request);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);
		outputStreamWriter.close();
	}

}

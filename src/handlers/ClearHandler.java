package handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import services.ClearService;

public class ClearHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println();
		System.out.println("Starting clear handler");
		
		String response = ClearService.serve();
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);
		outputStreamWriter.close();
	}

}

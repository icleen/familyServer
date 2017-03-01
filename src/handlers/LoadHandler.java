package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import model.LoadRequest;
import services.LoadService;

public class LoadHandler implements HttpHandler {
	
	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		System.out.println();
		System.out.println("Starting load handler");
		
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		LoadRequest request = (LoadRequest) gson.fromJson(inputStreamReader, LoadRequest.class);
		inputStreamReader.close();
		
		String response = LoadService.serve(request);
		System.out.println(response);
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson(response, outputStreamWriter);
		outputStreamWriter.close();
	}

}

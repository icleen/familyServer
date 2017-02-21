package handlers;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HelloWorldHandler implements HttpHandler {

	private static Gson gson = new Gson();
	
	@Override
	public void handle(HttpExchange exchange) throws IOException {
		InputStreamReader inputStreamReader = new InputStreamReader(exchange.getRequestBody());
		String object = (String) gson.fromJson(inputStreamReader, String.class);
		inputStreamReader.close();
		System.out.println(object + " World!");
		
		exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);
		// 0 means the response body has an unknown amount of stuff in it
		
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(exchange.getResponseBody());
		gson.toJson("Hello worked!", outputStreamWriter);

		outputStreamWriter.close();
	}

}

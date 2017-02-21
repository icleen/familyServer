package handlers;

import java.io.IOException;
import java.net.HttpURLConnection;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class HelloWorldHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange arg0) throws IOException {
		System.out.println("Hello World!");
//		-1 means the response body is empty
		arg0.sendResponseHeaders(HttpURLConnection.HTTP_OK, -1);
	}

}

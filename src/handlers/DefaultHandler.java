package handlers;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.ServerCommunicator;

public class DefaultHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		URI uri = exchange.getRequestURI();
		String pathString = uri.getPath();
		
		String [] pathParts = pathString.split("/");
		StringBuilder pathSegments = new StringBuilder();
		for(int i = 1; i < pathParts.length; i++) {
			pathSegments.append(pathParts[i]);
			if(i < pathParts.length - 1) {
				pathSegments.append(", ");
			}
		}
		System.out.println("    PathString parts = " + pathSegments.toString());
		
		if(pathString.equals("/")) {
			pathString = DEFAULT_PATH;
		}
		
		int responseCode = 0;
		int bodyIsEmptyCode = 0;
		Path path = Paths.get(ServerCommunicator.HTTP_ROOT + pathString);
		byte[] result = new byte[0];
		try {
			result = Files.readAllBytes(path);
			responseCode = HttpURLConnection.HTTP_OK;
			bodyIsEmptyCode = 0;
		} catch (IOException e) {
			try {
				path = Paths.get(ServerCommunicator.HTTP_ROOT + LOC_404);
				result = Files.readAllBytes(path);
				responseCode = HttpURLConnection.HTTP_NOT_FOUND;
				bodyIsEmptyCode = 0;
				System.out.println("result = " + result);
			} catch (IOException error404) {
				bodyIsEmptyCode = -1;
				responseCode = HttpURLConnection.HTTP_NOT_FOUND;
			}
		}
		
		//0 means something is coming back
		exchange.sendResponseHeaders(responseCode, bodyIsEmptyCode);
		
		OutputStream os = exchange.getResponseBody();
		os.write(result);
		os.close();
	}

	public final String DEFAULT_PATH = "/index.html";
	public final String LOC_404 = "/404.html";
	
}

package handlers;

import java.io.File;
import java.io.IOException;
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
		String path = uri.getPath();
		System.out.println("Path: " + path);
		
		String[] pathParts = path.split("/");
		StringBuilder segments = new StringBuilder();
		for(int i = 0; i < pathParts.length; i++) {
			segments.append(pathParts[i]);
			if(i < pathParts.length - 1) {
				segments.append(", ");
			}
		}
		System.out.println("Path parts: " + segments.toString());
		
//		switch(path) {
//		case "/":
////			blah
//			break;
//		case "/clear/other":
////			blah
//			break;
//			default:
////				bleh
//				
//		}
		
		File file = new File(ServerCommunicator.HTTP_ROOT + index_html_location);
		byte[] result = getBytesFromTextFile(file);
		
	}
	
	private byte[] getBytesFromTextFile(File file) {
//		read in from file through scanner and append to a StringBuilder and then return result.toString().toBytes();
		StringBuilder result = new StringBuilder();
		return result.toString().getBytes();
	}
	
	private byte[] getBytesFromNonTextFile(String pathString) {
		Path path = Paths.get(ServerCommunicator.HTTP_ROOT + pathString);
//		read in from file through scanner and append to a StringBuilder and then return result.toString().toBytes();
		byte[] result = new byte[0];
		try {
			result = Files.readAllBytes(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result.toString().getBytes();
	}

	public final String index_html_location = "";
	
}

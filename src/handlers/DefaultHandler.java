package handlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import server.ServerCommunicator;

public class DefaultHandler implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		ServerCommunicator.sendingToBrowser = true;
		
		URI uri = exchange.getRequestURI();
		String path = uri.getPath();
		System.out.println("Path: " + path);
		
		String[] pathParts = path.split("/");
		System.out.println("pathParts.length: " + pathParts.length);
		for(int i = 0; i < pathParts.length; i++) {
			System.out.println(pathParts[i]);
		}
		
		if(pathParts.length == 0) {
			OutputStream writer = exchange.getResponseBody();
			writer.write( getBytesFromTextFile(new File(ServerCommunicator.HTTP_ROOT + index_html_location)) );

			writer.close();
		}else if(pathParts[1].equals("favicon.ico")) {
			OutputStream writer = exchange.getResponseBody();
			writer.write( getBytesFromNonTextFile(pathParts[0]) );

			writer.close();
		}
	}
	
	private byte[] getBytesFromTextFile(File file) {
//		read in from file through scanner and append to a StringBuilder and then return result.toString().toBytes();
		StringBuilder result = new StringBuilder();
		try {
			Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)));
			while(scanner.hasNext()) {
				result.append(scanner.nextLine());
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public final String index_html_location = "index.html";
	
}

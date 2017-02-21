package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import handlers.HelloWorldHandler;
import handlers.PrimitiveHandler;

public class Server {
	
	private static final int SERVER_PORT_NUMBER = 3722;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	private HttpHandler myHandler = new HelloWorldHandler();
	private HttpHandler primitiveHandler = new PrimitiveHandler();
	
	private Server() {
	}
	
	private void run() {
		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {
			System.out.println("Could not create HTTP server: " + e.getMessage());
			e.printStackTrace();
			return;
		}
		server.setExecutor(null); // use the default executor
		server.createContext("/", myHandler);
		server.createContext("/", primitiveHandler);
		server.start();
	}
	
	public static void main(String args[]) {
		new Server().run();
	}
	
	public static int getServerPortNumber() {
		return SERVER_PORT_NUMBER;
	}
	
}

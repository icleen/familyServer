package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import handlers.HelloWorldHandler;
import handlers.PrimitiveHandler;

public class ServerCommunicator {
	
	private static final int SERVER_PORT_NUMBER = 3722;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	private HttpHandler helloHandler = new HelloWorldHandler();
	private HttpHandler primitiveHandler = new PrimitiveHandler();
	
	public static final String HELLO_DESIGNATOR = "/hello";
	public static final String PRIMITIVE_DESIGNATOR = "/primitive";
	
	private ServerCommunicator() {
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
		server.createContext(HELLO_DESIGNATOR, helloHandler);
		server.createContext(PRIMITIVE_DESIGNATOR, primitiveHandler);
		server.start();
	}
	
	public static void main(String args[]) {
		new ServerCommunicator().run();
	}
	
	public static int getServerPortNumber() {
		return SERVER_PORT_NUMBER;
	}
	
}

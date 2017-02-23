package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import handlers.ClearHandler;
import handlers.EventHandler;
import handlers.FillHandler;
import handlers.HelloWorldHandler;
import handlers.LoadHandler;
import handlers.LoginHandler;
import handlers.PersonHandler;
import handlers.PrimitiveHandler;
import handlers.RegisterHandler;

public class ServerCommunicator {
	
	private static final int SERVER_PORT_NUMBER = 3722;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	private HttpHandler helloHandler = new HelloWorldHandler();
	private HttpHandler primitiveHandler = new PrimitiveHandler();
	private HttpHandler registerHandler = new RegisterHandler();
	private HttpHandler loginHandler = new LoginHandler();
	private HttpHandler clearHandler = new ClearHandler();
	private HttpHandler fillHandler = new FillHandler();
	private HttpHandler loadHandler = new LoadHandler();
	private HttpHandler personHandler = new PersonHandler();
	private HttpHandler eventHandler = new EventHandler();
	
	public static final String HELLO_DESIGNATOR = "/hello";
	public static final String PRIMITIVE_DESIGNATOR = "/primitive";
	public static final String REGISTER_DESIGNATOR = "/user/register";
	public static final String LOGIN_DESIGNATOR = "/user/login";
	public static final String CLEAR_DESIGNATOR = "/clear";
	public static final String FILL_DESIGNATOR = "/fill/*/*";
	public static final String LOAD_DESIGNATOR = "/load";
//	public static final String PERSONID_DESIGNATOR = "/person/*";
	public static final String PERSON_DESIGNATOR = "/person";
//	public static final String EVENTID_DESIGNATOR = "/event/*";
	public static final String EVENT_DESIGNATOR = "/event";
	
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
		server.createContext(REGISTER_DESIGNATOR, registerHandler);
		server.createContext(LOGIN_DESIGNATOR, this.loginHandler);
		server.createContext(CLEAR_DESIGNATOR, this.clearHandler);
		server.createContext(FILL_DESIGNATOR, this.fillHandler);
		server.createContext(LOAD_DESIGNATOR, this.loadHandler);
//		server.createContext(PERSONID_DESIGNATOR, this.personHandler);
		server.createContext(PERSON_DESIGNATOR, this.personHandler);
//		server.createContext(EVENTID_DESIGNATOR, this.eventHandler);
		server.createContext(EVENT_DESIGNATOR, this.eventHandler);
		
		server.start();
	}
	
	public static void main(String args[]) {
		new ServerCommunicator().run();
	}
	
	public static int getServerPortNumber() {
		return SERVER_PORT_NUMBER;
	}
	
}

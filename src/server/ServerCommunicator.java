package server;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import handlers.ClearHandler;
import handlers.DefaultHandler;
import handlers.EventHandler;
import handlers.FillHandler;
import handlers.LoadHandler;
import handlers.LoginHandler;
import handlers.PersonHandler;
import handlers.RegisterHandler;

public class ServerCommunicator {
	
	private static final int SERVER_PORT_NUMBER = 3740;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	
	private HttpServer server;
	
	private HttpHandler registerHandler = new RegisterHandler();
	private HttpHandler loginHandler = new LoginHandler();
	private HttpHandler clearHandler = new ClearHandler();
	private HttpHandler fillHandler = new FillHandler();
	private HttpHandler loadHandler = new LoadHandler();
	private HttpHandler personHandler = new PersonHandler();
	private HttpHandler eventHandler = new EventHandler();
	private HttpHandler defaultHandler = new DefaultHandler();
	
	public static final String REGISTER_DESIGNATOR = "/user/register";
	public static final String LOGIN_DESIGNATOR = "/user/login";
	public static final String CLEAR_DESIGNATOR = "/clear";
	public static final String FILL_DESIGNATOR = "/fill";
	public static final String LOAD_DESIGNATOR = "/load";
	public static final String PERSON_DESIGNATOR = "/person";
	public static final String EVENT_DESIGNATOR = "/event";
	public static final String DEFAULT_DESIGNATOR = "/";
	
	public static final String AUTHORIZATION = "Authorization";
	
	public static final String HTTP_ROOT = "http_root/familymapserver/data/HTML";
	
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
		
		server.createContext(REGISTER_DESIGNATOR, registerHandler);
		server.createContext(LOGIN_DESIGNATOR, this.loginHandler);
		server.createContext(CLEAR_DESIGNATOR, this.clearHandler);
		server.createContext(FILL_DESIGNATOR, this.fillHandler);
		server.createContext(LOAD_DESIGNATOR, this.loadHandler);
		server.createContext(PERSON_DESIGNATOR, this.personHandler);
		server.createContext(EVENT_DESIGNATOR, this.eventHandler);
		server.createContext(DEFAULT_DESIGNATOR, this.defaultHandler);
		
		server.start();
	}
	
	public static void main(String args[]) {
		new ServerCommunicator().run();
	}
	
	public static int getServerPortNumber() {
		return SERVER_PORT_NUMBER;
	}
	
}

package services;

import dao.Connector;

public class ClearService {
	
	public static String serve() {
		Connector.initializeTable();
		return "You cleared the database!";
	}

}

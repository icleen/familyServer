package services;

import dao.Dao;

public class ClearService {
	
	public static String serve() {
		Dao.initializeTable();
		return "You cleared the database!";
	}

}

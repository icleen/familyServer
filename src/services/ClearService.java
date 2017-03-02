package services;

import dao.Dao;
import model.Message;

public class ClearService {
	
	public static Message serve() {
		Dao.initializeTable();
		String result = "You cleared the database!";
		return new Message(result);
	}

}

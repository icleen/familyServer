package test;

import database.InitializeTable;
import model.User;

public class Test {
	
	public static void main(String args[]) {
		
		InitializeTable.init();
		new User("iclee141", "bob", "icleen", "iain", "lee", "male");
		
	}

}

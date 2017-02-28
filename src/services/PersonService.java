package services;

import model.Person;

public class PersonService {
	
	public static Person serve(String id) {
		return new Person(id, "1", "userName", "bob", "Blonderson", "female");
	}

}

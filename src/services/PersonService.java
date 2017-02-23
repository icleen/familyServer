package services;

import model.Person;

public class PersonService {
	
	public static Person serve(String id) {
		return new Person(Integer.parseInt(id), "bob", "Blonderson", "female");
	}

}

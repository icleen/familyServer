package services;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.AuthDao;
import dao.PersonDao;
import model.AuthToken;
import model.Message;
import model.ObjectResponse;
import model.Person;

public class PersonService {
	
	public static Object serve(String authCode, String id) {
		
		AuthDao aDao = new AuthDao();
		AuthToken token = null;
		try {
			token = aDao.getAuthByCode(authCode);
		} catch (SQLException e1) {
//			e1.printStackTrace();
			String response = "The authCode was incorrect";
			return new Message(response);
		}
		if(token == null) {
			String response = "The authCode was incorrect";
			return new Message(response);
		}
		
		PersonDao pDao = new PersonDao();
//		if the id is not null, get just the person with the specified id
		if(id != null) {
			Person person = null;
			try {
				person = pDao.getPerson(id);
			} catch (SQLException e) {
//				e.printStackTrace();
				String response = "Could not get the person";
				return new Message(response);
			}
			
			return person;
		}
//		if the id is null, get all of the people associated with the user
		else {
			ArrayList<Person> people = null;
			try {
				people = pDao.getPeople(token.getUserName());
			} catch (SQLException e) {
				e.printStackTrace();
				String response = "Could not get the people";
				return new Message(response);
			}
			return new ObjectResponse(people.toArray());
		}
		
	}
	
//	private static String toString(Person p, String user) {
//		StringBuilder output = new StringBuilder();
//		output.append("{\n");
//		output.append("descendant:\"" + user + "\",\n");
//		output.append("personId:\"" + p.getId() + "\"\n");
//		output.append("firstName:\"" + p.getFirstName() + "\"\n");
//		output.append("lastName:\"" + p.getLastName() + "\"\n");
//		output.append("gender:\"" + p.getGender() + "\"\n");
//		output.append("father:\"" + p.getFather() + "\"\n");
//		output.append("mother:\"" + p.getMother() + "\"\n");
//		output.append("spouse:\"" + p.getSpouse() + "\"\n");
//		output.append("}\n");
//		return output.toString();
//	}
//	
//	private static String toString(ArrayList<Person> people, String user) {
//		StringBuilder output = new StringBuilder();
//		output.append("\"data\": [\n");
//		for(int i = 0; i < people.size(); i++) {
//			Person p = people.get(i);
//			output.append("{\n");
//			output.append("descendant:\"" + user + "\",\n");
//			output.append("personId:\"" + p.getId() + "\"\n");
//			output.append("firstName:\"" + p.getFirstName() + "\"\n");
//			output.append("lastName:\"" + p.getLastName() + "\"\n");
//			output.append("gender:\"" + p.getGender() + "\"\n");
//			output.append("father:\"" + p.getFather() + "\"\n");
//			output.append("mother:\"" + p.getMother() + "\"\n");
//			output.append("spouse:\"" + p.getSpouse() + "\"\n");
//			output.append("}\n");
//		}
//		output.append("]");
//		return output.toString();
//	}

}

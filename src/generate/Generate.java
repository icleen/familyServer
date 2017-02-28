package generate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import dao.PersonDao;
import model.Person;

public class Generate {
	
	private int MAX;
	private int current;
	private ArrayList<Person> people;
	private String userName;
	
	private static int id = 1010;
	
	private String[] maleNames = {
			"Iain", "Bob", "Anakin", "Lucas", "Brigham", "Joseph", "Obiwan", "Taylor", "Kyle", "Mace"
	};
	
	private String[] femaleNames = {
			"Kelly", "Bobina", "Jyn", "Rey", "Leia", "Emma", "Obiwan", "Taylor", "Lacy", "Padme"
	};
	
	private String[] lastNames = {
			"Lee", "Burt", "Skywalker", "Young", "Smitherson", "Kenobi", "Evert", "Whatcott", "Windu"
	};
	
	public void generatePeople(int number, String userName) {
		people = new ArrayList<>();
		this.userName = userName;
		MAX = number;
		current = 0;
		PersonDao pDao = new PersonDao();
		
		recurse();
		
		for(int i = 0; i < people.size(); i++) {
			try {
				pDao.addPerson(people.get(i));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private ArrayList<String> recurse() {
		if(current == MAX) {
			return null;
		}
		current++;
		Random ran = new Random();
		ArrayList<String> hp = recurse();
		String hf = null, hm = null, wf = null, wm = null;
		if(hp != null) {
			
		}
		ArrayList<String> wp = recurse();
		int index = ran.nextInt(maleNames.length);
		String hName = maleNames[index];
		index = ran.nextInt(femaleNames.length);
		String wName = femaleNames[index];
		index = ran.nextInt(lastNames.length);
		String lName = lastNames[index];
		
		String husband = nextId();
		String wife = nextId();
		Person h = new Person(husband, userName, hName, lName, "m", hParents.get(0), hParents.get(1), wife);
		Person w = new Person(wife, userName, wName, lName, "f", wParents.get(0), wParents.get(1), husband);
		people.add(h);
		people.add(w);
		ArrayList<String> temp = new ArrayList<>();
		temp.add(h.getId());
		temp.add(w.getId());
		return temp;
	}
	
	private ArrayList<Person> generateCouple() {
		ArrayList<Person> people = new ArrayList<>();
		Random ran = new Random();
		
		PersonDao pDao = new PersonDao();
		
		int index = ran.nextInt(maleNames.length);
		String hName = maleNames[index];
		index = ran.nextInt(femaleNames.length);
		String wName = femaleNames[index];
		index = ran.nextInt(lastNames.length);
		String lName = lastNames[index];
		
		String husband = nextId();
		String wife = nextId();
		
		people.add(new Person(husband, userName, hName, lName, "m", null, null, wife));
		people.add(new Person(wife, userName, wName, lName, "f", null, null, husband));
		
		return people;
	}
	
	private String nextId() {
		return (new StringBuilder(id++).toString());
	}
	
}

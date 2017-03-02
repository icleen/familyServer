package generate;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Dao;
import model.Event;
import model.Location;
import model.Person;

public class Generate {
	
	private int MAX;
//	private int current;
	private ArrayList<Person> people;
	private String userName;
	
	private Events eventData;
	private ArrayList<Event> events;
	private int eventId = 2010;
	
	private int id = 1010;
	private NameStore nameStore;
	
	private static final int curYear = 2017;
	private static final int oneGen = 60;
	
	public Generate() {
		nameStore = new NameStore();
		nameStore.importJson();
		eventData = new Events();
		eventData.importJson();
		events = new ArrayList<>();
		
		Dao dao = new Dao();
		int[] temp = null;
		try {
			temp = dao.getNextId();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		id = temp[0];
		eventId = temp[1];
	}
	
	public Generate(int personId, int eventId) {
		nameStore = new NameStore();
		nameStore.importJson();
		eventData = new Events();
		eventData.importJson();
		events = new ArrayList<>();
		this.id = personId;
		this.eventId = eventId;
	}
	
	public ArrayList<Person> generatePeople(int generations, String userName) {
		people = new ArrayList<>();
		events = new ArrayList<>();
		this.userName = userName;
		MAX = generations;
//		PersonDao pDao = new PersonDao();
		
		recurse(0);
		updateID();
		return people;
	}
	
	private ArrayList<String> recurse(int current) {
		if(current >= MAX) {
			return null;
		}
		ArrayList<String> hp = recurse(current + 1);
		String hf = null, hm = null, wf = null, wm = null;
		if(hp != null) {
			hf = hp.get(0);
			hm = hp.get(1);
		}
		ArrayList<String> wp = recurse(current + 1);
		if(wp != null) {
			wf = wp.get(0);
			wm = wp.get(1);
		}
		String hName = nameStore.getRandomMalename();
		String wName = nameStore.getRandomFemalename();
		String lName = nameStore.getRandomSurname();
		
		String husband = nextId();
		String wife = nextId();
		Person h = new Person(husband, userName, hName, lName, "m", hf, hm, wife);
		Person w = new Person(wife, userName, wName, lName, "f", wf, wm, husband);
		people.add(h);
		people.add(w);
//		generate the events now
		generateEvents(husband, userName, current);
		generateEvents(wife, userName, current);
		ArrayList<String> temp = new ArrayList<>();
		temp.add(h.getId());
		temp.add(w.getId());
		return temp;
	}
	
	public String nextId() {
		String s = ("" + id++);
//		System.out.println(s);
		return s;
	}
	
	public void updateID() {
		Dao dao = new Dao();
		int[] temp = { id, eventId };
		try {
			dao.putId(temp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private String nextEventId() {
		String s = ("" + eventId++);
		return s;
	}
	
	public ArrayList<Event> generateEvents(String personId, String userName, int generation) {
		ArrayList<Event> temp = new ArrayList<>();
		Location loc = eventData.getRandomLocation();
		int year = curYear - (generation * oneGen + 21);
		temp.add( new Event(nextEventId(), userName, personId, loc.latitude, loc.longitude, loc.country, loc.city, "birth", 
				"" + year) );
		temp.add( new Event(nextEventId(), userName, personId, loc.latitude, loc.longitude, loc.country, loc.city, "baptism", 
				"" + (year + 8)) );
		temp.add( new Event(nextEventId(), userName, personId, loc.latitude, loc.longitude, loc.country, loc.city, "marriage", 
				"" + (year + 20)) );
		if(year + 80 < curYear) {
			temp.add( new Event(nextEventId(), userName, personId, loc.latitude, loc.longitude, loc.country, loc.city, "death", 
					"" + (year + 80)) );
		}else {
			temp.add( new Event(nextEventId(), userName, personId, null, null, null, null, "death", null) );
		}
		
		events.addAll(temp);
		return temp;
	}
	
	public ArrayList<Event> getEvents() {
		return events;
	}
	
}

package model;

public class Event {
	
	private final int id;
	private final Person descendant;
	private final Person person;
	
	private String latitude;
	private String longitude;
	private String country;
	private String city;
	private String type;
	private String year;
	
	public Event(int id, Person descendant, Person person) {
		this.id = id;
		this.descendant = descendant;
		this.person = person;
	}
	
	public Event(int id, Person descendant, Person person, String latitude, String longitude, 
			String country, String city, String type, String year) {
		this.id = id;
		this.descendant = descendant;
		this.person = person;
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.city = city;
		this.type = type;
		this.year = year;
	}
	
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return this.city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getId() {
		return id;
	}
	public Person getDescendant() {
		return descendant;
	}
	public Person getPerson() {
		return person;
	}
	
}

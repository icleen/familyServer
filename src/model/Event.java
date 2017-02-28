package model;

public class Event {
	
	private final String eventId;
//	private final String userId;
	private String userName;
	private final String personId;
	
	private String latitude;
	private String longitude;
	private String country;
	private String city;
	private String type;
	private String year;
	
	public Event(String eventId, String userName, String personId) {
		this.eventId = eventId;
//		this.userId = userId;
		this.userName = userName;
		this.personId = personId;
	}
	
	public Event(String eventId, String userName, String personId, String latitude, String longitude, 
			String country, String city, String type, String year) {
		this.eventId = eventId;
//		this.userId = userId;
		this.userName = userName;
		this.personId = personId;
		
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
	public String getPersonId() {
		return personId;
	}
	public String getEventId() {
		return eventId;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getUserName() {
		return userName;
	}

//	public String getUserId() {
//		return userId;
//	}
	
	public String toString() {
		StringBuilder output = new StringBuilder();
		
		output.append("descendant: " + this.userName + "\",\n");
		output.append("eventId:\"" + this.eventId + "\",\n");
		output.append("personId:\"" + this.personId + "\",\n");
		output.append("lattitude:\"" + this.latitude + "\",\n");
		output.append("longitude:\"" + this.longitude + "\",\n");
		output.append("country:\"" + this.country + "\",\n");
		output.append("city:\"" + this.city + "\",\n");
		output.append("eventType:\"" + this.type + "\",\n");
		output.append("year:\"" + this.year + "\",\n");
		
		return output.toString();
	}
	
}

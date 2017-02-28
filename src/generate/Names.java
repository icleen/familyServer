package generate;

import java.util.Random;

public class Names {
	
	private String[] maleFnames = {
			"Iain", "Bob", "Anakin", "Lucas", "Brigham", "Joseph", "Obiwan", "Taylor", "Kyle", "Mace"
	};
	
	private String[] femaleFnames = {
			"Kelly", "Bobina", "Jyn", "Rey", "Leia", "Emma", "Obiwan", "Taylor", "Lacy", "Padme"
	};
	
	private String[] lastNames = {
			"Lee", "Burt", "Skywalker", "Young", "Smitherson", "Kenobi", "Evert", "Whatcott", "Windu"
	};
	
	public String maleFirstName() {
		
		Random ran = new Random();
		int index = ran.nextInt(maleFnames.length);
		
		return maleFnames[index];
	}
	
	public String maleLastName() {
		
		Random ran = new Random();
		int index = ran.nextInt(maleLnames.length);
		
		return maleLnames[index];
	}
	
	public String femaleFirstName() {
		
		Random ran = new Random();
		int index = ran.nextInt(femaleFnames.length);
		
		return femaleFnames[index];
	}
	
	public String femaleLastName() {
		
		Random ran = new Random();
		int index = ran.nextInt(femaleLnames.length);
		
		return femaleLnames[index];
	}

}

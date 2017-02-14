package generate;

import model.Person;

public class Generate {
	
	private int generations;
	private Names names;
	private Person root;
	
	public Generate() {
		names = new Names();
	}
	
	public Person father(Person root, int gens) {
		if(gens == 0) {
			return null;
		}
		this.root = root;
		generations = gens;
		
		Person parent = null;
		Person[] parents = recurse(2);
		parent = new Person(root, names.maleFirstName(), names.maleLastName(), "male", parents[0], parents[1], null);
		
		return parent;
	}
	
	public Person mother(Person root, int gens) {
		if(gens == 0) {
			return null;
		}
		this.root = root;
		generations = gens;
		
		Person parent = null;
		Person[] parents = recurse(2);
		parent = new Person(root, names.femaleFirstName(), names.femaleLastName(), "female", parents[0], parents[1], null);
		
		return parent;
	}
	
	private Person[] recurse(int current) {
		
		if(current >= generations) {
			return new Person[2];
		}
		
		Person[] thisGen = new Person[2];
		Person[] parents;
		parents = recurse(current + 1);
		thisGen[0] = new Person(root, names.maleFirstName(), names.maleLastName(), "male", parents[0], parents[1], null);
		parents = recurse(current + 1);
		thisGen[1] = new Person(root, names.femaleFirstName(), names.femaleLastName(), "female", parents[0], parents[1], null);
		
		thisGen[0].setSpouse(thisGen[1]);
		thisGen[1].setSpouse(thisGen[0]);
		
		return thisGen;
	}
	
}

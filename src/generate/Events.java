package generate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.google.gson.Gson;

import model.Location;
import model.Locations;

public class Events {
	
	private Locations locations;
	
	public Locations importJson() {
		Gson gson = new Gson();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("src/generate/woodfiel@schizo.cs.byu.edu"));
			locations = gson.fromJson(reader, Locations.class);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return locations;
	}
	
	public Location getRandomLocation() {
		Random ran = new Random();
		int index = ran.nextInt(locations.size());
		return locations.get(index);
	}
	
	public static void main(String[] args) {
		Events e = new Events();
		e.importJson();
//		System.out.println("locations: " + e.locations);
	}

}

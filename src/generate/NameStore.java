package generate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import com.google.gson.Gson;

import model.Names;

public class NameStore {
	
	private Names surnames;
	private Names maleNames;
	private Names femaleNames;
	
	public void importJson() {
		Gson gson = new Gson();
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("http_root/familymapserver/data/json/snames.json"));
			surnames = gson.fromJson(reader, Names.class);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader = new BufferedReader(new FileReader("http_root/familymapserver/data/json/mnames.json"));
			maleNames = gson.fromJson(reader, Names.class);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			reader = new BufferedReader(new FileReader("http_root/familymapserver/data/json/fnames.json"));
			femaleNames = gson.fromJson(reader, Names.class);
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	public Names getSurnames() {
		return surnames;
	}

	public Names getMaleNames() {
		return maleNames;
	}

	public Names getFemaleNames() {
		return femaleNames;
	}
	
	public String getRandomSurname() {
		Random ran = new Random();
		int index = ran.nextInt(surnames.size());
		return maleNames.data[index];
	}
	
	public String getRandomMalename() {
		Random ran = new Random();
		int index = ran.nextInt(maleNames.size());
		return maleNames.data[index];
	}
	
	public String getRandomFemalename() {
		Random ran = new Random();
		int index = ran.nextInt(femaleNames.size());
		return maleNames.data[index];
	}

	public static void main(String[] args) {
		NameStore n = new NameStore();
		n.importJson();
		System.out.println("surames: " + n.surnames);
		System.out.println("femaleNames: " + n.femaleNames);
		System.out.println("maleNames: " + n.maleNames);
	}

}

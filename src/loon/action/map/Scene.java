package loon.action.map;

import java.util.ArrayList;


public class Scene {
	
	private String name;

	private ArrayList<Item> items = new ArrayList<Item>();

	private ArrayList<Character> characters = new ArrayList<Character>();

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addItem(Item item) {
		this.items.add(item);
	}

	public Item getItem(int index) {
		return this.items.get(index);
	}

	public Item getItem(String name) {
		int index = findItem(name);
		if (index == -1) {
			return null;
		}
		return getItem(index);
	}

	public int findItem(String name) {
		for (int i = 0; i < this.items.size(); i++) {
			if (getItem(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	public Item removeItem(int index) {
		return this.items.remove(index);
	}

	public int countItems() {
		return this.items.size();
	}

	public void addCharacter(Character character) {
		this.characters.add(character);
	}

	public Character getCharacter(int index) {
		return this.characters.get(index);
	}

	public Character getCharacter(String name) {
		int index = findCharacter(name);
		if (index == -1) {
			return null;
		}
		return getCharacter(index);
	}

	public int findCharacter(String name) {
		for (int i = 0; i < this.characters.size(); i++) {
			if (getCharacter(i).getName().equalsIgnoreCase(name)) {
				return i;
			}
		}
		return -1;
	}

	public Character removeCharacter(int index) {
		return this.characters.remove(index);
	}

	public int countCharacters() {
		return this.characters.size();
	}
}

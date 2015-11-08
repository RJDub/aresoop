package tiles;

import java.util.ArrayList;
import java.util.Random;

import buildings.ResourceBuilding;
import enums.Resource;
import model.*;

public class ResourceTile extends Tile {

	private Resource type;
	private int capacity;
	private ArrayList<Resource> resources;
	private ArrayList<Colonist> occupants;
	private ArrayList<ResourceBuilding> buildings;

	public ResourceTile(String path, Resource in) {
		super(path);
		resources = new ArrayList<Resource>();
		occupants = new ArrayList<Colonist>();
		buildings = new ArrayList<ResourceBuilding>();
		capacity = 5;
		type = in;
		determineResources();
	}
	
	public ArrayList<Colonist> getOccupants(){
		return occupants;
	}
	
	public ArrayList<Resource> getResources(){
		return resources;
	}
	
	public ArrayList<ResourceBuilding> getBuildings(){
		return buildings;
	}
	
	private void updateCapacity(){
		capacity = 5;
		for (int i = 0; i < buildings.size(); i++){
			capacity += buildings.get(i).getCapacity();
		}
	}

	private void determineResources() {
		Random rando = new Random();
		int chance = rando.nextInt(7);
		updateCapacity();
		switch (chance) {
		case 0:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Aquarius);
			}
			break;
		case 1:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Agrarian);
			}
			break;
		case 2:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Iron);
			}
			break;
		case 3:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Nickel);
			}
			break;
		case 4:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Oxygen);
			}
			break;
		case 5:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Unobtanium);
			}
			break;
		case 6:
			for (int i = 0; i < capacity; i++){
				resources.add(Resource.Thorium);
			}
			break;
		default:
			break;
		}
	}
	
	public Resource harvest(){
		for (int i = 0; i < occupants.size(); i++){
			
		}
		return resources.remove(0);
	}
	
	public void generate(){
		if (resources.size() < capacity){
			for (int i = resources.size(); i < capacity; i++){
				resources.add(type);
			}
		}
	}
	
	//TODO: add support to upgrade a building and to add objects to a building (ie tools)
	
	public Colonist removeOccupant(Colonist person){
		for (int i = 0; i < occupants.size(); i++){
			if (occupants.get(i).getName().compareTo(person.getName()) == 0){
				return occupants.remove(i);
			}
		}
		return null;
	}

}

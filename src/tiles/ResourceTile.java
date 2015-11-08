package tiles;

import java.util.ArrayList;
import java.util.Random;

import enums.Resource;
import model.*;

public class ResourceTile extends Tile {

	private ArrayList<Resource> resources;
	private ArrayList<Colonist> occupants;
	private ArrayList<Building> buildings;

	public ResourceTile(String path) {
		super(path);
		resources = new ArrayList<Resource>();
		occupants = new ArrayList<Colonist>();
		buildings = new ArrayList<Building>();
		determineResources();
	}
	
	public ArrayList<Colonist> getOccupants(){
		return occupants;
	}
	
	public ArrayList<Resource> getResources(){
		return resources;
	}
	
	public ArrayList<Building> getBuildings(){
		return buildings;
	}

	private void determineResources() {
		Random rando = new Random();
		int chance = rando.nextInt(7);
		switch (chance) {
		case 0:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Aquarius);
			}
			break;
		case 1:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Agrarian);
			}
			break;
		case 2:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Iron);
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Nickel);
			}
			break;
		case 4:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Oxygen);
			}
			break;
		case 5:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Unobtanium);
			}
			break;
		case 6:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Thorium);
			}
			break;
		default:
			break;
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

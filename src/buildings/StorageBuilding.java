package buildings;

import java.util.ArrayList;

import enums.Resource;
import model.Building;

public class StorageBuilding extends Building {

	private Resource type;
	private int capacity;
	private ArrayList<Resource> resources;
	
	public StorageBuilding(Resource input, int cap) {
		type = input;
		resources = new ArrayList<Resource>();
		capacity = cap;
	}
	
	public Resource getType(){
		return type;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public ArrayList<Resource> getAllResources(){
		return resources;
	}
	
	public void depositResource(){
		resources.add(type);
	}
	
	public void useResource(){
		resources.remove(0);
	}
	
}

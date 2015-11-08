package buildings;

import java.awt.Point;
import java.util.ArrayList;

import enums.Resource;
import model.Building;
import tiles.*;

public class StorageBuilding extends Building {

	private Resource type;
	private int capacity;
	private ArrayList<Resource> resources;
	public StorageBuilding(Point create, Resource input) {
		super(create);
		// TODO Auto-generated constructor stub
		type = input;
		resources = new ArrayList<Resource>();
		capacity = 10;
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

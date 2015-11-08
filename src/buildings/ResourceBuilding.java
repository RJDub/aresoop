package buildings;

import java.awt.Point;
import java.util.ArrayList;

import model.Building;
import enums.*;

public class ResourceBuilding extends Building{
	
	private Resource type;
	private int capacity;
	private ArrayList<Resource> resources;

	public ResourceBuilding(Point create, Resource input) {
		super(create);
		type = input;
		capacity = 20;
		resources = null;
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
	
	public Resource harvest(){
		return resources.remove(0);
	}
	
	public void generate(){
		
	}

}

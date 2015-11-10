package buildings;

import java.awt.Point;

import model.*;
import enums.*;

public class ResourceBuilding extends Building{
	
	private Resource type;
	private int capacity;
	private int level;

	public ResourceBuilding(Resource input, int cap) {
		type = input;
		capacity = cap;
		level = 1;
	}
	
	public Resource getType(){
		return type;
	}
	
	public int getCapacity(){
		return capacity;
	}
	
	public int getLevel(){
		return level;
	}
	
	

}

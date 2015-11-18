package buildings;

import java.awt.Point;

import model.*;
import enums.*;

public class ResourceBuilding extends Building{
	
	public ResourceBuilding(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	private Resource type;
	private int capacity;
	private int level;

	
//		type = input;
//		capacity = cap;
//		level = 1;
//	}
//	
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

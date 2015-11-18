package colonists;
import java.util.ArrayList;

import enums.*;

import model.Colonist;

public class Farmer extends Colonist{
	
public Farmer(String input, int xIn, int yIn) {
		super(input, xIn, yIn);
		// TODO Auto-generated constructor stub
	}

//	private int maxCapacity;
//	private ArrayList<Resource> resources;
//
//	public Farmer(String input) {
//		super(input);
//		
//		maxCapacity = 5;
//		
//		resources = null;
//	}
//	
//	public void addResource(Resource input) {
//		resources.add(input);
//	}
	
	public ArrayList<Resource> getResource() {
		return resources;
	}
	
	public int getmaxCapacity() {
		return maxCapacity;
	}

}
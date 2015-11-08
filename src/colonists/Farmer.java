package colonists;
import java.util.ArrayList;

import tiles.*;

import model.Colonist;

public class Farmer extends Colonist{
	
	private int maxCapacity;
	private ArrayList<Resource> resources;

	public Farmer(String input) {
		super(input);
		
		maxCapacity = 5;
		
		resources = null;
	}
	
	public void addResource(Resource input) {
		resources.add(input);
	}
	
	public ArrayList<Resource> getResource() {
		return resources;
	}
	
	public int getmaxCapacity() {
		return maxCapacity;
	}

}

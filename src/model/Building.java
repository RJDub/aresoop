package model;

import java.awt.Point;
import java.util.ArrayList;

import colonists.*;

public abstract class Building {
	private Point location;
	private ArrayList<Colonist> occupants;
	
	public Building(Point create){
		location = create;
		occupants = new ArrayList<Colonist>();
	}
	
	public int getOccupantCount(){
		return occupants.size();
	}
	
	public Colonist getOccupantAtIndex(int index){
		return occupants.get(index);
	}
	
	public Point getLocation(){
		return location;
	}
	
	public void enterBuilding(Colonist input){
		occupants.add(input);
	}
}

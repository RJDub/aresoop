package model;

import enums.BuildingType;

public abstract class Building {

	public int thirstBonus;
	public int fatigueBonus;
	public int hungerBonus;
	public int xLoc, yLoc;
	public BuildingType buildingType;
	public Building(int x, int y){
		xLoc = x;
		yLoc = y;
	}
	
	
}

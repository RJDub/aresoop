package model;

import enums.BuildingType;

public abstract class Building {

	private int thirstBonus;
	private int fatigueBonus;
	private int hungerBonus;
	private int r,c;
	private BuildingType type;
	public Building(int row, int column){
		r = row;
		c = column;
	}
	
	public BuildingType getType(){
		return type;
	}
	
	public int getR(){
		return r;
	}
	
	public int getC(){
		return c;
	}
	
	public void setType(BuildingType in){
		type = in;
	}
}

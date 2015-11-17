package model;

public abstract class Building {

	public int thirstBonus;
	public int fatigueBonus;
	public int hungerBonus;
	public int xLoc, yLoc;
	public Building(int x, int y){
		xLoc = x;
		yLoc = y;
	}
	
	
}

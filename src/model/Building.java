package model;

import java.io.Serializable;

import enums.BuildingType;

public abstract class Building implements Serializable {

	private int thirstBonus;
	private int fatigueBonus;
	private int hungerBonus;
	private int r,c;
	private BuildingType type;
	public Building(int row, int column){
		r = row;
		c = column;
	}
	
	public void setRow(int row){
		r = row;
	}
	
	public void setCol(int col){
		c = col;
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
	public int getHungerBonus(){
		return hungerBonus;
	}
	
	public int getThirstBonus(){
		return thirstBonus;
	}
	
	public int getFatigueBonus(){
		return fatigueBonus;
	}
	
	public void setHungerBonus(int b){
		hungerBonus = b;
	}

	public void setFatigueBonus(int i) {
		fatigueBonus = i;
		
	}

	public void setThirstBonus(int i) {
		thirstBonus = i;
		
	}
	
	@Override
	public String toString(){
		return type.toString()+ " Row: "+this.getR()+ " Col: "+this.getC(); 
	}
}

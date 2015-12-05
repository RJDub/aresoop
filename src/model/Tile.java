
package model;

import java.io.Serializable;

import enums.*;

public class Tile implements Serializable{
	
	private TileType type;
	private int c;
	private int r;
	private int resource_amt;
	
	public Tile(TileType type, int row, int column){
		this.type = type;
		r = row;
		c = column;
	}
	
	public TileType getType(){
		return type;
	}
	
	public int getC(){
		return c;
	}
	
	public int getR(){
		return r;
	}
	
	public void setType(TileType in){
		type = in;
	}
	
	@Override 
	public String toString(){
		return this.type.toString();
	}
	
	public void setResourceAmt(int amt){
		resource_amt = amt;
	}
	
	public int getResourceAmt(){
		return resource_amt;
	}
	
	public boolean withdrawResourceAmt(int amt){
		if (resource_amt >= amt){
			resource_amt-=amt;
			return true;
		} else {
			return false;
		}
	}
}

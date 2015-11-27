
package model;

import java.io.Serializable;

import enums.*;

public class Tile implements Serializable{
	
	private TileType type;
	private int c;
	private int r;
	
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
}

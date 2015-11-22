
package model;

import enums.*;

public class Tile {
	
	private TileType type;
	private int x;
	private int y;
	
	public Tile(TileType type, int xIn, int yIn){
		this.type = type;
		x = xIn;
		y = yIn;
	}
	
	public TileType getType(){
		return type;
	}
	
	public int getX(){
		return x;
	}
	
	public int getY(){
		return y;
	}
}

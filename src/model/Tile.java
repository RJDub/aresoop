
package model;

import enums.*;

public class Tile {
	
	private TileType type;
	
	public Tile(TileType type){
		this.type = type;
	}
	
	public TileType getType(){
		return type;
	}
	
}

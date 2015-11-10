package tiles;

import java.awt.Point;
import java.util.ArrayList;
import colonists.*;
import enums.*;
import model.Colonist;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	public Terrain terrainType; 
	private Point location;

	public Tile(){
		
	}
	
	public Tile(Point create, String path){
		location = create;
		
	}
	
	
}

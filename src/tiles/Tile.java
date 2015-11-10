package tiles;

import java.awt.Point;
import java.util.ArrayList;
import colonists.*;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	public Terrain terrainType; 
	private Point location;

	public Tile(Point create){
		location = create;
	}
	
	public Tile(Point create, String path){
		location = create;
		
	}
	
	
}

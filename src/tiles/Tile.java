package tiles;

import java.awt.Point;
import java.util.ArrayList;
import colonists.*;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	private Point location;
	
	public Tile(Point create){
		location = create;
	}
	
	public Point getLocation(){
		return location;
	}
}

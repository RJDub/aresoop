package tiles;

import java.awt.Point;
import java.util.ArrayList;
import colonists.*;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	private Point location;
	private String imagePath;
	public Tile(Point create){
		location = create;
	}
	
	public Tile(Point create, String path){
		location = create;
		imagePath = path;
	}
	
	public Point getLocation(){
		return location;
	}
	
	public String getImagePath(){
		return imagePath;
	}


}

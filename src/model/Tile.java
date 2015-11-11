package model;

import java.awt.Point;
import java.util.ArrayList;
import colonists.*;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	
	private String imagePath;
	public Tile(String path){
		
		imagePath = path;
	}
	
	public ArrayList<Colonist> getOccupants(){
		return occupants;
	}
	
	public String getImagePath(){
		return imagePath;
	}


}

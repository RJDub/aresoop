package tiles;



import java.util.ArrayList;
import enums.*;
import model.*;


public class GroundTile extends Tile {
	

public GroundTile(TileType type) {
		super(type);
		// TODO Auto-generated constructor stub
	}
private ArrayList<Colonist> occupants;
private ArrayList<Building> buildings;	
//	public GroundTile(){
//		occupants = new ArrayList<Colonist>();
//		buildings = new ArrayList<Building>();
//
//	}
//
//	public ArrayList<Colonist> getOccupants(){
//		return occupants;
//	}
//	
//	public ArrayList<Building> getBuildings(){
//		return buildings;
//	}
//	
//	public Colonist removeOccupant(Colonist person){
//		for (int i = 0; i < occupants.size(); i++){
//			if (occupants.get(i).getName().compareTo(person.getName()) == 0){
//				return occupants.remove(i);
//			}
//		}
//		return null;
//	}

}

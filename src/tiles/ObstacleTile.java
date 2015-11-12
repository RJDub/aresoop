
package tiles;

import java.util.ArrayList;

import model.Building;
import model.Tile;

public class ObstacleTile extends Tile{
	
	private ArrayList<Building> buildings;	

	public ObstacleTile() {
		buildings = new ArrayList<Building>();
	}
	
	public ArrayList<Building> getBuildings(){
		return buildings;
	}

}


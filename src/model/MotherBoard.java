package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import colonists.*;
import enums.*;
import buildings.*;

public class MotherBoard extends Observable implements Serializable{
	// private Tile[][] tiles;
	private ArrayList<Colonist> colonists;
	private Tile[][] map;
	private ArrayList<Building> buildings;
	private ArrayList<Item> items;

	public MotherBoard(ArrayList<Colonist> cols, Tile[][] tiles) {
		colonists = cols;
		map = tiles;
		buildings = new ArrayList<Building>();
		items = new ArrayList<Item>();
	}
	
	public MotherBoard(Tile[][] tiles) {
		colonists = new ArrayList<Colonist>();
		map = tiles;
		buildings = new ArrayList<Building>();
		items = new ArrayList<Item>();
	}

	public void update() {
		updateColonists();
		updateBuildings(); // TODO: finish this method.
		setChanged();
		notifyObservers(this);
	}

	public void updateColonists() {
		for (Colonist colonist : colonists) {
			if(colonist.isAlive()){
				updateNeeds(colonist);
				assignAction(colonist);
				executeAction(colonist);
			}
		}
	}
	
	public void addBuilding(Building b) {
		buildings.add(b);
		
	}
	public boolean spawnBuilding(Building b){
		if (map[b.getR()][b.getC()].getType().isBuildable()){
			addBuilding(b);
			return true;
		}else { 
			return false;
		}
	}
	
	public Tile[][] getTiles(){
		return map;
	}
	public void addItem(Item i) {
		items.add(i);
	}

	public void removeItem(Item i) {
		items.remove(i);
	}

	public void giveItem(Colonist col, Item item) {
		for (int i = 0; i < items.size(); i++) {
			if (items.get(i) == item) {
				col.addItem(item);
				items.remove(i);
			}
		}
	}

	public ArrayList<Colonist> getArrColonists() {
		return colonists;
	}

	public ArrayList<Building> getArrBuildings() {
		return buildings;
	}

	public ArrayList<Item> getArrItems() {
		return items;
	}

	public int getBoardWidth() {
		return map[0].length;
	}

	public int getBoardHeight() {
		return map.length;
	}

	public Tile getTileAtLocation(int r, int c) {
		return map[r][c];
	}

	public void setTileAtLocation(TileType t, int r, int c){
		map[r][c] = new Tile(t,r,c);
	}
	public void start() {

	}

	// this is where the building checks if a colonist is on it.
	// if there is a colonist on this building, increase the
	// appropriate need or unload all of the colonists cargo into
	// the building if it is a storage building.
	private void updateBuildings() {
		for (Building building : buildings) {
			switch(building.getType()){
			case Storage:
				for (Colonist colonist : colonists) {
					if (colonist.getR() == building.getR() && colonist.getC() == building.getC()) {
						int amount = colonist.withdrawResources();
						Task task = colonist.getTask();
						((StorageBuilding) building).depositResource(amount, task);
					}
				}
				break;
			case Dormitory:
				for (Colonist colonist : colonists) {
					if (colonist.getR() == building.getR() && colonist.getC() == building.getC() && colonist.getAction()==Action.FindSleep) {
						colonist.incrementFatigueLevel(((Dormitory)building).getFatigueBonus()); 
					}
				}
				break;
			case Mess:
				for (Colonist colonist : colonists) {
					switch (colonist.getAction()){
					case FindFood:
						if (colonist.getR() == building.getR() && colonist.getC() == building.getC())
							colonist.incrementHungerLevel(((Mess)building).getHungerBonus()); 
						break;
					case FindWater:
						if (colonist.getR() == building.getR() && colonist.getC() == building.getC())
							colonist.incrementThirstLevel(((Mess)building).getThirstBonus()); 
						break;
					default:
						break;
					}
				}		
			default:
			
			}
		}

	}

	private void updateNeeds(Colonist col) {
		col.update(map[col.getR()][col.getC()].getType());
		// if (map[col.getX()][col.getY()].getType() == TileType.Ice){
		// col.incrementHungerLevel(-1);
		// } else {
		// col.incrementThirstLevel(-1);
		// col.incrementHungerLevel(-1);
		// }
	}

	public void assignAction(Colonist col) {
		if (col.areColonistsNeedsMet()) {
			switch (col.getTask()) {
			case MiningIce:
				col.setAction(makeDecisionOnMining(col, TileType.Ice));
				break;
			case MiningIronOre:
				col.setAction(makeDecisionOnMining(col, TileType.IronOre));
				break;
			case MiningUnobtanium:
				col.setAction(makeDecisionOnMining(col, TileType.Unobtainium));
			case MiningMossyRock:
				col.setAction(makeDecisionOnMining(col, TileType.MossyRock));
			default:
				col.setAction(Action.None);
				break;
			}

		}

		else { // colonists needs are NOT met...
			// decide which need should be fulfilled by colonist, and set action accordingly.
			col.setNeedsAction();
		}
			
	}

	private Action makeDecisionOnMining(Colonist col, TileType resource) {
		if (!col.hasCapacityToMineResources()) {
//			if (col.getPath() == null) constructResourcePath(col, resource);
//			if at building
			if(col.isAtStorageBuilding(buildings)){
			//if(col.isAtBuilding(buildings)){
				return Action.UnloadCargo;
			}
			else
				return Action.Move_To_Storage;			
		} else {
			if (getTileAtLocation(col.getR(), col.getC()).getType() == resource){
				return Action.Mine;
				
			} else {
				if (resource == TileType.Ice)
					return Action.Move_To_Ice;
				else if(resource == TileType.IronOre)
					return Action.Move_To_Iron;
				else if(resource == TileType.Unobtainium)
					return Action.Move_To_Unobtainium;
				else
					return Action.Move_To_MossyRock;
			}
		}
//		}
//			
//			if (col.getAction() == Action.Mine && !col.hasCapacityToMineResources()) {
//			constructBuildingPath(col, BuildingType.Storage);
//			return Action.Move_To_Storage;
//		} else if (col.getPath() == null && !col.hasCapacityToMineResources()) {
//			return Action.UnloadCargo;
//		} else {
//			return Action.Mine;
//		}
		// if (!col.hasCapacityToMineResources()) {
		// if (col.getPath()==null)
		// constructBuildingPath(col, BuildingType.Storage);
		// return Action.UnloadCargo;
		// }
		// if (map[col.getR()][col.getC()].getType() == resource) {
		// collectResource(col, resource);
		// return Action.Mine;
		// }else if (){
		//
		// }else {
		// if (col.getPath() == null) {
		// if (!col.hasCapacityToMineResources()){
		// constructBuildingPath(col, BuildingType.Storage);
		// } else {
		// constructResourcePath(col, resource);
		// }
		// }
		// return Action.Move;
		// }
	}

	public void assignTask(Colonist col, Task t) {
		col.setTask(t);
	}

	public void executeAction(Colonist col) {
		switch (col.getAction()) {
		case Mine:
//			System.out.println("Colonist " + col.getName() + " is mining.");
			//if (col.getPath() == null) constructResourcePath(col, TileType.IronOre );
			col.execute();
			break;
		case Move_To_Ice:
			// move(col);
			if (col.getPath() == null) constructResourcePath(col, TileType.Ice);
			moveColonist(col);
//			System.out.println("Colonist " + col.getName() + " is moving to ice");
			break;
		case Move_To_Iron:
			if (col.getPath() == null) constructResourcePath(col, TileType.IronOre);
			moveColonist(col);
//			System.out.println("Colonist " + col.getName() + " is moving to ironore");
			break;
		case Move_To_Storage:
			if (col.getPath() == null) constructBuildingPath(col, BuildingType.Storage);
			moveColonist(col);
//			System.out.println("Colonist " + col.getName() + " is moving to storage");
			break;
		case UnloadCargo:
			// this is handled by the building.
//			System.out.println("Colonist " + col.getName() + " needs to unload cargo");
			// depreciated moveTowardsBuilding(col, BuildingType.Storage);
			break;
		case None:
//			System.out.println("Colonist " + col.getName() + " is ACTION_NONE.");
			break;
		case FindFood:
			if (col.getPath() == null) constructBuildingPath(col, BuildingType.Mess);
			moveColonist(col);
			break;
		case FindWater:
			if (col.getPath() == null) constructBuildingPath(col, BuildingType.Mess);
			moveColonist(col);
			break;
		case FindSleep:
			if (col.getPath() == null) constructBuildingPath(col, BuildingType.Dormitory);
			moveColonist(col);
			break;
		default:
			System.out.println("Colonist " + col.getName() + " has an unhandled action!ERROR!");
			break;
		}
	}

	private void constructBuildingPath(Colonist col, BuildingType build) {
		col.setPath(Map.findPathToBuilding(col.getR(), col.getC(), build, buildings, map));
	}

	private void constructResourcePath(Colonist col, TileType res) {
		col.setPath(Map.findPathToTileType(col.getR(), col.getC(), res, map));
	}

	private void moveColonist(Colonist col) {
		int[] locs = col.updatePath();
		col.setR(locs[0]);
		col.setC(locs[1]);
	}

	public void addColonist(Colonist c) {
		colonists.add(c);
	}

	// private void moveTowardsBuilding(Colonist col, BuildingType bt) {
	// // This is going away because a colonist just MOVES.
	// // TODO: add code that moves this colonist towards a building (to drop
	// // off storage or to
	// // fulfill need.
	//
	// // find nearest storage
	// // weak pathfinding code:
	// // this just finds the first building of bt x,y.
	// for (Building b : buildings) {
	// if (b.getType() == bt) {
	// // TODO: Paul implement a pathfinder for buildings here
	// }
	// }
	//
	// }

	private void collectResource(Colonist col, TileType res) {
		// TODO: we need to add code to get a colonist to extract resources
	}

	public void printModel() {
		for (Colonist col : colonists) {
			System.out.println(col.toString());
		}
	}

	public void move(Colonist col, int foundX, int foundY) {

	}
	
	public int getIronTotal(){
		int total = 0;
		for (Building b: buildings){
			if (b.getType()==BuildingType.Storage){
				total+= ((StorageBuilding) b).getIronOreAmount();
			}
		}
		return total;
	}
	
	public boolean withdrawIronTotal(int amt){
		if (amt <= getIronTotal()){
			for (Building b: buildings){
				if (b.getType()==BuildingType.Storage){
					if (amt <= ((StorageBuilding) b).getIronOreAmount()){
						((StorageBuilding) b).withdrawIronOre(amt);
						amt = 0;
					} else {
						int temp_amount = ((StorageBuilding) b).getIronOreAmount();
						((StorageBuilding) b).withdrawIronOre(temp_amount);
						amt -= temp_amount;
					}
				}
			}
			return true;
		}
		return false;
	}
	

}
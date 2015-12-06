package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observable;

import Helpers.ColonistHelper;
import Helpers.ResourceAmountHelper;
import Helpers.TileHandler;
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
		ColonistHelper.cleanupColonists(colonists);
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
						if (colonist.getR() == building.getR() && colonist.getC() == building.getC()){
							//check if enough food is in storage
							int available_food = ResourceAmountHelper.getStorageAmountFromTileType(TileType.MossyRock, this);
							int col_max_food_consumption = colonist.getMaxFoodConsumption();
							int hungerBonus = building.getHungerBonus();
							int max_food_colonist_can_eat = col_max_food_consumption/hungerBonus;
							if (available_food >= max_food_colonist_can_eat){
								colonist.incrementHungerLevel(max_food_colonist_can_eat*hungerBonus); 
								ResourceAmountHelper.withdrawAmountUsingTileType(TileType.MossyRock, this, max_food_colonist_can_eat);
								//eat max food
							} else{
								colonist.incrementHungerLevel(available_food*hungerBonus);
								ResourceAmountHelper.withdrawAmountUsingTileType(TileType.MossyRock, this, available_food);
							}	
						}
						break;
					case FindWater:
						//check if enough water is in storage
						int available_water = ResourceAmountHelper.getStorageAmountFromTileType(TileType.Ice, this);
						int col_max_water_consumption = colonist.getMaxWaterConsumption();
						int thirstBonus = building.getThirstBonus();
						int max_water_colonist_can_consume = col_max_water_consumption/thirstBonus;
						if (available_water >= max_water_colonist_can_consume){
							colonist.incrementThirstLevel(max_water_colonist_can_consume*thirstBonus); 
							ResourceAmountHelper.withdrawAmountUsingTileType(TileType.Ice, this, max_water_colonist_can_consume);
							//eat max food
						} else{
							colonist.incrementHungerLevel(available_water*thirstBonus);
							ResourceAmountHelper.withdrawAmountUsingTileType(TileType.Ice, this, available_water);
						}	
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
			case MiningUnobtainium:
				col.setAction(makeDecisionOnMining(col, TileType.Unobtainium));
				break;
			case MiningMossyRock:
				col.setAction(makeDecisionOnMining(col, TileType.MossyRock));
				break;
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
	}

	public void assignTask(Colonist col, Task t) {
		col.setTask(t);
	}

	public void executeAction(Colonist col) {
		switch (col.getAction()) {
		case Mine:
//			System.out.println("Colonist " + col.getName() + " is mining.");
			//if (col.getPath() == null) constructResourcePath(col, TileType.IronOre );
			//col.execute();
			int amt_to_mine = col.getMiningAmount();
			if(TileHandler.mineTile(map[col.getR()][col.getC()], amt_to_mine))
				col.mine(amt_to_mine);
			
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
		case Move_To_Unobtainium:
			if (col.getPath() == null) constructResourcePath(col, TileType.Unobtainium);
			moveColonist(col);
			break;
		case Move_To_MossyRock:
			if (col.getPath() == null) constructResourcePath(col, TileType.MossyRock);
			moveColonist(col);
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
	
	public int getUnobtaniumTotal(){
		int total = 0;
		for (Building b: buildings){
			if (b.getType()==BuildingType.Storage){
				total+= ((StorageBuilding) b).getUnobtainiumAmount();
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
	
	public boolean withdrawUnobtaniumTotal(int amt){
		if (amt <= getUnobtaniumTotal()){
			for (Building b: buildings){
				if (b.getType()==BuildingType.Storage){
					if (amt <= ((StorageBuilding) b).getUnobtainiumAmount()){
						((StorageBuilding) b).withdrawUnobtainium(amt);
						amt = 0;
					} else {
						int temp_amount = ((StorageBuilding) b).getUnobtainiumAmount();
						((StorageBuilding) b).withdrawUnobtainium(temp_amount);
						amt -= temp_amount;
					}
				}
			}
			return true;
		}
		return false;
	}
	

}
package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import tiles.*;
import colonists.*;
import enums.*;
import buildings.*;

public class MotherBoard extends Observable {
	// private Tile[][] tiles;
	private ArrayList<Colonist> colonists;
	private Tile[][] map;
	private ArrayList<Building> buildings;
	private ArrayList<Item> items;

	public MotherBoard(ArrayList<Colonist> cols, Tile[][] tiles) {
		// tiles = new Tile[10][10];
		colonists = cols;
		map = tiles;
		buildings = new ArrayList<Building>();
		items = new ArrayList<Item>();
	}

	public void addBuilding(Building b) {
		buildings.add(b);
	}
	
	public void addItem(Item i) {
		items.add(i);
	}
	
	public void removeItem(Item i) {
		items.remove(i);
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

	public void start() {

	}

	public void update() {
		updateColonists();
		updateBuildings(); // TODO: finish this method.
		setChanged();
		notifyObservers(this);
	}

	public void updateColonists() {
		for (Colonist colonist : colonists) {
			updateNeeds(colonist);
			assignAction(colonist);
			executeAction(colonist);
		}
	}

	// this is where the building checks if a colonist is on it.
	// if there is a colonist on this building, increase the
	// appropriate need or unload all of the colonists cargo into
	// the building if it is a storage building.
	private void updateBuildings() {
		for (Building building : buildings) {
			if (building.getType() == BuildingType.Storage) {
				for (Colonist colonist : colonists) {
					if (colonist.getR() == building.getR() && colonist.getC() == building.getC()) {
						int amount = colonist.withdrawResources();
						Task task = colonist.getTask();
						((StorageBuilding) building).depositResource(amount, task);
					}
				}
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
			default:
				col.setAction(Action.None);
				break;
			}

		}

		else
			col.setAction(Action.None);
	}

	private Action makeDecisionOnMining(Colonist col, TileType resource) {
		if (!col.hasCapacityToMineResources()) {
			return Action.UnloadCargo;
		}
		if (map[col.getR()][col.getC()].getType() == resource) {
			collectResource(col, resource);
			return Action.Mine;
		} else {
			if (col.getPath() == null) {
				constructResourcePath(col, resource);
			} else {
				moveColonist(col);
			}
			return Action.Move;
		}
	}

	public void assignTask(Colonist col, Task t) {
		col.setTask(t);
	}

	public void executeAction(Colonist col) {
		// TODO: effectively null, doesnt produce any output other than sysout
		switch (col.getAction()) {
		case Mine:
			System.out.println("Colonist " + col.getName() + " is mining.");
			col.execute();
			break;
		case Move:
			// move(col);
			System.out.println("Colonist " + col.getName() + " is moving.");
			break;
		case UnloadCargo:
			System.out.println("Colonist " + col.getName() + " needs to unload cargo");
			moveTowardsBuilding(col, BuildingType.Storage);
			break;
		default:
			System.out.println("Colonist " + col.getName() + " is ACTION_NONE.");
			break;
		}
	}

	private void constructBuildingPath(Colonist col, BuildingType build) {
		ArrayList<Tile> path = new ArrayList<Tile>();
		for (Building building : buildings) {
			if (building.getType() == build) {

			}
		}
		col.setPath(path);
	}

	private void constructResourcePath(Colonist col, TileType res) {
		ArrayList<Tile> path = new ArrayList<Tile>();
		for (int r = 0; r < map.length; r++) {
			for (int c = 0; c < map[0].length; c++) {
				if (map[r][c].getType() == res) {
					path = constructPath(col, r, c, path);
				}
			}
		}
		col.setPath(path);
	}

	private ArrayList<Tile> constructPath(Colonist col, int r, int c, ArrayList<Tile> inital) {
		ArrayList<Tile> path = inital;
		ArrayList<Tile> tempP = new ArrayList<Tile>();
		int colC = col.getC();
		int colR = col.getR();
		tempP.add(map[colR][colC]);
		while (true) {
			if (colC > c && colR > r) {
				if (map[colR][colC - 1].getType().getWeight() < map[colR][colC - 1].getType().getWeight()) {
					colC--;
				} else {
					colR--;
				}
			} else if (colC < c && colR < r) {
				if (map[colR][colC + 1].getType().getWeight() < map[colR][colC + 1].getType().getWeight()) {
					colC++;
				} else {
					colR++;
				}
			} else if (colC > c && colR < r) {
				if (map[colR][colC - 1].getType().getWeight() < map[colR][colC + 1].getType().getWeight()) {
					colC--;
				} else {
					colR++;
				}
			} else if (colC < c && colR > r) {
				if (map[colR][colC + 1].getType().getWeight() < map[colR][colC - 1].getType().getWeight()) {
					colC++;
				} else {
					colR--;
				}
			} else if (colC > c) {
				colC--;
			} else if (colC < c) {
				colC++;
			} else if (colR > r) {
				colR--;
			} else if (colR < r) {
				colR++;
			} else {
				break;
			}
			tempP.add(map[colR][colC]);
		}
		if (tempP.size() < path.size() || path.size() == 0) {
			for (int i = 0; i < path.size(); i++) {
				path.remove(i);
			}
			for (int i = 0; i < tempP.size(); i++) {
				path.add(tempP.get(i));
			}
		}
		return path;
	}

	private void moveColonist(Colonist col) {
		Tile newL = col.updatePath();
		col.setR(newL.getR());
		col.setC(newL.getC());
	}

	public void addColonist(Colonist c) {
		colonists.add(c);
	}

	private void moveTowardsBuilding(Colonist col, BuildingType bt) {
		// TODO: add code that moves this colonist towards a building (to drop
		// off storage or to
		// fulfill need.

		// find nearest storage
		// weak pathfinding code:
		// this just finds the first building of bt x,y.
		for (Building b : buildings) {
			if (b.getType() == bt) {
				// TODO: Paul implement a pathfinder for buildings here
			}
		}

	}

	private void collectResource(Colonist col, TileType res) {
		// TODO: we need to add code to get a colonist to extract resources
	}

	public void printModel() {
		for (Colonist col : colonists) {
			System.out.println(col.toString());
		}
	}

	// This is a very important method that Paul is working on.
	//
	// this method receives a colonist (which has their own x and y)
	// and a destination x and destination y. This code will find
	// the best path for the colonist to take towards this destination
	// and the actually move the colonist towards that location.
	public void move(Colonist col, int foundX, int foundY) {

	}

	// private void setupBoard() {
	// // random random.nextint()
	// Random random = new Random();
	//
	// //assign tiles to each part of the array
	// for (int i = 0; i < 10; i++){
	// for(int j = 0; j<10; j++){
	// int rand = random.nextInt(3);
	// switch(rand){
	// case 0:
	// tiles[i][j] = new GroundTile();
	// break;
	//
	// case 1:
	// tiles[i][j] = new ObstacleTile();
	// break;
	//
	// case 2:
	// int chance = random.nextInt(7);
	// switch (chance) {
	// case 0:
	// tiles[i][j] = new ResourceTile(Resource.Aquarius);
	// break;
	// case 1:
	// tiles[i][j] = new ResourceTile(Resource.Agrarian);
	// break;
	// case 2:
	// tiles[i][j] = new ResourceTile(Resource.Iron);
	// break;
	// case 3:
	// tiles[i][j] = new ResourceTile(Resource.Nickel);
	// break;
	// case 4:
	// tiles[i][j] = new ResourceTile(Resource.Oxygen);
	// break;
	// case 5:
	// tiles[i][j] = new ResourceTile(Resource.Unobtanium);
	// break;
	// case 6:
	// tiles[i][j] = new ResourceTile(Resource.Thorium);
	// break;
	// default:
	// break;
	// }
	//
	// break;
	// }
	//
	// }
	// }
	// // resource, ground, obstacle
	// }
	//
	// public void update(){
	// setChanged();
	// notifyObservers(this);
	// updateResources();
	// updateColonists();
	// updateBoardGame();
	// }
	//
	// public void updateResources(){
	//
	// }
	//
	// public void updateColonists(){
	//
	// }

	//
	// public void updateBoardGame(){
	//
	// }

}
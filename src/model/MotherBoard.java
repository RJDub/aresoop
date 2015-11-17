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


	public MotherBoard(ArrayList<Colonist> cols, Tile[][] tiles) {
		// tiles = new Tile[10][10];
		colonists = cols;
		map = tiles;
		buildings = new ArrayList<Building>();
	}

	public void addBuidling(Building b){
		buildings.add(b);
	}
	public ArrayList<Colonist> getArrColonists() {
		return colonists;
	}
	
	public int getBoardWidth(){
		return map[0].length;
	}
	
	public int getBoardHeight(){
		return map.length;
	}
	
	public Tile getTileAtLocation(int x, int y){
		return map[x][y];
	}

	public void start() {

	}

	public void update() {
		for (Colonist colonist : colonists) {
			updateNeeds(colonist);
			assignAction(colonist);
			executeAction(colonist);
		}
		
		updateBuildings();  //TODO: finish this method.
		setChanged();
		notifyObservers(this);
	}
	
	// this is where the building checks if a colonist is on it.
	// if there is a colonist on this building, increase the 
	// appropriate need.
	private void updateBuildings() {
		
		
	}

	private void updateNeeds(Colonist col){
		
		if (map[col.getX()][col.getY()].getType() == TileType.Ice){
			col.incHungerLevel(-1);
		} else {
			col.incThirstLevel(-1);
			col.incHungerLevel(-1);
		}
	}

	public void assignAction(Colonist col) {
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

	private Action makeDecisionOnMining(Colonist col, TileType resource) {
		if (map[col.getX()][col.getY()].getType() == resource) {
			collectResource(col, resource);
			return Action.Mine;
		} else {
			moveToResource(col, resource);
			return Action.Move;
		}
	}

	public void assignTask(Colonist col, Task t) {
		col.setTask(t);
	}

	public void executeAction(Colonist col) {
		//TODO: effectively null, doesnt produce any output other than sysout
		switch (col.getAction()) {
		case Mine:
			System.out.println("Colonist " + col.getName() + " is mining.");
			break;
		case Move:
			//move(col);
			System.out.println("Colonist " + col.getName() + " is moving.");
			break;
		default:
			System.out.println("Colonist " + col.getName() + " is ACTION_NONE.");
			break;
		}
	}

	public void moveToResource(Colonist col, TileType res) {
		int curr = -1;
		int foundX = -1;
		int foundY = -1;
		// this code will get replaced by the move method that Paul is writing,
		// which will contain the pathfinding code.
		
		// WEAK PATHFINDING CODE:
		for (int x = 0; x < map[0].length; x++) {
			for (int y = 0; y < map.length; y++) {
				if (map[x][y].getType() == res) {
					int xRange = Math.abs(x - col.getX());
					int yRange = Math.abs(y - col.getY());
					if (curr > (xRange + yRange) || curr == -1) {
						curr = xRange + yRange;
						foundX = x;
						foundY = y;
					}
				}
			}
		}
		// END OF WEAK PATHFINDING CODE that will get replaced by the move().
		
		// moved the code that was here into the move method; 
		move (col, foundX, foundY);
		
	}
	
	private void moveTowardsBuilding(Colonist col){
		//TODO: add code that moves this colonist towards a building (to drop off storage or to
		// fulfill need.
	}
	private void collectResource(Colonist col, TileType res){
		//TODO: we need to add code to get a colonist to extract resources
	}

	public void printModel() {
		for (Colonist col : colonists) {
			System.out.println(col.toString());
		}
	}

	// This is a very important method that Paul is working on.
	// All decisions are made prior to this method call. 
	// 
	// this method receives a colonist (which has their own x and y) 
	// and a destination x and destination y.  This code will find 
	// the best path for the colonist to take towards this destination
	// and the actually move the colonist towards that location.
	public void move(Colonist c, int x, int y){
		if (foundX > col.getX()) {
			col.setX(col.getX() + 1);
		} else if (foundX < col.getX()) {
			col.setX(col.getX() - 1);
		}

		if (foundY > col.getY()) {
			col.setY(col.getY() + 1);
		} else if (foundX < col.getY()) {
			col.setY(col.getY() - 1);
		}
		
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

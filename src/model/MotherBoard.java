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
	// private ArrayList<Building> buldings;

	public MotherBoard(ArrayList<Colonist> cols, Tile[][] tiles) {
		// tiles = new Tile[10][10];
		colonists = cols;
		map = tiles;
		// buldings = new ArrayList<>();
		// setupBoard();
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
		setChanged();
		notifyObservers(this);
	}
	
	private void updateNeeds(Colonist col){
		col.incThirstLevel(-1);
		col.incHungerLevel(-1);
		
	}

	public void assignAction(Colonist col) {
		switch (col.getTask()) {
		case Mining:
			col.setAction(makeDecisionOnMining(col));
			break;
		default:
			col.setAction(Action.None);
			break;
		}
	}

	private Action makeDecisionOnMining(Colonist col) {
		if (map[col.getX()][col.getY()].getType() == TileType.Ice) {
			return Action.Mine;
		} else {
			return Action.Move;
		}
	}

	public void assignTask(Colonist col, Task t) {
		col.setTask(t);
	}

	public void executeAction(Colonist col) {
		switch (col.getAction()) {
		case Mine:
			System.out.println("Colonist " + col.getName() + " is mining.");
			break;
		case Move:
			move(col);
			System.out.println("Colonist " + col.getName() + " is moving.");
		default:
			System.out.println("Colonist " + col.getName() + " is ACTION_NONE.");
			break;
		}
	}

	public void move(Colonist col) {
		switch (col.getTask()) {
		case Mining:
			int curr = -1;
			int foundX = -1;
			int foundY = -1;
			for (int x = 0; x < map[0].length; x++) {
				for (int y = 0; y < map.length; y++) {
					if (map[x][y].getType() == TileType.Ice) {
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
		default:
			break;
		}
	}

	public void printModel() {
		for (Colonist col : colonists) {
			System.out.println(col.toString());
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

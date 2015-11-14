package model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

import tiles.*;
import colonists.*;
import enums.*;
import buildings.*;

public class MotherBoard extends Observable {
	//private Tile[][] tiles;
	private ArrayList<Colonist> colonists;
	private Tile[][] map;
	//private ArrayList<Building> buldings;

	public MotherBoard(ArrayList<Colonist> cols, Tile[][] tiles) {
		//tiles = new Tile[10][10];
		colonists = cols;
		map = tiles;
		//buldings = new ArrayList<>();
		//setupBoard();
	}
	
	public ArrayList<Colonist> getArrColonists(){
		return colonists;
	}
	
	public void start(){
		
	}
	
	public void update(){
		for (Colonist colonist : colonists){
			assignAction(colonist);
			executeAction(colonist);
		}
	}
	
	public void assignAction(Colonist col){
		switch (col.getTask()){
		case Mining:
			if (map[col.getX()][col.getY()].getType() == TileType.Ice){
				col.setAction(Action.Mine);
			} else {
				col.setAction(Action.Move);
			}
			break;
		default:
			col.setAction(Action.None);
			break;
		}
	}
	
	public void assignTask(Colonist col, Task t){
		col.setTask(t);
	}
	
	public void executeAction(Colonist col){
		switch (col.getAction()){
		case Mine:
			System.out.println("Colonist " + col.getName() + " is mining.");
			break;
		case Move:
			System.out.println("Colonist " + col.getName() + " is moving.");
		default:
			System.out.println("Colonist " + col.getName() + " is ACTION_NONE.");
			break;	
		}
	}
	
	
	public void printModel(){
		for (Colonist col : colonists){
			System.out.println(col.toString());
		}
	}
	

	
	

//	private void setupBoard() {
//		// random random.nextint()
//		Random random = new Random();
//		
//		//assign tiles to each part of the array
//		for (int i = 0; i < 10; i++){
//			for(int j = 0; j<10; j++){
//				int rand = random.nextInt(3);
//				switch(rand){
//				case 0:
//					tiles[i][j] = new GroundTile();
//					break;
//
//				case 1:
//					tiles[i][j] = new ObstacleTile();
//					break;
//
//				case 2:
//					int chance = random.nextInt(7);
//					switch (chance) {
//					case 0:
//						tiles[i][j] = new ResourceTile(Resource.Aquarius);
//						break;
//					case 1:
//						tiles[i][j] = new ResourceTile(Resource.Agrarian);
//						break;
//					case 2:
//						tiles[i][j] = new ResourceTile(Resource.Iron);
//						break;
//					case 3:
//						tiles[i][j] = new ResourceTile(Resource.Nickel);
//						break;
//					case 4:
//						tiles[i][j] = new ResourceTile(Resource.Oxygen);
//						break;
//					case 5:
//						tiles[i][j] = new ResourceTile(Resource.Unobtanium);
//						break;
//					case 6:
//						tiles[i][j] = new ResourceTile(Resource.Thorium);
//						break;
//					default:
//						break;
//					}
//
//					break;
//				}
//
//			}
//		}
//		// resource, ground, obstacle
//	}
//	
//	public void update(){
//		setChanged();
//	    notifyObservers(this);
//		updateResources();
//		updateColonists();
//		updateBoardGame();
//	}
//	
//	public void updateResources(){
//		
//	}
//	
//	public void updateColonists(){
//		
//	}
//	
//	public void updateBoardGame(){
//		
//	}
	
}

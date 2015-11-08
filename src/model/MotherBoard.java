package model;

import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import tiles.*;
import colonists.*;
import buildings.*;


public class MotherBoard {
	Tile[][] tiles;
	ArrayList<Colonist> colonists = new ArrayList<>();
	ArrayList<Building> buldings = new ArrayList<>();
	
	public MotherBoard(){
		tiles = new Tile[10][10];
		
		setupBoard();
	}
	
	private void setupBoard(){
		//random radnom.nextint()
		Random random = new Random();
		
		//assign tiles to each part of the array
		for (int i = 0; i < 10; i++){
			for(int j = 0; j<10; j++){
				int r = random.nextInt(3);
				switch(r){
				case 0:
					tiles[i][j] = new ResourceTile(new String("./ground.png"));
				break;
				
				case 1:
					tiles[i][j] = new ResourceTile(new String("./obstacle.png"));
				break;
					
				case 2:
					tiles[i][j] = new ResourceTile(new String("./resource.png"));
				break;
				}
				
			}
		}
		//resource, ground, obstacle
	}
}

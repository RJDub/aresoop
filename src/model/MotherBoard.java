package model;

import java.util.ArrayList;
import java.util.Random;

import tiles.*;
import colonists.*;
import enums.Resource;
import buildings.*;

public class MotherBoard {
	private Tile[][] tiles;
	private ArrayList<Colonist> colonists;
	private ArrayList<Building> buldings;

	public MotherBoard() {
		tiles = new Tile[10][10];
		colonists = new ArrayList<>();
		buldings = new ArrayList<>();
		setupBoard();
	}

	private void setupBoard() {
		// random random.nextint()
		Random random = new Random();

		// assign tiles to each part of the array
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				int r = random.nextInt(3);
				switch (r) {
				case 0:
					tiles[i][j] = new GroundTile();
					break;

				case 1:
					tiles[i][j] = new ObstacleTile();
					break;

				case 2:
					int chance = random.nextInt(7);
					switch (chance) {
					case 0:
						tiles[i][j] = new ResourceTile(Resource.Aquarius);
						break;
					case 1:
						tiles[i][j] = new ResourceTile(Resource.Agrarian);
						break;
					case 2:
						tiles[i][j] = new ResourceTile(Resource.Iron);
						break;
					case 3:
						tiles[i][j] = new ResourceTile(Resource.Nickel);
						break;
					case 4:
						tiles[i][j] = new ResourceTile(Resource.Oxygen);
						break;
					case 5:
						tiles[i][j] = new ResourceTile(Resource.Unobtanium);
						break;
					case 6:
						tiles[i][j] = new ResourceTile(Resource.Thorium);
						break;
					default:
						break;
					}

					break;
				}

			}
		}
		// resource, ground, obstacle
	}
	
	public void update(){
		updateResources();
		updateColonists();
		updateBoardGame();
	}
	
	public void updateResources(){
		
	}
	
	public void updateColonists(){
		
	}
	
	public void updateBoardGame(){
		
	}
}

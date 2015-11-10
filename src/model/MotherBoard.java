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
					tiles[i][j] = new GroundTile(new String("./ground.png"));
					break;

				case 1:
					tiles[i][j] = new ObstacleTile(new String("./obstacle.png"));
					break;

				case 2:
					int chance = random.nextInt(7);
					switch (chance) {
					case 0:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Aquarius);
						break;
					case 1:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Agrarian);
						break;
					case 2:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Iron);
						break;
					case 3:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Nickel);
						break;
					case 4:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Oxygen);
						break;
					case 5:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Unobtanium);
						break;
					case 6:
						tiles[i][j] = new ResourceTile(new String("./resource.png"), Resource.Thorium);
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
}

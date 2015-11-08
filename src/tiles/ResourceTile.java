package tiles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import enums.Resource;
import model.Tile;

public class ResourceTile extends Tile {

	ArrayList<Resource> resources;

	public ResourceTile(String path) {
		super(path);
		// TODO Auto-generated constructor stub
		resources = new ArrayList<Resource>();
		determineResources();
	}

	private void determineResources() {
		Random rando = new Random();
		int chance = rando.nextInt(7);
		switch (chance) {
		case 0:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Aquarius);
			}
			break;
		case 1:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Agrarian);
			}
			break;
		case 2:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Iron);
			}
			break;
		case 3:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Nickel);
			}
			break;
		case 4:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Oxygen);
			}
			break;
		case 5:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Unobtanium);
			}
			break;
		case 6:
			for (int i = 0; i < 3; i++){
				resources.add(Resource.Thorium);
			}
			break;
		default:
			break;
		}
	}

}

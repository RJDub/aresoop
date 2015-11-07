package tiles;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class ResourceTile extends Tile {

	ArrayList<Resource> resources;

	public ResourceTile(Point create) {
		super(create);
		// TODO Auto-generated constructor stub
		resources = new ArrayList<Resource>();
		determineResources();
	}

	private void determineResources() {
		Random rando = new Random();
		Resource res = null;
		int amount = rando.nextInt(6);
		for (int i = 0; i < amount; i++) {
			int chance = rando.nextInt(7);
			switch (chance) {
			case 0:
				res = Resource.Aquarius;
				break;
			case 1:
				res = Resource.Agrarian;
				break;
			case 2:
				res = Resource.Iron;
				break;
			case 3:
				res = Resource.Nickel;
				break;
			case 4:
				res = Resource.Oxygen;
				break;
			case 5:
				res = Resource.Unobtanium;
				break;
			case 6:
				res = Resource.Thorium;
				break;
			default:
				res = null;
				break;
			}
			resources.add(res);
		}
	}

}

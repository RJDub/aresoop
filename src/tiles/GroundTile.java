package tiles;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GroundTile extends Tile {
	
	public GroundTile(Point create){
		super(create);
		super.terrainType = Terrain.Flat;
	}
}

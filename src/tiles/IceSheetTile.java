package tiles;

import java.awt.Point;

public class IceSheetTile extends Tile{

	public IceSheetTile(Point create) {
		super(create);
		super.terrainType = Terrain.IceSheet;
	}

}

package Helpers;

import enums.TileType;
import model.Tile;

public class TileHandler {

	public static boolean mineTile(Tile tile, int amt) {
		TileType type = tile.getType();
		if (tile.getResourceAmt() > 0){
			if(type == TileType.Ice || type == TileType.IronOre || type == TileType.Unobtainium || type == TileType.MossyRock ){
				tile.withdrawResourceAmt(amt);
				return true;
			}
		}
		if (tile.getResourceAmt() <= 0){
			tile.setType(TileType.Flat);
		}
		
		return false;
	}

}

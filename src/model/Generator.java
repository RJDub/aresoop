package model;

import enums.*;

public class Generator {

	
	public static Tile[][] generateMap(Tile[][] map){
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				map[x][y] = new Tile(TileType.Flat);
			}
		}
		return map;
	}
}

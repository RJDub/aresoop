package model;

import enums.*;

public class Generator {

	
	public static Tile[][] generateMap(Tile[][] map){
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				map[x][y] = new Tile(TileType.Flat);
			}
		}
		map[0][9] = new Tile(TileType.IronOre);
		map[3][5] = new Tile(TileType.Ice);
		return map;
	}
	
	public static Tile[][] generateNeedsTestMap(Tile[][] map){
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				map[x][y] = new Tile(TileType.Flat);
			}
		}
		map[0][1] = new Tile(TileType.Ice);
		return map;
	}
}

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
	
	public static Tile[][] generateMap2(){
		Tile[][] map2 = new Tile[10][10];
		for (int y = 0; y < map2[0].length; y++){
			for (int x = 0; x < map2.length; x++){
				map2[x][y] = new Tile(TileType.Flat);
			}
		}
		map2[0][9] = new Tile(TileType.IronOre);
		map2[3][5] = new Tile(TileType.Ice);
		map2[3][7] = new Tile(TileType.Volcano);
		map2[3][8] = new Tile(TileType.Volcano);
		map2[3][9] = new Tile(TileType.Volcano);
		map2[4][7] = new Tile(TileType.Crater);
		map2[4][8] = new Tile(TileType.Crater);
		map2[4][9] = new Tile(TileType.Crater);
		
		return map2;
		
		
	}
}

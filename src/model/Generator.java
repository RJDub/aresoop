package model;

import java.util.Random;

import enums.*;

public class Generator {

	
	public static Tile[][] generateMap(Tile[][] map){
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				map[y][x] = new Tile(TileType.Flat, x, y);
			}
		}
		map[0][9] = new Tile(TileType.IronOre, 9, 0);
		map[3][5] = new Tile(TileType.Ice, 3, 5);
		return map;
	}
	
	public static Tile[][] generateNeedsTestMap(Tile[][] map){
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				map[y][x] = new Tile(TileType.Flat, x, y);
			}
		}
		map[0][1] = new Tile(TileType.Ice, 1, 0);
		return map;
	}
	
	public static Tile[][] generateMap2(){
		Tile[][] map2 = new Tile[10][10];
		for (int y = 0; y < map2[0].length; y++){
			for (int x = 0; x < map2.length; x++){
				map2[y][x] = new Tile(TileType.Flat, x, y);
			}
		}
		map2[0][9] = new Tile(TileType.IronOre, 9, 0);
		map2[3][5] = new Tile(TileType.Ice);
		map2[3][7] = new Tile(TileType.Volcano);
		map2[3][8] = new Tile(TileType.Volcano);
		map2[3][9] = new Tile(TileType.Volcano);
		map2[4][7] = new Tile(TileType.Crater);
		map2[4][8] = new Tile(TileType.Crater);
		map2[4][9] = new Tile(TileType.Crater);
		
		return map2;
	}
	
	public static Tile[][] generateEasyMap(Tile[][] map){
		Random rand = new Random();
		int chance = 0;
		for (int y = 0; y < map[0].length; y++){
			for (int x = 0; x < map.length; x++){
				chance = rand.nextInt(100) + 1;
				if (chance < 56){
					map[x][y] = new Tile(TileType.Flat);
				} else if (chance < 76){
					map[x][y] = new Tile(TileType.Ice);
				} else if (chance < 86){
					map[x][y] = new Tile(TileType.IronOre);
				} else if (chance < 91){
					map[x][y] = new Tile(TileType.Crater);
				} else if (chance < 96){ 
					map[x][y] = new Tile(TileType.Mountain);
				} else {
					map[x][y] = new Tile(TileType.Volcano);
				}
			}
		}
		return map;
	}
}

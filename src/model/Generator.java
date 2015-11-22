package model;

import java.util.Random;

import enums.*;

public class Generator {

	
	public static Tile[][] generateMap(Tile[][] map){
		for (int r = 0; r < map.length; r++){
			for (int c = 0; c < map[0].length; c++){
				map[r][c] = new Tile(TileType.Flat, r, c);
			}
		}
		map[0][9] = new Tile(TileType.IronOre, 0, 9);
		map[3][5] = new Tile(TileType.Ice, 3, 5);
		return map;
	}
	
	public static Tile[][] generateNeedsTestMap(Tile[][] map){
		for (int r = 0; r < map.length; r++){
			for (int c = 0; c < map[0].length; c++){
				map[r][c] = new Tile(TileType.Flat, r, c);
			}
		}
		map[0][1] = new Tile(TileType.Ice, 1, 0);
		return map;
	}
	
	public static Tile[][] generateMap2(){
		Tile[][] map2 = new Tile[10][10];
		for (int r = 0; r < map2.length; r++){
			for (int c = 0; c < map2[0].length; c++){
				map2[r][c] = new Tile(TileType.Flat, r, c);
			}
		}
		map2[0][9] = new Tile(TileType.IronOre, 0, 9);
		map2[3][5] = new Tile(TileType.Ice, 3, 5);
		map2[3][7] = new Tile(TileType.Volcano, 3, 7);
		map2[3][8] = new Tile(TileType.Volcano, 3, 8);
		map2[3][9] = new Tile(TileType.Volcano, 3, 9);
		map2[4][7] = new Tile(TileType.Crater, 4, 7);
		map2[4][8] = new Tile(TileType.Crater, 4, 8);
		map2[4][9] = new Tile(TileType.Crater, 4, 9);
		
		return map2;
	}
	
	public static Tile[][] generateEasyMap(Tile[][] map){
		Random rand = new Random();
		int chance = 0;
		for (int r = 0; r < map.length; r++){
			for (int c = 0; c < map[0].length; c++){
				chance = rand.nextInt(100) + 1;
				if (chance < 56){
					map[r][c] = new Tile(TileType.Flat, r, c);
				} else if (chance < 76){
					map[r][c] = new Tile(TileType.Ice, r, c);
				} else if (chance < 86){
					map[r][c] = new Tile(TileType.IronOre, r, c);
				} else if (chance < 91){
					map[r][c] = new Tile(TileType.Crater, r, c);
				} else if (chance < 96){ 
					map[r][c] = new Tile(TileType.Mountain, r, c);
				} else {
					map[r][c] = new Tile(TileType.Volcano, r, c);
				}
			}
		}
		return map;
	}
}

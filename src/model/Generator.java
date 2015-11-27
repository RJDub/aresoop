package model;

import java.io.Serializable;
import java.util.Random;

import enums.*;

public class Generator implements Serializable {

	
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
		map[3][2] = new Tile(TileType.Ice, 3, 2);
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
	
	public static Tile[][] realisticMap(Tile[][] map){
		Random rand = new Random();
		for (int r = 0; r < map.length; r++){
			for (int c = 0; c < map[0].length; c++){
				if (r < 2 || r > map.length-3){
					map[r][c] = new Tile(TileType.Flat, r, c);
				}
				map[r][c] = new Tile(TileType.Flat, r, c);
			}
		}
		
		int row = rand.nextInt(map.length/4)  + 3;
		int col = rand.nextInt(map[0].length/4) + 3;
		map[row-1][col-1].setType(TileType.Crater);
		map[row][col-1].setType(TileType.Crater);
		map[row-1][col].setType(TileType.Crater);
		map[row][col].setType(TileType.Crater);
		
		int obstacles = 0;
		int resources = 0;
		while(true){
			int choice = rand.nextInt(5);
			switch(choice){
			case 1:
				
			}
			if (obstacles == 5 && resources == 10){
				break;
			}
		}
		
		
		
		return map;
	}
}

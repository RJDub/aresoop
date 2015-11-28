package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import buildings.Dormitory;
import buildings.Mess;
import buildings.StorageBuilding;
import enums.*;

public class Generator implements Serializable {

	public static ArrayList<Colonist> generateDefaultColonists(){
		ArrayList<Colonist> result = new ArrayList<Colonist>();
		result.add(new Colonist("Ryan", 0, 0));
		result.add(new Colonist("Mark", 0,0));
		result.add(new Colonist("Mingcheng", 0, 0));
		result.add(new Colonist("Paul", 0, 0));
		return result;
	}
	
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
	
	public static Tile[][] generateTestMap(int row_count, int col_count){
		Tile[][] map = new Tile[row_count][col_count];
		for (int r = 0; r < row_count; r++){
			for(int c = 0; c < col_count; c++){
				map[r][c] = new Tile(TileType.Flat, r,c);
			}
		}
		map[0][9] = new Tile(TileType.IronOre, 0, 9);
		map[3][5] = new Tile(TileType.Ice, 3, 5);
		map[3][7] = new Tile(TileType.Volcano, 3, 7);
		map[3][8] = new Tile(TileType.Volcano, 3, 8);
		map[3][9] = new Tile(TileType.Volcano, 3, 9);
		map[4][7] = new Tile(TileType.Crater, 4, 7);
		map[4][8] = new Tile(TileType.Crater, 4, 8);
		map[4][9] = new Tile(TileType.Crater, 4, 9);
		return map;
	}
	
	public static MotherBoard generateTestMotherBoard(int row_count, int col_count){
		MotherBoard model = new MotherBoard(Generator.generateDefaultColonists(), Generator.generateTestMap(row_count, col_count));
		model.addBuilding(new Dormitory(4, 4));
		model.addBuilding(new Mess(4, 5));
		model.addBuilding(new StorageBuilding(5	, 9));
		return model;
		
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

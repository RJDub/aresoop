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
		model.getArrColonists().get(0).incrementFatigueLevel(-801);
		model.getArrColonists().get(1).incrementHungerLevel(-801);
		model.getArrColonists().get(2).incrementHungerLevel(-801);
		model.getArrColonists().get(2).incrementFatigueLevel(-801);
		model.getArrColonists().get(3).incrementHungerLevel(-799);
		model.getArrColonists().get(3).incrementThirstLevel(-799);
		model.getArrColonists().get(3).incrementFatigueLevel(-799);
		
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
	
	public static MotherBoard generateStandardModel(int row_count, int col_count){
		MotherBoard model = new MotherBoard(Generator.generateStandardMap(row_count, col_count));
		Random r = new Random();
		for (int i = 0; i < row_count; i++){
			
			TileType t = null;
			
			int switch_random = r.nextInt(7);
			switch(switch_random){
			case 0:
				t = TileType.Volcano;
				break;
			case 1:
				t = TileType.Crater;
				break;
			case 2:
				t = TileType.Mountain;
				break;
			case 3:
				t = TileType.Ice;
				break;
			case 4:
				t = TileType.IronOre;
				break;
			case 5:
				t = TileType.Unobtainium;
				break;
			case 6:
				t = TileType.MossyRock;
				break;
			}
			int rand_r = r.nextInt(row_count);
			r = new Random();
			int rand_c = r.nextInt(col_count);
			r = new Random();
			int count = r.nextInt(10);
			createPatch(t, rand_r,rand_c, count,model);
		}
		createPatch(TileType.Ice, 5,10,5,model);
		createPatch(TileType.Mountain, 2,1,5, model);
		createPatch(TileType.Crater, 2,1,3, model);
		createPatch(TileType.Volcano, 2,1,5, model);
		createPatch(TileType.IronOre, 8,8, 6,model);
		spawnBuilding(new Dormitory(8,5), model);		
		spawnBuilding(new Mess(8,7), model);
		StorageBuilding storage = new StorageBuilding(8,6);
		storage.depositResource(50, Task.MiningMossyRock);
		storage.depositResource(50, Task.MiningIce);
		
		spawnBuilding(storage, model);
		
		spawnColonist("Paul",5,4, model);
		
		spawnColonist("Ryan",8,4, model);
		spawnColonist("Mingchen",8,4, model);
		spawnColonist("Sean",8,4, model);
		return model;
	}
	
	private static void createPatch(TileType t, int row, int col, int amt, MotherBoard model){
		int[] focus = {row,col};
		spawnTileTypes(amt,t,focus, model);
	}
	private static void spawnTileTypes(int i, TileType tile_type,int[] focus, MotherBoard model) {
		Random r = new Random();
		for (int num = 0; num < i; num++){
			int row_delta = 1;
			int col_delta = 1;
			if(r.nextInt(2) == 0){
				row_delta *= -1;
			}
			if(r.nextInt(2)==0){
				col_delta*=-1;
			}
			
			int row = focus[0] + row_delta;
			int col = focus[1] + col_delta;
			if ((row < model.getTiles().length) && (row >=0) && (col < model.getTiles()[0].length) && (col >= 0)){
				spawnTile(tile_type, row,col, model.getTiles());
			}

			
		}
		
	}
	public static boolean spawnColonist(String name, int row, int col, MotherBoard model){
		ArrayList<int[]> path = Map.findPathToTileType(row, col, TileType.Flat, model.getTiles());
		if (path != null){
			int r = path.get(path.size()-1)[0];
			int c = path.get(path.size()-1)[1];
			model.addColonist(new Colonist(name,r,c));
			return true;
		} else 
			return false;
	
	}

	public static boolean spawnTile(TileType tile_type,int row, int col, Tile[][] tiles){
		ArrayList<int[]> path = Map.findPathToTileType(row, col, TileType.Flat, tiles);
		if (path != null){
			int r = path.get(path.size()-1)[0];
			int c = path.get(path.size()-1)[1];
			
			tiles[r][c] = new Tile(tile_type, r, c);
			if (tile_type == TileType.Ice){
				tiles[r][c].setResourceAmt(20);
				
			}
			if (tile_type == TileType.IronOre){
				tiles[r][c].setResourceAmt(20);
			}
			
			if (tile_type == TileType.Unobtainium){
				tiles[r][c].setResourceAmt(20);
			}
			
			if (tile_type == TileType.MossyRock){
				tiles[r][c].setResourceAmt(20);
			}
			
			return true;
		} else 
			return false;
	}
	
	public static Tile[][] generateStandardMap(int row_count, int col_count){
		Tile[][] map = new Tile[row_count][col_count];
		for (int r = 0; r < row_count; r++){
			for(int c = 0; c < col_count; c++){
				map[r][c] = new Tile(TileType.Flat, r,c);
			}
		}

		
		return map;
	}
	
	public static boolean spawnBuilding(Building b, MotherBoard m){
		ArrayList<int[]> path = Map.findPathToTileType(b.getR(), b.getC(), TileType.Flat, m.getTiles());
		if (path != null){
			b.setRow(path.get(path.size()-1)[0]);
			b.setCol(path.get(path.size()-1)[1]);
			m.addBuilding(b);
			return true;
		} else {
			return false;
		}
			
	}
}

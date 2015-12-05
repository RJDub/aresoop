package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import buildings.Dormitory;
import buildings.Mess;
import buildings.StorageBuilding;
import enums.Action;
import enums.BuildingType;
import enums.Task;
import enums.TileType;
import model.Building;
import model.Colonist;
import model.Generator;
import model.MotherBoard;
import model.Tile;
import items.*;
public class TestAresGame {
	@Test
	public void testResourceAmount(){
		MotherBoard model = Generator.generateStandardModel(100,100);
		int total_resource = 0;
		int total_ice_count=0;
		for (int row = 0; row < 100; row++){
			for (int col = 0; col < 100; col++){
				if (model.getTiles()[row][col].getType() == TileType.Ice){
					total_ice_count++;
					total_resource += model.getTileAtLocation(row,col).getResourceAmt();
					
				}
			}
		}
		assertEquals(total_resource/total_ice_count, 20);
	}
	
	@Test
	public void testItems(){
		Colonist c = new Colonist("Jackhammer Jim",0,0);
		c.addItem(new JackHammer());
		assertEquals(0, c.getResourceAmount());
		c.execute();
		assertEquals(1, c.getResourceAmount());
		c.setTask(Task.MiningIce);
		c.execute();
		assertEquals(2, c.getResourceAmount());
		c.setTask(Task.MiningIronOre);
		c.execute();
		assertEquals(4, c.getResourceAmount());
		
		
	}
	
	
	@Test
	public void testNeedsInMotherBoard() {
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);

		model.getArrColonists().add(paul);
		
		paul.incrementThirstLevel(10);
		
		assertEquals(1010, paul.getThirstLevel());
		
		paul.incrementThirstLevel(-20);
		
		assertEquals(990, paul.getThirstLevel());
		
		assertTrue(paul.areColonistsNeedsMet());
	}
	@Test
	public void testBuildingsInMotherBoard(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		ArrayList<Building> buildings = new ArrayList<Building>();
		
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		
		Building dorm = new Dormitory(5,5);
		Building mess = new Mess(5,6);
		assertEquals(1000,dorm.getFatigueBonus());
		assertEquals(1000,mess.getHungerBonus());
		
		model.addBuilding(dorm);
		model.addBuilding(mess);
		
		Colonist paul = new Colonist("Paul", 0, 0);
		
	}
	
	
		@Test
		public void testStorageDesposit() {
			StorageBuilding building = new StorageBuilding(50, 50);
			building.depositResource(3, Task.MiningIce);
			assertEquals(3, building.getWaterAmount());
			
			StorageBuilding building2 = new StorageBuilding(50, 50);
			building2.depositResource(3, Task.MiningIronOre);
			assertEquals(3, building2.getIronOreAmount());
			
			building2.depositResource(5, Task.MiningIronOre);
			assertEquals(8, building2.getIronOreAmount());
			building2.depositResource(1000, Task.MiningIce);
			assertEquals(1000, building2.getWaterAmount());
			assertEquals(8, building2.getIronOreAmount());
			
			
		}
		
		@Test
		public void testColonists() {
			Colonist c = new Colonist("George",1,1);
			assertEquals(1000,c.getFatigueLevel());
			assertEquals(1000,c.getHungerLevel());
			assertEquals(1000,c.getThirstLevel());
			c.update(TileType.Ice);
			assertEquals(999,c.getFatigueLevel());
			assertEquals(999,c.getHungerLevel());
			assertEquals(1000,c.getThirstLevel());
			c.update(TileType.Crater);
			assertEquals(998,c.getFatigueLevel());
			assertEquals(998,c.getHungerLevel());
			assertEquals(999,c.getThirstLevel());
			assertEquals(1,c.getC());
			assertEquals(1,c.getR());
		}
	
}

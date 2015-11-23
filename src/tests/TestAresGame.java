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
	public void testItems(){
		Colonist c = new Colonist("Jackhammer Jim",0,0);
		c.setItem(new JackHammer());
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
	public void testStartInMotherboard() {
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);
		
		model.getArrColonists().add(paul);
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		
		model.printModel();
		
		System.out.println("\n\n");
		
		model.printModel();
		model.assignTask(model.getArrColonists().get(0), Task.MiningIce);
		assertEquals(Task.MiningIce, model.getArrColonists().get(0).getTask());
		
		assertEquals(Action.None, model.getArrColonists().get(0).getAction());
		assertEquals(0, model.getArrColonists().get(0).getC());
		
		model.update();
		model.update();
		model.update();

		
		//assertEquals(Action.Move, model.getArrColonists().get(0).getAction());
		
		//assertEquals(1, model.getArrColonists().get(0).getX());
		assertEquals(997, paul.getThirstLevel());
		
		model.update();
		model.update();
		model.update();
		
		assertEquals(995, paul.getThirstLevel());
		model.update();
		model.update();
		model.update();
		assertEquals(995, paul.getThirstLevel());
		
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
	public void testResourceMiningInMotherboard(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		ArrayList<Building> buildings = new ArrayList<Building>();
		
		Tile[][] tiles = Generator.generateMap2();
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);
		Colonist mingcheng = new Colonist("Mingcheng", 2, 2);
		model.addColonist(paul);
		model.addColonist(mingcheng);
		model.printModel();
		model.update();
		model.addBuilding(new StorageBuilding(5,8));
		assertEquals(0,paul.getResourceAmount());
		model.update();
		assertEquals(Action.None, paul.getAction());
		assertEquals(998,paul.getThirstLevel());
		paul.setTask(Task.MiningIce);
		model.update();
		//paul Should be at 0,1 or 1, 0
		
		assertEquals(997, paul.getThirstLevel());
		model.update();
		assertEquals(Action.Move, paul.getAction());
		assertTrue(!(paul.getPath()==null));
		assertEquals(996, paul.getThirstLevel());
		assertEquals(0,paul.getR());
		assertEquals(2,paul.getC());
		model.update();
		assertEquals(3,paul.getC());
		assertEquals(995, paul.getThirstLevel());
		model.update();
		model.update();
		model.update();
		model.update();
		model.update();
		model.update();
		assertEquals(5,paul.getC());
		assertEquals(3,paul.getR());
		assertEquals(990, paul.getThirstLevel());
		model.update();
		
		assertEquals(990, paul.getThirstLevel());
		assertEquals(2, paul.getResourceAmount());
		model.update();
		assertEquals(3,paul.getResourceAmount());
		model.update();
		model.update();
		// moving to storage? hopefully? storage at 5, 8
		//Currently at 3,5
	
		model.update();
		assertEquals(3, paul.getR());
		assertEquals(6, paul.getC());		
		assertEquals(5 ,paul.getResourceAmount());

		model.update();
		assertEquals(4, paul.getR());
		assertEquals(6, paul.getC());
		
		assertEquals(5, paul.getResourceAmount());
		assertEquals(Action.UnloadCargo, paul.getAction());
		
		model.update();
		assertEquals(5, paul.getR());
		assertEquals(6, paul.getC());
		
		
		model.update();
		assertEquals(7,paul.getC());
		assertEquals(5,paul.getR());
		assertEquals(model.getArrBuildings().get(0).getType(), BuildingType.Storage);
		
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

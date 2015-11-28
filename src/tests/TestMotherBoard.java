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
import items.JackHammer;
import model.*;

public class TestMotherBoard {
	
	@Test
	public void testNeedsFulfillment(){
		
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
	public void testNeeds() {
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
		assertEquals(1, paul.getC());
		assertEquals(0, paul.getR());
		
		assertEquals(997, paul.getThirstLevel());
		model.update();
		assertEquals(Action.Move_To_Ice, paul.getAction());
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
		assertEquals(Action.Move_To_Storage, paul.getAction());
		
		model.update();
		assertEquals(5, paul.getR());
		assertEquals(6, paul.getC());
		
		
		model.update();
		assertEquals(7,paul.getC());
		assertEquals(5,paul.getR());
		assertEquals(model.getArrBuildings().get(0).getType(), BuildingType.Storage);
		
	}
	
	@Test
	public void testResourcePathFinding(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);
		
		model.getArrColonists().add(paul);
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		
		model.assignTask(model.getArrColonists().get(0), Task.MiningIce);
		assertEquals(Task.MiningIce, model.getArrColonists().get(0).getTask());
		
		model.update();
		
		assertEquals(4, model.getArrColonists().get(0).getPath().size());
		
		model.update();
		assertEquals(3, model.getArrColonists().get(0).getPath().size());
		
	}
	
	@Test
	public void testGiveItem(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);
		model.addItem(new JackHammer());
	
		assertEquals(1, model.getArrItems().size());
		
		model.giveItem(paul, model.getArrItems().get(0));
		assertEquals("Jackhammer", paul.getItems().toString());
		assertEquals(0, model.getArrItems().size());
		
	}

}

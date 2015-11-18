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
import model.*;

public class TestMotherBoard {

	@Test
	public void testStart() {
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
		assertEquals(0, model.getArrColonists().get(0).getX());
		
		model.update();
		model.update();
		model.update();
		
		//assertEquals(Action.Move, model.getArrColonists().get(0).getAction());
		
		//assertEquals(1, model.getArrColonists().get(0).getX());
		assertEquals(999, paul.getThirstLevel());
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
		assertEquals(1000,dorm.fatigueBonus);
		assertEquals(1000,mess.hungerBonus);
		
		model.addBuilding(dorm);
		model.addBuilding(mess);
		
		Colonist paul = new Colonist("Paul", 0, 0);
		
	}
	
	@Test
	public void testResourceMining(){
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
		assertEquals(997, paul.getThirstLevel());
		model.update();
		assertEquals(996, paul.getThirstLevel());
		assertEquals(2,paul.getX());
		model.update();
		assertEquals(3,paul.getX());
		assertEquals(995, paul.getThirstLevel());
		model.update();
		model.update();
		model.update();
		model.update();
		assertEquals(3,paul.getX());
		assertEquals(5,paul.getY());
		assertEquals(993, paul.getThirstLevel());
		model.update();
		assertEquals(993, paul.getThirstLevel());
		assertEquals(3,paul.getResourceAmount());
		model.update();
		assertEquals(4,paul.getResourceAmount());
		model.update();
		assertEquals(5,paul.getResourceAmount());
		model.update();
		assertEquals(5,paul.getResourceAmount());
		assertEquals(Action.UnloadCargo,paul.getAction());
		assertEquals(4, paul.getX());
		model.update();
		model.update();
		assertEquals(5,paul.getX());
		assertEquals(8,paul.getY());
		assertEquals(model.getArrBuildings().get(0).buildingType, BuildingType.Storage);
		
	}

}

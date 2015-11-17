package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import buildings.Dormitory;
import buildings.Mess;
import enums.Action;
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
	public void testBuildingsInMotherBoard(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		ArrayList<Building> buildings = new ArrayList<Building>();
		
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		
		Building dorm = new Dormitory();
		Building mess = new Mess();
		assertEquals(1000,dorm.fatigueBonus);
		assertEquals(1000,mess.hungerBonus);
		Colonist paul = new Colonist("Paul", 0, 0);
		
	}

}

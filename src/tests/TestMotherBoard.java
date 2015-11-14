package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import enums.Action;
import enums.Task;
import model.*;

public class TestMotherBoard {

	@Test
	public void testStart() {
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		
		model.getArrColonists().add(new Colonist("Paul", 0, 0));
		model.getArrColonists().add(new Colonist("Mingcheng", 0, 0));
		
		model.printModel();
		model.update();
		
		System.out.println("\n\n");
		
		model.printModel();
		model.assignTask(model.getArrColonists().get(0), Task.Mining);
		assertEquals(Task.Mining, model.getArrColonists().get(0).getTask());
		
		assertEquals(Action.None, model.getArrColonists().get(0).getAction());
		model.update();
		
		assertEquals(Action.Move, model.getArrColonists().get(0).getAction());
	}

}

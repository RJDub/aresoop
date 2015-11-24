package tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Colonist;
import model.Generator;
import model.Map;
import model.MotherBoard;
import model.Tile;

public class TestMapPathfinding {

	@Test
	public void testPathfinding(){
		ArrayList<Colonist> colonists = new ArrayList<Colonist>();
		Tile[][] tiles = Generator.generateNeedsTestMap(new Tile[10][10]);
		MotherBoard model = new MotherBoard(colonists, tiles);
		Colonist paul = new Colonist("Paul", 0, 0);
		
		model.getArrColonists().add(paul);
		Map.printPath(Map.findFullPath(0, 0, 3, 0, tiles));
//		assertEquals(3,Map.findFullPath(0, 0, 3, 0, tiles).get(2)[0]);
//		assertEquals(2,Map.findFullPath(0, 0, 3, 0, tiles).get(1)[0]);
//		assertEquals(1,Map.findFullPath(0, 0, 3, 0, tiles).get(0)[0]);
		
	}
	@Test
	public void testPath2(){
		Tile[][] tiles  = Generator.generateMap2();
		
		ArrayList<Colonist>colonists = new ArrayList<Colonist>();
		colonists.add(new Colonist("Mingcheng", 2, 9));
		MotherBoard model = new MotherBoard(colonists,tiles);
		// volcano at 3,7
		// volcano at 3,8
		// volcano at 3,9
		// 4,7 mountain
		
		ArrayList<int[]> path = Map.findFullPath(model.getArrColonists().get(0).getR(), model.getArrColonists().get(0).getC(), 
				5,9, tiles);
		System.out.println("");
		Map.printPath(path);
		assertEquals(2,path.get(0)[0]);
		assertEquals(8,path.get(0)[1]);
		
		assertEquals(2,path.get(1)[0]);
		assertEquals(7,path.get(1)[1]);
		
		
		
		
	}
}

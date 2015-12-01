package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import buildings.StorageBuilding;
import enums.Task;
import model.Building;
import model.Generator;
import model.MotherBoard;

public class TestBuildings {

	@Test
	public void testStorageDespositWithdraw() {
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

		building.withdrawWater(2);
		assertEquals(1, building.getWaterAmount());
		
		building2.withdrawIronOre(2);
		assertEquals(6, building2.getIronOreAmount());
		
	}
	
	
	@Test
	public void testSpawnBuildings(){
		MotherBoard model = Generator.generateTestMotherBoard(10,10);
		
	}

}

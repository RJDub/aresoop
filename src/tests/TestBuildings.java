package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import buildings.StorageBuilding;
import enums.Task;
import model.Building;

public class TestBuildings {

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

}

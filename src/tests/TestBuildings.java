package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import buildings.StorageBuilding;
import model.Building;

public class TestBuildings {

	@Test
	public void testStorageDesposit() {
		StorageBuilding building = new StorageBuilding(50, 50);
		building.depositResource(3);
		assertEquals(3, building.getResourceAmount());
		
	}

}

package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import enums.TileType;
import model.Colonist;
import model.Tile;

public class TestColonists {

	@Test
	public void test() {
		Colonist c = new Colonist("George",1,1);
		int starting_fatigue = c.getFatigueLevel();
		int starting_hunger = c.getHungerLevel();
		int starting_thrist = c.getThirstLevel();
		
		assertEquals(starting_fatigue,c.getFatigueLevel());
		assertEquals(starting_hunger,c.getHungerLevel());
		assertEquals(starting_thrist,c.getThirstLevel());
		c.update(TileType.Ice);
		starting_fatigue--;
		starting_hunger--;
	
		assertEquals(starting_fatigue,c.getFatigueLevel());
		assertEquals(starting_hunger,c.getHungerLevel());
		assertEquals(starting_thrist,c.getThirstLevel());
		c.update(TileType.Crater);
		starting_fatigue--;
		starting_hunger--;
		starting_thrist--;
		
		assertEquals(starting_fatigue,c.getFatigueLevel());
		assertEquals(starting_hunger,c.getHungerLevel());
		assertEquals(starting_thrist,c.getThirstLevel());
		assertEquals(1,c.getC());
		assertEquals(1,c.getR());
	}

}

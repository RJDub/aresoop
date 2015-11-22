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
		assertEquals(1,c.getX());
		assertEquals(1,c.getY());
	}

}

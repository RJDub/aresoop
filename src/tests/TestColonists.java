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
		c.update(TileType.Ice);
	}

}

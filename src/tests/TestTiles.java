package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import enums.TileType;
import model.*;

public class TestTiles {

	@Test
	public void testTileType() {
		Tile volcano = new Tile(TileType.Volcano, 3,3);
		Tile flat = new Tile(TileType.Flat, 1,1);
		Tile crater = new Tile(TileType.Crater, 3,4);
		Tile mountain = new Tile(TileType.Mountain, 3,5);
		Tile iron = new Tile(TileType.IronOre, 3,6);
		Tile ice = new Tile(TileType.Ice, 3,7);
		
		assertEquals(999, volcano.getType().getWeight());
		assertEquals(1, flat.getType().getWeight());
		assertEquals(999, crater.getType().getWeight());
		assertEquals(999, mountain.getType().getWeight());
		assertEquals(1, iron.getType().getWeight());
		assertEquals(1, ice.getType().getWeight());
		
		assertTrue(!volcano.getType().isBuildable());
		assertTrue(flat.getType().isBuildable());
		assertTrue(!crater.getType().isBuildable());
		assertTrue(!mountain.getType().isBuildable());
		assertTrue(!iron.getType().isBuildable());
		assertTrue(!ice.getType().isBuildable());
			
	}

}

package model;

import java.awt.Point;
import java.util.ArrayList;

import enums.Terrain;

public abstract class Tile {
	private ArrayList<Colonist> occupants;
	public Terrain terrainType;
	private Point location;

	public Tile() {

	}

	public Tile(Point create, String path) {
		location = create;

	}

}

package model;

import java.awt.Point;

public abstract class Item {

	private Point location;
	
	public Item(Point input){
		location = input;
	}
	
	public Point getLocation(){
		return location;
	}
}

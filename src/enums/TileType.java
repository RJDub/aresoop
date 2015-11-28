package enums;

import java.io.Serializable;

public enum TileType implements Serializable {
	Flat(1),
	Ice(1),
	IronOre(1),
	Volcano(999),
	Crater(999),
	Mountain(999);
	
	private boolean buildable;
	
	static {
		Flat.buildable = true;
		Ice.buildable = false;
		IronOre.buildable = false;
		Volcano.buildable = false;
		Crater.buildable = false;
		Mountain.buildable = false;
	}
	
	private int weight;
	private TileType(int input){
		weight = input;
	}
	
	public int getWeight(){
		return weight;
	}
	
	public boolean isBuildable(){
		return buildable;
	}
	
	
	
}

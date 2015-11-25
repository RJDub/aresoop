package enums;

import java.io.Serializable;

public enum TileType implements Serializable {
	Flat(1),
	Ice(1),
	IronOre(1),
	Volcano(999),
	Crater(999),
	Mountain(999);
	
	private int weight;
	private TileType(int input){
		weight = input;
	}
	
	public int getWeight(){
		return weight;
	}
}

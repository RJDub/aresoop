package enums;

import java.io.Serializable;

public enum TileType implements Serializable {
	Flat(1),
	Ice(1),
	IronOre(1),
	Unobtainium(1),
	MossyRock(1),
	Volcano(999),
	Crater(999),
	Mountain(999);
	
	private boolean buildable;
	private boolean mineable;
	private String string;
	
	static {
		Flat.buildable = true;
		Ice.buildable = false;
		IronOre.buildable = false;
		Volcano.buildable = false;
		Crater.buildable = false;
		Mountain.buildable = false;
		Unobtainium.buildable=false;
		MossyRock.buildable=false;
	}
	static {
		Flat.mineable = false;
		Ice.mineable = true;
		IronOre.mineable = true;
		Volcano.mineable = false;
		Crater.mineable = false;
		Mountain.mineable = false;
		Unobtainium.mineable=true;
		MossyRock.mineable=true;
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
	
	static {
		Flat.string = "Flat";
		Ice.string = "Ice";
		IronOre.string = "Iron Ore";
		Volcano.string= "Lava";
		Crater.string= "Crater";
		Mountain.string= "Boulder";
		Unobtainium.string ="Unobtainium";
		MossyRock.string="Moss";
	}
	
	public boolean isMineable(){
		return mineable;
	}
	@Override
	public String toString(){
		return string;
	}
	
	
}

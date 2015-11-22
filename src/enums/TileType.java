package enums;

public enum TileType {
	Flat(1),
	Ice(1),
	IronOre(1),
	Volcano(999),
	Crater(3),
	Mountain(5);
	
	private int weight;
	private TileType(int input){
		weight = input;
	}
	
	public int getWeight(){
		return weight;
	}
}

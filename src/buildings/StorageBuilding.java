package buildings;
import enums.BuildingType;
import enums.Task;
import model.Building;

public class StorageBuilding extends Building {
	
	private int waterAmount;
	private int ironOreAmount;

	public StorageBuilding(int x, int y) {
		super(x, y);
		super.setType(BuildingType.Storage);
		waterAmount = 0;
		ironOreAmount = 0;
	
	}
	
	public int getIronOreAmount() {
		return ironOreAmount;
	}
	
	public int getWaterAmount() {
		return waterAmount;
	}
	
	
	public void depositResource(int amount, Task task) {
		if (task == Task.MiningIce){
			waterAmount += amount;
		}
		
		else if (task == Task.MiningIronOre){
			ironOreAmount += amount;
		}
	}

	
//
//	private Resource type;
//	private int capacity;
//	private ArrayList<Resource> resources;
//	
////	public StorageBuilding(Resource input, int cap) {
////		type = input;
////		resources = new ArrayList<Resource>();
////		capacity = cap;
////	}
//	
//	public Resource getType(){
//		return type;
//	}
//	
//	public int getCapacity(){
//		return capacity;
//	}
//	
//	public ArrayList<Resource> getAllResources(){
//		return resources;
//	}
//	
//	public void depositResource(){
//		resources.add(type);
//	}
//	
//	public void useResource(){
//		resources.remove(0);
//	}
	
}

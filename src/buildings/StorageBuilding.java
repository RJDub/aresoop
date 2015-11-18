package buildings;
import enums.BuildingType;
import model.Building;

public class StorageBuilding extends Building {

	public StorageBuilding(int x, int y) {
		super(x, y);
		super.buildingType = BuildingType.Storage;
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

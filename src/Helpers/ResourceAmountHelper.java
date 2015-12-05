package Helpers;

import buildings.StorageBuilding;
import enums.Action;
import enums.BuildingType;
import enums.TileType;
import model.Building;
import model.MotherBoard;

public class ResourceAmountHelper {
	public static int getStorageAmountFromTileType(TileType type, MotherBoard model){
		int total = 0;
		for (Building b: model.getArrBuildings()){
			if (b.getType()==BuildingType.Storage){
				if(type == TileType.Ice){
					total+=((StorageBuilding) b).getWaterAmount();
				}
				if(type == TileType.MossyRock){
					total+=((StorageBuilding) b).getFoodAmount();	
				}
				
				if(type == TileType.IronOre){
					total+=((StorageBuilding) b).getIronOreAmount();
				}
				
				if (type == TileType.Unobtainium){
					total +=((StorageBuilding) b).getUnobtainiumAmount(); 
				}
			}
				
		}
		return total;
		
	}

	public static void withdrawAmountUsingTileType(TileType type, MotherBoard model) {
		// TODO Auto-generated method stub
		
	}

}

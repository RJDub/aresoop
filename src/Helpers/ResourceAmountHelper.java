package Helpers;

import java.util.ArrayList;

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

	public static boolean withdrawAmountUsingTileType(TileType type, MotherBoard model,int amt) {
		ArrayList<Building> buildings = model.getArrBuildings();
		if (amt <= getStorageAmountFromTileType(type, model)){
			for (Building b: buildings){
				if (b.getType()==BuildingType.Storage){
					if (amt <= ((StorageBuilding) b).getResourceAmountByTileType(type)){
						((StorageBuilding) b).withdrawResourceAmountByTileType(type,amt);
						amt = 0;
					} else {
						int temp_amount = ((StorageBuilding) b).getResourceAmountByTileType(type);
						((StorageBuilding) b).withdrawResourceAmountByTileType(type,temp_amount);
						amt -= temp_amount;
					}
				}
			}
			return true;
		}
		return false;
		
	}

}

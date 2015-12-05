package view.displayables;

import buildings.StorageBuilding;
import enums.BuildingType;
import model.Building;

public class DisplayableBuilding implements DisplayableObject {
	Building building;
	public DisplayableBuilding (Building b){
		building = b;
	}
	
	@Override
	public String display() {
		String result = building.toString()+'\n';
		if (building.getType().equals(BuildingType.Storage)){
			result+="Resources: \n";
			result+="\t Water: "+((StorageBuilding)building).getWaterAmount()+'\n';
			result+="\t Iron Ore: "+((StorageBuilding)building).getIronOreAmount()+'\n';
			result+="\t Unobtainium: "+((StorageBuilding)building).getUnobtainiumAmount()+'\n';
			result+="\t Food: "+((StorageBuilding)building).getFoodAmount()+'\n';
			
		}
		result+= "\nRow: "+building.getR();
		result+= "\nCol: "+building.getC();
		
		return result + "\n\nFunction: " + building.getFunction();
		
	}

}

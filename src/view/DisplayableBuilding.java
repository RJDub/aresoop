package view;

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
			result+="resources: \n";
			result+="\t water: "+((StorageBuilding)building).getWaterAmount()+'\n';
			result+="\t iron ore: "+((StorageBuilding)building).getIronOreAmount()+'\n';
			result+="\t unobtainium: "+((StorageBuilding)building).getUnobtainiumAmount()+'\n';
			result+="\t food: "+((StorageBuilding)building).getFoodAmount()+'\n';
			
		}
		result+= "\nrow: "+building.getR();
		result+= "\ncol: "+building.getC();
		
		return result + "\n\tadd better descprition in DisplayableBuilding class";
		
	}

}

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
			result+="\t irone ore: "+((StorageBuilding)building).getIronOreAmount()+'\n';
			
		}
		
		return result + "\n\tadd better descprition in DisplayableBuilding class";
		
	}

}

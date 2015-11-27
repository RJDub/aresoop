package view;

import model.Building;

public class DisplayableBuilding implements DisplayableObject {
	Building building;
	public DisplayableBuilding (Building b){
		building = b;
	}
	@Override
	public String display() {
		return building.toString();
		
	}

}

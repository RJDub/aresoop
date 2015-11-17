package buildings;

import enums.BuildingType;
import model.Building;

	 
public class Dormitory extends Building{
	public BuildingType buildingType;
	public Dormitory(int x, int y){
		super(x, y);
		buildingType = BuildingType.Dormitory;
		super.thirstBonus = 0;
		super.fatigueBonus = 1000;
		super.hungerBonus = 0;
	}
	
	
}

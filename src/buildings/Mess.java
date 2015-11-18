package buildings;

import enums.BuildingType;
import model.Building;

public class Mess extends Building{
	public Mess(int x, int y){
		super(x,y);
		super.buildingType = BuildingType.Mess;
		super.thirstBonus = 0;
		super.fatigueBonus = 0;
		super.hungerBonus = 1000;
	}
}

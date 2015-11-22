package buildings;

import enums.BuildingType;
import model.Building;

	 
public class Dormitory extends Building{

	public Dormitory(int r, int c){
		super(r, c);
		super.setType(BuildingType.Dormitory);
		super.thirstBonus = 0;
		super.fatigueBonus = 1000;
		super.hungerBonus = 0;
	}
	
	
}

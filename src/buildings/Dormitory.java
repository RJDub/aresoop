package buildings;

import enums.BuildingType;
import model.Building;

	 
public class Dormitory extends Building{

	public Dormitory(int r, int c){
		super(r, c);
		super.setType(BuildingType.Dormitory);
		super.setThirstBonus(0);
		super.setFatigueBonus(1000);
		super.setHungerBonus(0);
	}
	
	
}

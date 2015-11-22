package buildings;

import enums.BuildingType;
import model.Building;

public class Mess extends Building{
	public Mess(int r, int c){
		super(r,c);
		super.setType(BuildingType.Mess);
		super.setThirstBonus(0);
		super.setFatigueBonus(0);
		super.setHungerBonus(1000);
	}
}

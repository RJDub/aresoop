package buildings;

import java.io.Serializable;

import enums.BuildingType;
import model.Building;

	 
public class Dormitory extends Building implements Serializable {

	public Dormitory(int r, int c){
		super(r, c);
		super.setType(BuildingType.Dormitory);
		super.setThirstBonus(0);
		super.setFatigueBonus(100);
		super.setHungerBonus(0);
	}

	@Override
	public String getFunction() {
		return "For Colonists To Sleep";
	}
	
	
}

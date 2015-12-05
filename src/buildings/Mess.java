package buildings;

import java.io.Serializable;

import enums.BuildingType;
import model.Building;

public class Mess extends Building implements Serializable {
	public Mess(int r, int c){
		super(r,c);
		super.setType(BuildingType.Mess);
		super.setThirstBonus(100);
		super.setFatigueBonus(0);
		super.setHungerBonus(100);
	}

	@Override
	public String getFunction() {
		return "For Colonists To Eat";
	}
}

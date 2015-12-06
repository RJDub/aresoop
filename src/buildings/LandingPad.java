package buildings;

import java.io.Serializable;

import enums.BuildingType;
import model.Building;

public class LandingPad extends Building implements Serializable{

	public LandingPad(int row, int column) {
		super(row, column);
		super.setType(BuildingType.LandingPad);
		super.setThirstBonus(0);
		super.setFatigueBonus(0);
		super.setHungerBonus(0);
	}

	@Override
	public String getFunction() {
		
		return "New colonist will spawn at here.";
	}
}

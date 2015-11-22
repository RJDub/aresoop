package buildings;

import enums.BuildingType;
import model.Building;

public class Mess extends Building{
	public Mess(int r, int c){
		super(r,c);
		super.setType(BuildingType.Mess);
		super.thirstBonus = 0;
		super.fatigueBonus = 0;
		super.hungerBonus = 1000;
	}
}

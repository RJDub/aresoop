package buildings;

import model.Building;

public class Mess extends Building{
	public Mess(){
		super.thirstBonus = 0;
		super.fatigueBonus = 0;
		super.hungerBonus = 1000;
	}
}

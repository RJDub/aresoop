package view;

import model.Colonist;

public class DisplayableColonist implements DisplayableObject
{
	private Colonist c;
	public DisplayableColonist(Colonist c ){
		this.c = c;
	}
	@Override
	public String display() {
		String t = 
				"\n\n\tName:   " + c.getName()
				+ "\n\tHunger:   " + c.getHungerLevel()
				+ "\n\tThirst:   " + c.getThirstLevel()
				+ "\n\tFatigue:   " + c.getFatigueLevel()
				+ "\n\tTask:   " + c.getTask()
				+ "\n\tAction:   " + c.getAction()
				+ "\n\tResource Amount:   " + c.getResourceAmount();
				if (c.getItems()!=null){
					t = t+ "\n\tItem:   " + c.getItems().toString();
				} else t = t+ "\n\tItem: None ";
					
				t = t+ "\n\tColumn:   " + c.getC()
				+ "\n\tRow:   " + c.getR();
		return t;
	}

}

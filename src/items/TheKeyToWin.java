package items;

import enums.Task;
import model.Item;

public class TheKeyToWin extends Item {
	public TheKeyToWin() {
		super();
		task= Task.MiningUnobtainium;
		bonus_amount = 1;
		owner = null;
	}
	
	@Override
	public String toString(){
		return "TheKeyToWin";
	}
	
}

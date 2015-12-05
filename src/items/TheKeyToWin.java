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
	
	public String getFunction() {
		return "Mining Unobtainium Faster";
	}
	
	@Override
	public String toString(){
		return "TheKeyToWin";
	}
	
}

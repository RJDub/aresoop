package items;

import enums.Task;
import model.Item;

public class IceDrill extends Item {
	
	public IceDrill() {
		super();
		task= Task.MiningIce;
		bonus_amount = 1;
		owner = null;
	}
	
	public String getFunction() {
		return "Mining Ice Faster";
	}
	
	@Override
	public String toString(){
		return "IceDrill";
	}
}

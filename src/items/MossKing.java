package items;

import java.awt.Point;
import java.io.Serializable;

import enums.Task;
import model.*;
public class MossKing extends Item implements Serializable {

	public MossKing() {
		super();
		task= Task.MiningMossyRock;
		bonus_amount = 1;
		owner = null;
	}
	
	@Override
	public String toString(){
		return "MossKing";
	}
	
}

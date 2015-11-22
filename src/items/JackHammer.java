package items;
import java.awt.Point;

import enums.Task;
import model.*;

public class JackHammer extends Item{

	public JackHammer() {
		super();
		task= Task.MiningIronOre;
		bonus_amount = 1;
		
	}

}

package items;
import java.awt.Point;
import java.io.Serializable;

import enums.Task;
import model.*;

public class JackHammer extends Item implements Serializable {

	public JackHammer() {
		super();
		task= Task.MiningIronOre;
		bonus_amount = 1;
		owner = null;
		
	}
	@Override
	public String toString(){
		return "Jackhammer";
	}

}

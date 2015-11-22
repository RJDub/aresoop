package items;
import java.awt.Point;

import enums.Task;
import model.*;

public class JackHammer extends Item{
	public Task task_bonus;
	public int bonus_amount;
	public JackHammer() {
		super();
		task_bonus = Task.MiningIronOre;
		bonus_amount = 1;
		
	}

}

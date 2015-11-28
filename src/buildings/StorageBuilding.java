package buildings;
import java.io.Serializable;

import enums.BuildingType;
import enums.Task;
import model.Building;

public class StorageBuilding extends Building  implements Serializable {
	
	private int waterAmount;
	private int ironOreAmount;

	public StorageBuilding(int x, int y) {
		super(x, y);
		super.setType(BuildingType.Storage);
		waterAmount = 0;
		ironOreAmount = 0;
	
	}
	
	public int getIronOreAmount() {
		return ironOreAmount;
	}
	
	public int getWaterAmount() {
		return waterAmount;
	}
	
	
	public void depositResource(int amount, Task task) {
		if (task == Task.MiningIce){
			waterAmount += amount;
		}
		
		else if (task == Task.MiningIronOre){
			ironOreAmount += amount;
		}
	}
}

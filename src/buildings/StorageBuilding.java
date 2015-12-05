package buildings;
import java.io.Serializable;

import enums.BuildingType;
import enums.Task;
import enums.TileType;
import model.Building;

public class StorageBuilding extends Building  implements Serializable {
	
	private int waterAmount;
	private int ironOreAmount;
	private int foodAmount;
	private int unobtainiumAmount;
	
	public StorageBuilding(int x, int y) {
		super(x, y);
		super.setType(BuildingType.Storage);
		waterAmount = 0;
		ironOreAmount = 0;
		foodAmount = 0;
		unobtainiumAmount = 0;
	
	}
	
	public int getIronOreAmount() {
		return ironOreAmount;
	}
	
	public int getWaterAmount() {
		return waterAmount;
	}
	
	public int getFoodAmount() {
		return foodAmount;
	}
	
	public int getUnobtainiumAmount() {
		return unobtainiumAmount;
	}
	
	
	public void depositResource(int amount, Task task) {
		if (task == Task.MiningIce){
			waterAmount += amount;
		}
		
		else if (task == Task.MiningIronOre){
			ironOreAmount += amount;
		}
		
		else if (task == Task.MiningMossyRock){
			foodAmount += amount;
		}
		
		else if (task == Task.MiningUnobtainium){
			unobtainiumAmount += amount;
		}
	}
	
	public boolean withdrawWater(int amount) {
		if (amount<=waterAmount) {
			waterAmount-=amount;
			return true;
		}
		else
			return false;
	}
	
	public boolean withdrawIronOre(int amount) {
		if (amount<=ironOreAmount) {
			ironOreAmount-=amount;
			return true;
		}
		else
			return false;
	}
	
	public boolean withdrawFood(int amount) {
		if (amount<=foodAmount) {
			foodAmount-=amount;
			return true;
		}
		else
			return false;
	}
	
	public boolean withdrawUnobtainium(int amount) {
		if (amount<=unobtainiumAmount) {
			unobtainiumAmount-=amount;
			return true;
		}
		else
			return false;
	}
	
	public int getResourceAmountByTileType(TileType type){
		if(type == TileType.Ice){
			return this.getWaterAmount();
		}
		if(type == TileType.MossyRock){
			return this.getFoodAmount();	
		}
		
		if(type == TileType.IronOre){
			return this.getIronOreAmount();
		}
		
		if (type == TileType.Unobtainium){
			return this.getUnobtainiumAmount(); 
		}
		return 0;
	}
	
	public boolean withdrawResourceAmountByTileType(TileType type, int amt){
		if(type == TileType.Ice){
			return this.withdrawWater(amt);
		}
		if(type == TileType.MossyRock){
			return this.withdrawFood(amt);	
		}
		
		if(type == TileType.IronOre){
			return this.withdrawIronOre(amt);
		}
		
		if (type == TileType.Unobtainium){
			return this.withdrawUnobtainium(amt);
			
		}
		return false;
	}
}

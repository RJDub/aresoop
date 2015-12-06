package model;

import java.io.Serializable;
import java.util.ArrayList;

import enums.*;
import Helpers.Constants;


public class Colonist implements Serializable {

	private int hunger;
	private int hunger_threshold;
	private int thirst;
	private int thirst_threshold;
	private int fatigue;
	private int fatigue_threshold;
	private String name;
	private Task task;
	private Action action;
	private int r, c;
	private int resourceAmount;
	private Item items;
	private int max_food_consumption = 1000;
	private int max_water_consumption = 1000;

	int capacity; // need to think what this will do!

	private ArrayList<int[]> path;

	public Colonist(String input, int row, int column) {
		name = input;
		task = Task.None;
		action = Action.None;
		r = row;
		c = column;

		thirst = Constants.COLONIST_START_THIRST;
		fatigue = Constants.COLONIST_START_FATIGUE;
		hunger = Constants.COLONIST_START_HUNGER;
		
		thirst_threshold = Constants.COLONIST_START_THIRST_THRESHOLD;
		hunger_threshold = Constants.COLONIST_START_FATIGUE_THRESHOLD;
		fatigue_threshold = Constants.COLONIST_START_HUNGER_THRESHOLD;
		
		capacity = Constants.COLONIST_DEFAULT_CAPACITY;
		
		resourceAmount = 0;
		items = null;
		path = null;

	}

	public void update(TileType tileType) {
		// this will evaluate what needs are lowered during the update call.
		if (tileType == TileType.Ice) {
			// dont decrement thirst level.
		} else {
			incrementThirstLevel(-1);
		}

		incrementHungerLevel(-1);
		incrementFatigueLevel(-1);
	}

	public int getResourceAmount() {
		return resourceAmount;
	}

	public String getName() {
		return name;
	}

	public int getC() {
		return c;
	}

	public int getR() {
		return r;
	}

	public Task getTask() {
		return task;
	}

	public Action getAction() {
		return action;
	}

	public int getThirstLevel() {
		return thirst;
	}
	
	public int getThirstThreshold(){
		return thirst_threshold;
	}
	public int getHungerLevel() {
		return hunger;
	}
	
	public int getHungerThreshold(){
		return hunger_threshold;
	}

	public int getFatigueLevel() {
		return fatigue;
	}

	public int getFatigueThreshold() {
		return fatigue_threshold;
	}

	public ArrayList<int[]> getPath() {
		return path;
	}

	public void setC(int col) {
		this.c = col;
	}

	public void setR(int row) {
		this.r = row;
	}

	public void setTask(Task input) {
		task = input;
	}

	public void setAction(Action input) {
		action = input;
	}

	public void setPath(ArrayList<int[]> in) {
		path = in;
	}

	public int[] updatePath() {
		
		int[] result=new int[2];
		if (path==null || path.size() == 0) {
			path = null;
			result[0] = r;
			result[1] = c;
		} else {
			result = path.remove(0);
		}
		
		return result;
	}

	public void incrementThirstLevel(int in) {
		thirst += in;
	}

	public void incrementHungerLevel(int in) {
		hunger += in;
	}

	public void incrementFatigueLevel(int in) {
		fatigue += in;
	}

	public boolean areColonistsNeedsMet() {

		if (getThirstLevel() > getThirstThreshold() && getHungerLevel() > getHungerThreshold() && getFatigueLevel() > getFatigueThreshold()) {
			return true;
		}

		else
			return false;

	}

	@Override
	public String toString() {
		return ("Colonist: " + getName() + "\nLocation: (" + getC() + ", " + getR() + ")\nTask: " + getTask()
				+ "\nAction: " + getAction() + "\nThirst: " + getThirstLevel());
	}

	public void execute() {
		int standard_amount = 1;
		// TODO go through Items
		if (items != null && task == items.task)
			standard_amount += items.bonus_amount;
		resourceAmount += standard_amount;

	}
	
	public int getMiningAmount(){
		int standard_amount = 1;
		// TODO go through Items
		if (items != null && task == items.task)
			standard_amount += items.bonus_amount;
		return standard_amount;
	}

	public boolean hasCapacityToMineResources() {
		return resourceAmount < capacity;
	}

	public int withdrawResources() {
		int retAmount = resourceAmount;
		resourceAmount = 0;
		return retAmount;

	}

	public void addItem(Item i) {
		items = i;
	}

	public void removeItem(Item i) {
		items = null;
	}

	public Item getItems() {
		return items;

	}

	public boolean isAtBuilding(ArrayList<Building> buildings) {
		for (Building b : buildings) {
			if (b.getC() == this.getC() && b.getR() == this.getR()) {
				return true;
			}
		}
		return false;

	}

	public void setNeedsAction() {
		if (getFatigueLevel() < getFatigueThreshold()) {
			setAction(Action.FindSleep);
		} else if (getHungerLevel() < getHungerThreshold()){
			setAction(Action.FindFood);
		} else if (getThirstLevel() < getThirstThreshold()){
			setAction(Action.FindWater);
		}

	}

	public boolean isAlive() {
		if (getHungerLevel() == 0 || getThirstLevel() == 0 || getFatigueLevel() == 0) {
			return false;
		} else {
			return true;
		}
		}

	public boolean isAtStorageBuilding(ArrayList<Building> buildings) {
		for(Building b: buildings){
			if (b.getType()==BuildingType.Storage){
				if ((b.getC() == this.getC()) && (b.getR() == this.getR())){
					return true;
				}
			}
		}
		return false;
	}

	public void mine(int amt_to_mine) {
		resourceAmount+=amt_to_mine;
	}

	public int getMaxFoodConsumption(){
		return max_food_consumption;
	}
	public int getMaxWaterConsumption(){
		return max_water_consumption;
	}
}

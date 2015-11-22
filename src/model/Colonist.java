package model;

import enums.*;

public class Colonist {
	
	private int hunger;
	private int thirst;
	private int fatigue;
	private String name;
	private Task task;
	private Action action;
	private int x,y;
	private int resourceAmount;
	int capacity; // need to think what this will do!
	public Colonist(String input, int xIn, int yIn) {
		name = input;
		task = Task.None;
		action = Action.None;
		x = xIn;
		y = yIn;
		thirst = 1000;
		hunger = 1000;
		fatigue = 1000;
		resourceAmount = 0;
		capacity = 5;
		
		
	}
	
	public void update(TileType tileType){
		// this will evaluate what needs are lowered during the update call.
		if (tileType==TileType.Ice){
			//dont decrement thirst level.
		}else{
			incrementThirstLevel(-1);
		}
		incrementHungerLevel(-1);
		incrementFatigueLevel(-1);
	}
	
	public int getResourceAmount(){
		return resourceAmount;
	}

	public String getName() {
		return name;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Task getTask(){
		return task;
	}
	
	public Action getAction(){
		return action;
	}
	
	public int getThirstLevel(){
		return thirst;
	}
	
	public int getHungerLevel(){
		return hunger;
	}
	
	public int getFatigueLevel(){
		return fatigue;
	}

	public void setX(int x){
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setTask(Task input){
		task = input;
	}
	
	public void setAction(Action input){
		action = input;
	}
	
	public void incrementThirstLevel(int in){
		thirst += in;
	}
	
	public void incrementHungerLevel(int in){
		hunger += in;
	}
	
	public void incrementFatigueLevel(int in){
		fatigue += in;
	}
	
	public boolean areColonistsNeedsMet(){
	
		if (getThirstLevel() > 0 && getHungerLevel() > 0 && getFatigueLevel() > 0) {
			return true;
		}
		
		else
			return false;
		
	}
	
	@Override
	public String toString(){
		return ("Colonist: " + getName() + "\nLocation: (" + getX() + ", " + getY() + ")\nTask: " + getTask() + "\nAction: " + getAction() + "\nThirst: " + getThirstLevel());
	}

	public void execute() {
		resourceAmount++;
		
	}

	public boolean hasCapacityToMineResources() {
		return resourceAmount < capacity;
	}
	
	public int withdrawResources(){
		int retAmount = resourceAmount;
		resourceAmount = 0;
		return retAmount;
		
	}


//	public boolean isAlive() {
//		if (HungerLevel == Hunger.Dead || ThirstLevel == Thirst.Dead || FatigueLevel == Fatigue.Dead) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//

//	public Colonist switchOccupation(String decision, String identification) {
//		if (decision.compareTo("Worker") == 0){
//			return new Worker(identification);
//		} else if (decision.compareTo("Farmer") == 0){
//			return new Farmer(identification);
//		} else {
//			return new Explorer(identification);
//		}
//	}
//	
//	public ProfessionalColonist upgradeColonist(String type, String identification) {
//		if (type.compareTo("Worker") == 0){
//			return new Engineer(identification);
//		} else if (type.compareTo("Farmer") == 0){
//			return new Advocate(identification);
//		} else {
//			return new Scientist(identification);
//		}
//	}

}

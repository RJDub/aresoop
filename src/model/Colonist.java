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

	public Colonist(String input, int xIn, int yIn) {
		name = input;
		task = Task.None;
		action = Action.None;
		x = xIn;
		y = yIn;
		thirst = 1000;
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
	
	public void incThirstLevel(int in){
		thirst += in;
	}
	
	public void incHungerLevel(int in){
		hunger += in;
	}
	
	public void incFatigueLevel(int in){
		fatigue += in;
	}
	
	
	
	@Override
	public String toString(){
		return ("Colonist: " + getName() + "\nLocation: (" + getX() + ", " + getY() + ")\nTask: " + getTask() + "\nAction: " + getAction() + "\nThirst: " + getThirstLevel());
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

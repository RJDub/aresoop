package model;

import enums.*;

public class Colonist {
//	Hunger HungerLevel;
//	Thirst ThirstLevel;
//	Fatigue FatigueLevel;
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
	
	
	
	
	
	@Override
	public String toString(){
		return ("Colonist: " + getName() + "; Location: (" + getX() + ", " + getY() + "); Task: " + getTask() + "; Action: " + getAction());
	}

//	public Hunger getHunger() {
//		return HungerLevel;
//	}
//
//	public Thirst getThirst() {
//		return ThirstLevel;
//	}
//	
//
//	public Fatigue getFatigue() {
//		return FatigueLevel;
//	}
//
//	
//	
//	public void makeThirsty(){
//		if (getThirst() == Thirst.Quenched){
//			ThirstLevel = Thirst.Normal;
//		} else if (getThirst() == Thirst.Normal){
//			ThirstLevel = Thirst.Parched;
//		} else if (getThirst() == Thirst.Parched){
//			ThirstLevel = Thirst.Dead;
//		}
//	}
//	
//	public void makeHungry(){
//		if (getHunger() == Hunger.Full){
//			HungerLevel = Hunger.Normal;
//		} else if (getHunger() == Hunger.Normal){
//			HungerLevel = Hunger.Hungry;
//		} else if (getHunger() == Hunger.Hungry){
//			HungerLevel = Hunger.Starving;
//		} else if (getHunger() == Hunger.Starving){
//			HungerLevel = Hunger.Dead;
//		}
//	}
//	
//	public void makeQuenched(){
//		if (getThirst() == Thirst.Parched){
//			ThirstLevel = Thirst.Normal;
//		} else if (getThirst() == Thirst.Normal){
//			ThirstLevel = Thirst.Quenched;
//		}
//	}
//	
//	public void makeFull(){
//		if (getHunger() == Hunger.Starving){
//			HungerLevel = Hunger.Hungry;
//		} else if (getHunger() == Hunger.Hungry){
//			HungerLevel = Hunger.Normal;
//		} else if (getHunger() == Hunger.Normal){
//			HungerLevel = Hunger.Full;
//		}
//	}
//
//	public boolean isAlive() {
//		if (HungerLevel == Hunger.Dead || ThirstLevel == Thirst.Dead || FatigueLevel == Fatigue.Dead) {
//			return false;
//		} else {
//			return true;
//		}
//	}
//
////	public void moveToDestination(Point dest) {
////		double destx = dest.getX();
////		double desty = dest.getY();
////
////		while (true) {
////			if (Location.getX() < destx && Location.getY() < desty) {
////				Location.setLocation(Location.getX() + 1, Location.getY() + 1);
////			} else if (Location.getX() < destx && Location.getY() == desty) {
////				Location.setLocation(Location.getX() + 1, Location.getY());
////			} else if (Location.getX() < destx && Location.getY() > desty) {
////				Location.setLocation(Location.getX() + 1, Location.getY() - 1);
////			} else if (Location.getX() == destx && Location.getY() < desty) {
////				Location.setLocation(Location.getX(), Location.getY() + 1);
////			} else if (Location.getX() == destx && Location.getY() == desty) {
////				Location.setLocation(Location.getX(), Location.getY());
////			} else if (Location.getX() == destx && Location.getY() > desty) {
////				Location.setLocation(Location.getX(), Location.getY() - 1);
////			} else if (Location.getX() > destx && Location.getY() < desty) {
////				Location.setLocation(Location.getX() - 1, Location.getY() + 1);
////			} else if (Location.getX() > destx && Location.getY() == desty) {
////				Location.setLocation(Location.getX() - 1, Location.getY());
////			} else if (Location.getX() > destx && Location.getY() > desty) {
////				Location.setLocation(Location.getX() - 1, Location.getY() - 1);
////			} else {
////				break;
////			}
////		}
////	}
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

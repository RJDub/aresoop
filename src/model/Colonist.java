package model;

import java.awt.Point;

import colonists.Advocate;
import colonists.Engineer;
import colonists.Explorer;
import colonists.Farmer;
import colonists.ProfessionalColonist;
import colonists.Scientist;
import colonists.Worker;
import enums.Fatigue;
import enums.Hunger;
import enums.Thirst;

public abstract class Colonist {
	Hunger HungerLevel;
	Thirst ThirstLevel;
	Fatigue FatigueLevel;
	String name;
	
	Point Location;

	public Colonist(String input) {
		name = input;
	}

	public String getName() {
		return name;
	}

	public Hunger getHunger() {
		return HungerLevel;
	}

	public Thirst getThirst() {
		return ThirstLevel;
	}

	public Fatigue getFatigue() {
		return FatigueLevel;
	}

	public double getXcoord() {
		return Location.getX();
	}

	public double getYcoord() {
		return Location.getY();
	}
	
	public void makeThirsty(){
		if (getThirst() == Thirst.Quenched){
			ThirstLevel = Thirst.Normal;
		} else if (getThirst() == Thirst.Normal){
			ThirstLevel = Thirst.Parched;
		} else if (getThirst() == Thirst.Parched){
			ThirstLevel = Thirst.Dead;
		}
	}
	
	public void makeQuenched(){
		if (getThirst() == Thirst.Parched){
			ThirstLevel = Thirst.Normal;
		} else if (getThirst() == Thirst.Normal){
			ThirstLevel = Thirst.Quenched;
		}
	}

	public boolean isAlive() {
		if (HungerLevel == Hunger.Dead || ThirstLevel == Thirst.Dead || FatigueLevel == Fatigue.Dead) {
			return false;
		} else {
			return true;
		}
	}

	public void moveToDestination(Point dest) {
		double destx = dest.getX();
		double desty = dest.getY();

		while (true) {
			if (Location.getX() < destx && Location.getY() < desty) {
				Location.setLocation(Location.getX() + 1, Location.getY() + 1);
			} else if (Location.getX() < destx && Location.getY() == desty) {
				Location.setLocation(Location.getX() + 1, Location.getY());
			} else if (Location.getX() < destx && Location.getY() > desty) {
				Location.setLocation(Location.getX() + 1, Location.getY() - 1);
			} else if (Location.getX() == destx && Location.getY() < desty) {
				Location.setLocation(Location.getX(), Location.getY() + 1);
			} else if (Location.getX() == destx && Location.getY() == desty) {
				Location.setLocation(Location.getX(), Location.getY());
			} else if (Location.getX() == destx && Location.getY() > desty) {
				Location.setLocation(Location.getX(), Location.getY() - 1);
			} else if (Location.getX() > destx && Location.getY() < desty) {
				Location.setLocation(Location.getX() - 1, Location.getY() + 1);
			} else if (Location.getX() > destx && Location.getY() == desty) {
				Location.setLocation(Location.getX() - 1, Location.getY());
			} else if (Location.getX() > destx && Location.getY() > desty) {
				Location.setLocation(Location.getX() - 1, Location.getY() - 1);
			} else {
				break;
			}
		}
	}

	public Colonist switchOccupation(String decision, String identification) {
		if (decision.compareTo("Worker") == 0){
			return new Worker(identification);
		} else if (decision.compareTo("Farmer") == 0){
			return new Farmer(identification);
		} else {
			return new Explorer(identification);
		}
	}
	
	public ProfessionalColonist upgradeColonist(String type, String identification) {
		if (type.compareTo("Worker") == 0){
			return new Engineer(identification);
		} else if (type.compareTo("Farmer") == 0){
			return new Advocate(identification);
		} else {
			return new Scientist(identification);
		}
	}

}

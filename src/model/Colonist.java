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
	int id;
	Point Location;

	public Colonist(int input) {
		id = input;
	}

	public int getID() {
		return id;
	}

	public Hunger getHungry() {
		return HungerLevel;
	}

	public Thirst getThirsty() {
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

	public Colonist switchOccupation(String decision, int identification) {
		if (decision.compareTo("Worker") == 0){
			return new Worker(identification);
		} else if (decision.compareTo("Farmer") == 0){
			return new Farmer(identification);
		} else {
			return new Explorer(identification);
		}
	}
	
	public ProfessionalColonist upgradeColonist(String type, int identification) {
		if (type.compareTo("Worker") == 0){
			return new Engineer(identification);
		} else if (type.compareTo("Farmer") == 0){
			return new Advocate(identification);
		} else {
			return new Scientist(identification);
		}
	}

}

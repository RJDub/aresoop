package colonists;

public abstract class Colonist {
	Hunger HungerLevel;
	Thirst ThirstLevel;
	Fatigue FatigueLevel;
	int id;
	
	public Colonist(int input){
		id = input;
	}
	
	public int getID(){
		return id;
	}
	
	public Hunger getHungry(){
		return HungerLevel;
	}
	
	public Thirst getThirsty(){
		return ThirstLevel;
	}
	
	public Fatigue getFatigue(){
		return FatigueLevel;
	}
	
	public boolean isAlive(){
		if (HungerLevel == Hunger.Dead || ThirstLevel == Thirst.Dead || FatigueLevel == Fatigue.Dead){
			return false;
		} else {
			return true;
		}
	}
}

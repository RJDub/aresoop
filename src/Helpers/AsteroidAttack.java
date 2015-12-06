package Helpers;

import java.util.ArrayList;
import java.util.Random;

import model.Building;
import model.MotherBoard;

public class AsteroidAttack {
	public static String asteroidAttack(MotherBoard model){
		ArrayList<Building> buildings = model.getArrBuildings();
		int landingPadLocation = LandingPadInfo.getLandingPadInArrayList(model);
		int size = buildings.size();
		Random r = new Random();
		
		int building_to_blow_up = r.nextInt(size);
		if (size == 1){
			return "no buildings left";
		}
		while (building_to_blow_up == landingPadLocation){
			building_to_blow_up = r.nextInt(size);
		}
		String return_string = buildings.get(building_to_blow_up).toString();
		buildings.remove(building_to_blow_up);
		return return_string;
	}

}

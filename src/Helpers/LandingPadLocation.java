package Helpers;

import java.util.ArrayList;

import enums.BuildingType;
import model.Building;
import model.MotherBoard;

public class LandingPadLocation {
	public static int[] getLandingPadLocation(MotherBoard model){
		ArrayList<Building> buildings = model.getArrBuildings();
		for (Building b: buildings){
			if(b.getType()==BuildingType.LandingPad){
				int[] values = {b.getR(),b.getC()};
				return values;
			}
		}
		int [] values = {0,0};
		return values;
	}
}

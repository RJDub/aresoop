package Helpers;

import java.util.ArrayList;

import model.Building;
import model.MotherBoard;

public class BuildingHelper {
	public static boolean hasNoBuildings(int row, int col, MotherBoard model){
		ArrayList<Building> buildings = model.getArrBuildings();
		for (Building b : buildings){
			if (b.getR() == row && b.getC() == col){
				return false;
			}
		}
		return true;
	}
	
	public static Building returnBuildingAt(int row, int col, MotherBoard model) {
		for (Building b: model.getArrBuildings()) {
			if (b.getR() == row && b.getC() == col)
				return b;
		}
		return null;
	}
}

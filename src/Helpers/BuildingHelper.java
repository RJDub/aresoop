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
}

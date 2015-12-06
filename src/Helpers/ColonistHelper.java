package Helpers;
import java.util.ArrayList;

import model.*;

public class ColonistHelper {
	public static void cleanupColonists(ArrayList<Colonist> colonists){
		boolean flag = false;
		while (!flag){
			
			int i = 0;
			flag = true;
			while (i < colonists.size()){
				if (!colonists.get(i).isAlive()){
					colonists.remove(i);
					flag = false;
					break;
				}
				i++;
			}
			
		}
	}
}

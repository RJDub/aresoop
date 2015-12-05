package view;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import buildings.StorageBuilding;
import enums.BuildingType;
import enums.TileType;
import model.Building;
import model.MotherBoard;

public class ModelStatusMonitor extends JPanel implements Observer {
	MotherBoard model;
	int total_stored_water;
	int total_stored_iron;
	int total_stored_food;
	int total_stored_unobtainium;
	JTextArea text_area;
	
	public ModelStatusMonitor(MotherBoard m){
		
		model = m;
		text_area = new JTextArea(10,10);
		
		text_area.setText("testing");
		text_area.setEditable(false);
		this.add(text_area);
		
	}
	
	public int getTotalAmount(TileType t){
		this.updateStorageAmounts();
		switch (t){
		case Ice:
			return total_stored_water;
			
		case IronOre:
			return total_stored_iron;
			
		case Unobtainium:
			return total_stored_unobtainium;
			
		case MossyRock:
			return total_stored_food;
		default:
			return 0;
		}
	}
	
	private void updateStorageAmounts(){
		total_stored_water=0;
		total_stored_iron=0;
		total_stored_food=0;
		total_stored_unobtainium=0;
		for (Building b: model.getArrBuildings()){
			if (b.getType()==BuildingType.Storage){
				total_stored_water+=((StorageBuilding) b).getWaterAmount();
				total_stored_iron+=((StorageBuilding) b).getIronOreAmount();
				total_stored_food+=((StorageBuilding) b).getFoodAmount();
				total_stored_unobtainium+=((StorageBuilding) b).getUnobtainiumAmount();
			}
				
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateStorageAmounts();
		updateDisplay();
		
	}

	private void updateDisplay() {
		String text = "";
		text+="Total Amounts: \n";
		text+="Total Water: "+ total_stored_water +"\n";
		text+="Total Food: "+ total_stored_food+"\n";
		text+="Total Iron: "+ total_stored_iron+"\n";
		text+="Total Unobtainium: "+ total_stored_unobtainium+"\n";
		text_area.setText(text);
	}
	
}

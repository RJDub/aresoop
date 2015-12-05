package view;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import buildings.StorageBuilding;
import enums.BuildingType;
import enums.TileType;
import model.Building;
import model.MotherBoard;

public class ModelStatusMonitor extends JPanel implements Observer {
	private MotherBoard model;
	private int wTotal;
	private int iTotal;
	private int fTotal;
	private int uTotal;
	
	private JLabel water;
	private JLabel food;
	private JLabel iron;
	private JLabel unob;
	
	private JLabel wAmount;
	private JLabel fAmount;
	private JLabel iAmount;
	private JLabel uAmount;
	
	public ModelStatusMonitor(MotherBoard m, int width){
		this.setLayout(null);
		this.setVisible(true);
		this.setBackground(Color.BLACK);
		this.setLocation(0, 0);
		this.setSize(width, 30);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		model = m;
		
//		Font AresFrame.BOLD_FONT = new Font(Font.MONOSPACED, Font.BOLD, 16);
		
		water = new JLabel("WATER: ");
		food = new JLabel("FOOD: ");
		iron = new JLabel("IRON ORE: ");
		unob = new JLabel("UNOBTANIUM: ");
		
		wAmount = new JLabel("0");
		fAmount = new JLabel("0");
		iAmount = new JLabel("0");
		uAmount = new JLabel("0");
		
		water.setVisible(true);
		water.setForeground(Color.YELLOW);
		water.setFont(AresFrame.BOLD_FONT);
		water.setLocation(300, 0);
		water.setSize(80, 30);
		wAmount.setVisible(true);
		wAmount.setForeground(Color.YELLOW);
		wAmount.setFont(AresFrame.BOLD_FONT);
		wAmount.setLocation(380, 0);
		wAmount.setSize(30, 30);
		
		food.setVisible(true);
		food.setForeground(Color.YELLOW);
		food.setFont(AresFrame.BOLD_FONT);
		food.setLocation(500, 0);
		food.setSize(60, 30);
		fAmount.setVisible(true);
		fAmount.setForeground(Color.YELLOW);
		fAmount.setFont(AresFrame.BOLD_FONT);
		fAmount.setLocation(560, 0);
		fAmount.setSize(30, 30);
		
		iron.setVisible(true);
		iron.setForeground(Color.YELLOW);
		iron.setFont(AresFrame.BOLD_FONT);
		iron.setLocation(680, 0);
		iron.setSize(100, 30);
		iAmount.setVisible(true);
		iAmount.setForeground(Color.YELLOW);
		iAmount.setFont(AresFrame.BOLD_FONT);
		iAmount.setLocation(800, 0);
		iAmount.setSize(30, 30);
		
		unob.setVisible(true);
		unob.setForeground(Color.YELLOW);
		unob.setFont(AresFrame.BOLD_FONT);
		unob.setLocation(920, 0);
		unob.setSize(130, 30);
		uAmount.setVisible(true);
		uAmount.setForeground(Color.YELLOW);
		uAmount.setFont(AresFrame.BOLD_FONT);
		uAmount.setLocation(1050, 0);
		uAmount.setSize(30, 30);
		
		this.add(water);
		this.add(wAmount);
		this.add(food);
		this.add(fAmount);
		this.add(iron);
		this.add(iAmount);
		this.add(unob);
		this.add(uAmount);
	}
	
	public int getTotalAmount(TileType t){
		this.updateStorageAmounts();
		switch (t){
		case Ice:
			return wTotal;
		case IronOre:
			return iTotal;
		case Unobtainium:
			return uTotal;
		case MossyRock:
			return fTotal;
		default:
			return 0;
		}
	}
	
	private void updateStorageAmounts(){
		wTotal=0;
		iTotal=0;
		fTotal=0;
		uTotal=0;
		for (Building b: model.getArrBuildings()){
			if (b.getType()==BuildingType.Storage){
				wTotal+=((StorageBuilding) b).getWaterAmount();
				iTotal+=((StorageBuilding) b).getIronOreAmount();
				fTotal+=((StorageBuilding) b).getFoodAmount();
				uTotal+=((StorageBuilding) b).getUnobtainiumAmount();
			}
		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		updateStorageAmounts();
		updateDisplay();	
	}

	private void updateDisplay() {
		wAmount.setText(Integer.toString(wTotal));
		fAmount.setText(Integer.toString(fTotal));
		iAmount.setText(Integer.toString(iTotal));
		uAmount.setText(Integer.toString(uTotal));
	}
}

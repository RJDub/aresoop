package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;

import model.*;
import buildings.*;

public class BuildingPanel extends JPanel{
	
	private ArrayList<Building> buildingArray;
	private static JList buildingList;
	
	public BuildingPanel(ArrayList<Building> b) {
		buildingArray = b;
		layoutGUI();
	}

	private void layoutGUI() {
//		buildingArray.add(new StorageBuilding(4,4));
		buildingList = new JList(toArrayInString(buildingArray));
		buildingList.setPreferredSize(new Dimension(400,1000));
		JScrollPane scrollPane = new JScrollPane(buildingList);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .13)));
		this.add(scrollPane);
	}
	
	private String[] toArrayInString(ArrayList<Building> b) {
		String temp[] = new String[b.size()];
		for (int i = 0; i < b.size(); i++) {
			temp[i] = b.get(i).getType().toString();
		}
		return temp;
	}

	public static JList getBuildingList() {
		return buildingList;
	}

	public ArrayList<Building> getArrBuildings() {
		return buildingArray;
	}

	public void updateBuildingList(ArrayList<Building> in){
		buildingArray = in;
	}
	

	
}

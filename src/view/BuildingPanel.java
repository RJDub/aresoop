package view;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import model.*;

public class BuildingPanel extends JPanel{
	
	private ArrayList<Building> buildingArray;
	//private Building[] buildings;
	private JList buildingList;
	
	public BuildingPanel(ArrayList<Building> b) {
		buildingArray = b;
		layoutGUI();
	}

	private void layoutGUI() {
		buildingList = new JList(buildingArray.toArray());
		buildingList.setPreferredSize(new Dimension(600,1500));
		JScrollPane scrollPane = new JScrollPane(buildingList);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * .333),(int) (Toolkit.getDefaultToolkit().getScreenSize().height * .13)));
		this.add(scrollPane);
	}
	
	
}

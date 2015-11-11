package view;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import colonists.*;
import enums.*;
import model.Colonist;

public class ColonistPanel extends JPanel{
	
	public ArrayList<Colonist> colonists;
	private String[] columnNames;
	private String[][] data;
	private JTable table;
	private Farmer farmer;
	
	public ColonistPanel() {
		colonists = new ArrayList<Colonist>();
		farmer = new Farmer("Farmer1");
		farmer.setHunger(Hunger.Full);
		farmer.setThirst(Thirst.Quenched);
		farmer.setFatigue(Fatigue.Energized);
		colonists.add(farmer);
		layoutGUI();
	}

	private void layoutGUI() {
		this.setLayout(new BorderLayout());
		columnNames = new String[]{"Name", "Hunger", "Thirst", "Fatigue"};
		data = new String[colonists.size()][4];
		for(int i =0; i < colonists.size(); i++){
			data[i][0] = colonists.get(i).getName();
			data[i][1] = colonists.get(i).getHunger().toString();
			data[i][2] = colonists.get(i).getThirst().toString();
			data[i][3] = colonists.get(i).getFatigue().toString();
		}
		table = new JTable(new ColonistTableModel(data,columnNames,colonists.size()));
		table.setAutoCreateRowSorter(true);
		JScrollPane scrollPane = new JScrollPane(table);
		this.add(scrollPane,BorderLayout.CENTER);
	}
}

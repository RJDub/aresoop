package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import colonists.*;
import enums.*;
import model.Colonist;

public class ColonistPanel extends JPanel{
	
	public ArrayList<Colonist> colonists;
	private String[] columnNames;
	public String[][] data;
	public JTable table;
	private Farmer farmer;
	
	public ColonistPanel(ArrayList<Colonist> c) {
		colonists = c;
		layoutGUI();
	}

	private void layoutGUI() {
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
		table.setPreferredSize(new Dimension(400,350));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400,220));
		this.add(scrollPane);
	}
}

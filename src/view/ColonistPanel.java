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
	private String[][] data;
	private JTable table;
	private Farmer farmer;
	private HUD hud;
	
	public ColonistPanel() {
		colonists = new ArrayList<Colonist>();
		hud = new HUD();
		farmer = new Farmer("Farmer1");
		farmer.setHunger(Hunger.Full);
		farmer.setThirst(Thirst.Quenched);
		farmer.setFatigue(Fatigue.Energized);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
		colonists.add(farmer);
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
		table.setPreferredSize(new Dimension(400,400));
		table.addMouseListener(new RowSelectListener());
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(400,400));
		this.add(scrollPane);
	}
	
	private class RowSelectListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			int rowSelected = table.getSelectedRow();
			if (rowSelected < 0) {
				
			}
			else{
				hud.setInfo(data[rowSelected][0], data[rowSelected][1], data[rowSelected][2], data[rowSelected][3]);
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
